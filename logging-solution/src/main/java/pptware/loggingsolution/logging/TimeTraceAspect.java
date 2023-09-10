package pptware.loggingsolution.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeTraceAspect {

    private final DataHolder dataHolder;

    public TimeTraceAspect(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    @Around("@annotation(timeTrace)") // TimeTrace 어노테이션이 적용된 곳을 포인트컷으로 사용
    public Object doTimeTrace(ProceedingJoinPoint joinPoint, TimeTrace timeTrace) throws Throwable {

        String name = joinPoint.getSignature().toShortString().split("\\(")[0] + "()"; // 현재 실행중인 메소드의 시그니처 정보를 가져오는 메소드
        String uri = dataHolder.getUri();
        int millis = timeTrace.millis();
        final int limitMillis = 300;

        dataHolder.stop();

        doLogTime("-->", uri, name, dataHolder.getLastTaskTimeMillis(), millis, limitMillis);

        dataHolder.start();

        Object result = joinPoint.proceed();

        dataHolder.stop();

        doLogTime("<--", uri, name, dataHolder.getLastTaskTimeMillis(), millis, limitMillis);

        dataHolder.start();

        return result;
    }

    private void doLogTime(String arrow, String uri, String name, long lastStopWatchTime,
                           int millis, int limitMillis) {
        if (lastStopWatchTime <= millis) {
            log.info(arrow + "[{}][{}ms][{}]", uri, lastStopWatchTime, name);
        } else if (lastStopWatchTime <= limitMillis) {
            log.warn(arrow + "[{}][{}ms][{}]", uri, lastStopWatchTime, name);
        } else log.error(arrow + "[{}][{}ms][{}]", uri, lastStopWatchTime, name);
    }
}