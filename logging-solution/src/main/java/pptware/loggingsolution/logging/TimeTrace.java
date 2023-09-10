package pptware.loggingsolution.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 어노테이션을 적용할 요소를 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 라이프사이클 지정
public @interface TimeTrace {

    int millis() default 50;
}