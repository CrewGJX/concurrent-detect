package com.phor.concurrentdetect.filter;

import com.phor.concurrentdetect.consts.FileTypeEnum;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.function.Predicate;

@Component
public class SimpleURLResourceFilter extends URLResourceFilter {
    @Override
    public boolean filter(URL url, Predicate<URL> optional) {
        String protocol = url.getProtocol();
        boolean isLocal = FileTypeEnum.FILE_PROTOCOL.getProtocol().equals(protocol) || FileTypeEnum.JAR_PROTOCOL.getProtocol().equals(protocol);

        if (optional != null) {
            return isLocal && optional.test(url);
        } else {
            return isLocal;
        }
    }
}
