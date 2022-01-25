package com.phor.concurrentdetect.filter;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class SimpleClassFilter extends ClassFilter {
    @Override
    public void filter(Class<?> clazz, Predicate<Class<?>> optional) {

    }
}
