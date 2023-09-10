package pptware.loggingsolution.logging;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j // 로거 필드 자동 생성을 위해 적용
@Component // 이 클래스를 빈에 등록
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
/*
스프링은 스프링 빈 컨테이너의 인스턴스를 싱글톤으로 관리함. 하지만 요청이 동시에 들어왔을 경우 필드를 공유하게됨(동시성 이슈)
@Scope를 사용하여 개별 request가 발생할 때마다 새로운 DataHolder 인스턴스를 생성
해당 스코프를 처리하기 위한 프록시 생성
*/
@Getter
@Setter
public class DataHolder extends StopWatch {
    private String uri;
    private String method;

    @PostConstruct
    private void init() {
        this.start();
    } // StopWatch를 시작

    public void setUri(String uri) {
        this.uri = uri;
        log.info("START [{}] [{}]", uri, method);
    }

    @PreDestroy
    private void destroy() {
        this.stop();
        log.info("END [{}] [{}] [{}]ms", uri, method, this.getTotalTimeMillis());
    }

}
