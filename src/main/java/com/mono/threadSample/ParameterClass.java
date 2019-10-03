package com.mono.threadSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParameterClass {

    @Autowired
    SessionInfoClass sessionInfoClass;

    public void setSessionInfoClass(SessionInfoClass sessionInfoClass) {
        this.sessionInfoClass = sessionInfoClass;
    }

    public SessionInfoClass getSessionInfoClass() {
        return sessionInfoClass;
    }

    @Override
    public String toString() {
        return sessionInfoClass.toString();
    }
}
