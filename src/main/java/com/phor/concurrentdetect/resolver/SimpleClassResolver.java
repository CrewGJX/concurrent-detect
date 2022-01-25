package com.phor.concurrentdetect.resolver;

import com.phor.concurrentdetect.consts.FileTypeEnum;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Component
public class SimpleClassResolver extends ClassResolver {
    @Override
    public Set<Class<?>> resolveClass(File path) {
        String fileName = path.getName();
        try {
            // 获取字节码文件
            if (fileName.endsWith(FileTypeEnum.CLASS_PROTOCOL.getProtocol())) {
                // java文件 映射class
                String className = fileName.replaceAll(".*(com/.*)\\.class$", "$1").replaceAll("/", ".");
                Class.forName(className);
                return Sets.newHashSet(aClass);
            } else if (fileName.endsWith(FileTypeEnum.JAR_PROTOCOL.getProtocol())) {
                // jar 获取文件。

                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> entries = jarFile.entries();
                Set<Class<?>> classes = new HashSet<>();
                while (entries.hasMoreElements()) {
                    JarEntry jarEntry = entries.nextElement();
                    String jarFileName = jarEntry.getName();
                    if (!jarFileName.contains("package-info") && jarFileName.endsWith(FileTypeEnum.CLASS_PROTOCOL.getProtocol())) {
                        String className = jarFileName.replaceAll(".*(com/.*)\\.class$", "$1").replaceAll("/", ".");
                        if (isNeed().test(jarFileName) && isConCurrent().test(jarFileName)) {
                            Class<?> aClass = Class.forName(className);
                            classes.add(aClass);
                        }
                    }
                }
                return classes;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }
}
