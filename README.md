# SpringBoot and Thread Sample
Using Spring Boot and a thread call a method in an external library.
The method in the external library depends on a class that is being overwritten locally.
The local class uses ApplicationContextHolder to call a local method.

# Problem
When the local method is called an exception is generated.

<pre>
Caused by: java.lang.IllegalStateException: No thread-bound request found: Are you referring to request attributes outside of an actual web request, or processing a request outside of the originally receiving thread? If you are actually operating within a web request and still receive this message, your code is probably running outside of DispatcherServlet: In this case, use RequestContextListener or RequestContextFilter to expose the current request.
	at org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes(RequestContextHolder.java:131)
	at org.springframework.web.context.request.SessionScope.get(SessionScope.java:55)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:353)
</pre>


