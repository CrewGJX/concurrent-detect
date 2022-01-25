package com.phor.concurrentdetect.consts;

public enum FileTypeEnum {
    FILE_PROTOCOL("file"),
    DIR_PROTOCOL("dir"),
    JAR_PROTOCOL("jar"),
    JAVA_PROTOCOL("java"),
    CLASS_PROTOCOL("class");


    private String protocol;

    FileTypeEnum(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
