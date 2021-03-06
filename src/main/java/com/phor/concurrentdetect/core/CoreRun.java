package com.phor.concurrentdetect.core;

import com.phor.concurrentdetect.conf.OutputSettings;
import com.phor.concurrentdetect.conf.ProjectSettings;
import com.phor.concurrentdetect.filter.ClassFilter;
import com.phor.concurrentdetect.filter.URLResourceFilter;
import com.phor.concurrentdetect.resolver.ResolverContext;
import com.phor.concurrentdetect.scan.ClassResourceScanner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

@Service
public class CoreRun implements ApplicationContextAware {

    @Resource
    ProjectSettings settings;

    @Resource
    LoadLib loadJar;

    @Resource
    ClassResourceScanner simpleClassScanner;

    @Resource
    OutputSettings outputSettings;

    @Resource
    ClassFilter simpleClassFilter;

    @Resource
    URLResourceFilter simpleURLResourceFilter;

    @Resource
    ResolverContext resolverContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            List<String> libPath = settings.getLibPath();
            String[] libPathArray = new String[libPath.size()];
            libPath.toArray(libPathArray);
//            loadJar.load(libPathArray);

            Enumeration<URL> resources = simpleClassScanner.scanInProject(settings.getBaseDir(),
                    "target/dsmc-server/WEB-INF/classes/com/",
                    "target/dsmc-server/WEB-INF/lib/");

            // 获得所有符合条件的class 集合
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();

                boolean filter = simpleURLResourceFilter.filter(url, null);
                if (!filter) {
                    continue;
                }

                String fileName = url.getFile();
                File file = new File(fileName);
                Set<Class<?>> resolve = resolverContext.resolve(file);

//                Set<Class<?>> controllers = recursiveGetClasses(resources.nextElement());
//                allControllers.addAll(controllers);
            }

//            recordAllFilteredClasses(allControllers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
