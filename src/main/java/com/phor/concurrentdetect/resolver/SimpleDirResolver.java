package com.phor.concurrentdetect.resolver;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SimpleDirResolver extends DirResolver {

    @Resource
    ResolverContext resolverContext;

    @Override
    public Set<Class<?>> resolverDir(File path) {
        File[] files = path.listFiles();

        if (ArrayUtils.isNotEmpty(files)) {
            return Arrays.stream(files)
                    .flatMap(f -> resolverContext.resolve(f).stream())
                    .collect(Collectors.toSet());
        } else {
            return Collections.emptySet();
        }
    }
}
