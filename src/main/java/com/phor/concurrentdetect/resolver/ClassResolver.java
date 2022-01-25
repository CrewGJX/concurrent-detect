package com.phor.concurrentdetect.resolver;

import java.io.File;
import java.util.Set;

public abstract class ClassResolver implements Resolver{
    public abstract Set<Class<?>> resolveClass(File path);
}
