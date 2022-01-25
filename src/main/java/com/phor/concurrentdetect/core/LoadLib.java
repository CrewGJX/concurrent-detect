package com.phor.concurrentdetect.core;

import java.io.IOException;

public interface LoadLib {
    void load(String[] jarFileNameArray) throws IOException;
}
