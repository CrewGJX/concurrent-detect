package com.phor.concurrentdetect.filter;

import java.util.function.Predicate;

public abstract class ClassFilter implements Filter{
    public abstract void filter(Class<?> clazz, Predicate<Class<?>> optional);
}
