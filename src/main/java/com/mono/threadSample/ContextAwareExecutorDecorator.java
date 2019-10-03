package com.mono.threadSample;

import org.slf4j.MDC;
import org.springframework.core.task.TaskExecutor;

import java.util.Map;
import java.util.concurrent.Executor;

@Deprecated
public class ContextAwareExecutorDecorator implements Executor, TaskExecutor {

    private final Executor executor;

    public ContextAwareExecutorDecorator(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Runnable command) {
        Runnable ctxAwareCommand = wrapContextAware(command);
        executor.execute(ctxAwareCommand);
    }


    private Runnable wrapContextAware(Runnable command) {

        final Map<String, String> callerContextCopy = MDC.getCopyOfContextMap();

        Runnable ctxAwareTask = () -> {
            final Map<String, String> executorContextCopy = MDC.getCopyOfContextMap();

            MDC.clear();
            if (callerContextCopy != null) {
                MDC.setContextMap(callerContextCopy);
            }

            command.run();

            MDC.clear();
            if (executorContextCopy != null) {
                MDC.setContextMap(executorContextCopy);
            }
        };

        return ctxAwareTask;
    }
}
