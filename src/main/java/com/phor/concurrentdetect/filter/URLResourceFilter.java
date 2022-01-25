package com.phor.concurrentdetect.filter;

import java.net.URL;
import java.util.function.Predicate;

public abstract class URLResourceFilter implements Filter{
    public abstract boolean filter(URL url, Predicate<URL> optional);
}
