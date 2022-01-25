package com.phor.concurrentdetect.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties(prefix = "external.project")
@Data
@Configuration
public class ProjectSettings {
    private String baseDir;

    private List<String> libPath;

    private String libPrefix;
}
