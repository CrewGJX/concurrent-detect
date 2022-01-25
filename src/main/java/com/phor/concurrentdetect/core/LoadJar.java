package com.phor.concurrentdetect.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

@Component
public class LoadJar implements LoadLib{

    @Override
    public void load(String[] jarFileNameArray) throws IOException {
        System.out.println("loading jar" + StringUtils.join(jarFileNameArray, ";"));

        for (String jarFileName : jarFileNameArray) {
            File file = new File(jarFileName);

            URLClassLoader sysLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            Class<? extends URLClassLoader> sysClass = URLClassLoader.class;
            try {
                Method method = sysClass.getDeclaredMethod("addURL", URL.class);
                method.setAccessible(true);
                method.invoke(sysLoader, file.toURI().toURL());
            } catch (Throwable t) {
                throw new IOException("Error, could not add URL to system classloader", t);
            }
        }
    }

}
