package com.phor.concurrentdetect.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "output")
@Data
public class OutputSettings {
    private String filePath;
}
