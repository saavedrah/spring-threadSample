package com.mono.threadSample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={ThreadSampleApplication.class})
@WebAppConfiguration
public class RunnableServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(RunnableServiceTest.class);

	@Autowired
    private RunnableService runnableService;

	@Test
	public void testAsyncParameters() throws Exception {

		ParameterClass parameterClass = new ParameterClass();
		parameterClass.sessionInfoClass = new SessionInfoClass();

		parameterClass.sessionInfoClass.setSubjectId("mySubjectId");

		Map<String, CompletableFuture<Map>> resultMap = new HashMap<>();

		for (int i = 0; i < 2; i++) {
			String key = "key_" + i;
			resultMap.put(key, runnableService.startAsyncProcess(parameterClass, 10));
		}

		resultMap.forEach((key, value) -> {
			try {
				LOGGER.info("RESULT from RunnableService:" + value.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testContextSameObject() {
		Map<String, CompletableFuture<Map>> resultMap = new HashMap<>();
		resultMap.put("key1", runnableService.objectInstanceTest());

		LOGGER.info("RESULT: " + resultMap);
	}
}
