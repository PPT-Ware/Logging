package pptware.loggingsolution.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class DataInterceptor implements HandlerInterceptor {

    private final DataHolder dataHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        dataHolder.setMethod(request.getMethod());
        dataHolder.setUri(request.getRequestURI());
        return true;
    }
}