package telran.annotations;

import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Index {
	
	boolean unique() default false;

}
