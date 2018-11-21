package telran.tests.performance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import telran.tests.annotations.Test;

public class PerformanceTest {
	public void runTest(Class<?> clazz) throws Exception{
		Method[] protocolMethods = clazz.getMethods();
		
		for(Method method : protocolMethods) {
			if(method.isAnnotationPresent(Test.class)) {
				runTest(method, method.getAnnotation(Test.class).value(), clazz);
			}
		}
		
	}

	private void runTest(Method method, int nRuns, Class<?> clazz) throws Exception{
		Object obj = clazz.getConstructor().newInstance();
		Instant start = Instant.now();
		for (int i = 0; i < nRuns; i++) {
			method.invoke(obj);
		}
		Instant finish = Instant.now();
		System.out.printf("method: %s, runs: %d, running time: %d\n", method.getName(), nRuns, ChronoUnit.MILLIS.between(start, finish));
		
		
	}
}
