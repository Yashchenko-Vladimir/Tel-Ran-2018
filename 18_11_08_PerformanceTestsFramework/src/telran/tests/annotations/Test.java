package telran.tests.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
//	int nRuns() default  1;
	int value() default 1;
}
