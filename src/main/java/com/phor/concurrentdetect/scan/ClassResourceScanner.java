package com.phor.concurrentdetect.scan;

import java.net.URL;
import java.util.Enumeration;

public abstract class ClassResourceScanner implements Scanner{
    public abstract Enumeration<URL> scanInProject(String projectBaseDir, String buildTargetDir, String libTargetDir) throws Exception;
}
