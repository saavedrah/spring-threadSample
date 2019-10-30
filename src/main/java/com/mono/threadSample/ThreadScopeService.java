package com.mono.threadSample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadScopeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadScopeService.class);

    @Autowired
    private ThreadScopeObject threadScopeObject;

    public void showObject() {
        LOGGER.info ("ShowObject: " + threadScopeObject);
    }
}
