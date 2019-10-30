package com.mono.threadSample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("thread")
public class ThreadScopeObject implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadScopeObject.class);

    private String field1;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("****************** new object - " + this);
    }
}
