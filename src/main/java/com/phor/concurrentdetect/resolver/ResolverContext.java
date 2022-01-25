package com.phor.concurrentdetect.resolver;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Collections;
import java.util.Set;

@Component
public class ResolverContext {
    @Resource
    DirResolver simpleDirResolver;

    @Resource
    ClassResolver simpleClassResolver;

    public Set<Class<?>> resolve(File srcFile) {
        boolean directory = srcFile.isDirectory();
        boolean file = srcFile.isFile();

        if (directory) {
            return simpleDirResolver.resolverDir(srcFile);
        }

        if (file) {
            return simpleClassResolver.resolveClass(srcFile);
        }

        return Collections.EMPTY_SET;
    }
}
