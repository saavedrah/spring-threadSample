package com.mono.threadTestBaseLibrary;

import com.mono.threadSample.ApplicationContextHolder;
import com.mono.threadSample.ParameterClass;

public class BeanCallerClass {

    public String getSubjectId() {
        ParameterClass parameterClass = ApplicationContextHolder.getContext().getBean(ParameterClass.class);
        return parameterClass.getSessionInfoClass().getSubjectId();
    }
}
