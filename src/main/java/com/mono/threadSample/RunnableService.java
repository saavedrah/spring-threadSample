package com.mono.threadSample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class RunnableService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunnableService.class);

    @Autowired
    SessionInfoClass sessionInfoClass;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Map> startAsyncProcess(ParameterClass parameterClass, int sleep) throws Exception {

        LOGGER.info(String.format("Thread - Parameters: %s ", parameterClass.toString()));

        if (sleep > 0) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Sleep: " + sleep + "[sec]");
            }
            Thread.sleep(sleep * 1000);
        }

        Class<?> _class = Class.forName("com.mono.threadTestLibrary.CallerClass");
        Method method = _class.getDeclaredMethod("getParameterClassValues");
        String fromExtClass = (String) method.invoke(_class.newInstance());

        LOGGER.info(String.format("Thread - From External Class: %s ", fromExtClass));

        Map<String,Object> response = new HashMap<>();
        response.put("out","OK");
        response.put("inParam", parameterClass.toString());

        return CompletableFuture.completedFuture(response);
    }

}
