package com.mono.threadSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class MainController {

    @Autowired
    RunnableService runnableService;

    @Autowired
    ParameterClass parameterClass;

    @Autowired
    SessionInfoClass otherParameterClass;

    @RequestMapping(value="/testAsync", method= RequestMethod.GET)
    public void endPointGet(@PathVariable String endPoint,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {

        otherParameterClass.setSubjectId("MyId");
        parameterClass.sessionInfoClass = otherParameterClass;

        CompletableFuture<Map> resultAsync = runnableService.startAsyncProcess(parameterClass, 10);

        Map map = resultAsync.get();
        try (OutputStream outs = response.getOutputStream()) {
            byte[] outByte = map.toString().getBytes(StandardCharsets.UTF_8);
            response.setContentLength(outByte.length);
            outs.write(outByte);
            outs.flush();
        }
    }
}
