package com.mono.threadSample;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public interface IRunnableService_bak {

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Map> startAsyncProcess (ParameterClass parameterClass, int sleep) throws Exception;
}
