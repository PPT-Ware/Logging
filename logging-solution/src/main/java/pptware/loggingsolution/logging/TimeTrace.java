package pptware.loggingsolution.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeTrace {

    /**
     *  Aspect에서 log.WARN을 표시할 시간 기준 ms
     */
    int millis() default 50;

}