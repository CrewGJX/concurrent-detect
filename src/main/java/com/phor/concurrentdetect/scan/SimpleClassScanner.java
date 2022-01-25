package com.phor.concurrentdetect.scan;

import com.phor.concurrentdetect.conf.ProjectSettings;
import com.phor.concurrentdetect.consts.FileTypeEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import sun.misc.CompoundEnumeration;

import javax.annotation.Resource;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SimpleClassScanner extends ClassResourceScanner {

    @Resource
    ProjectSettings projectSettings;

    @Override
    public Enumeration<URL> scanInProject(String projectBaseDir, String buildTargetDir, String libTargetDir) throws Exception {
        String libPrefix = projectSettings.getLibPrefix();
        String sourceDir = StringUtils.joinWith(File.pathSeparator, projectBaseDir, buildTargetDir);
        String libDir = StringUtils.joinWith(File.pathSeparator, projectBaseDir, libTargetDir);

        File libDirFile = new File(libDir);
        File[] files = libDirFile.listFiles((dir, name) -> name.startsWith(libPrefix));

        URL sourceDirUrl = new URL(FileTypeEnum.FILE_PROTOCOL.getProtocol(), "", 0, sourceDir);

        Enumeration<URL>[] tmp = (Enumeration<URL>[]) new Enumeration<?>[2];
        tmp[0] = Collections.enumeration(Collections.singletonList(sourceDirUrl));

        if (ArrayUtils.isNotEmpty(files)) {
            tmp[1] = Collections.enumeration(Arrays.stream(files).map(f -> {
                try {
                    return new URL(FileTypeEnum.FILE_PROTOCOL.getProtocol(), "", 0, f.getAbsolutePath());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList()));
        }

        return new CompoundEnumeration<>(tmp);
    }
}
