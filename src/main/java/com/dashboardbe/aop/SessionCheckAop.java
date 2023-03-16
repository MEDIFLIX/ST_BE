package com.dashboardbe.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
@Slf4j
public class SessionCheckAop {
    /**
     * "세션체크" 공통 관심사 AOP 처리
     */
    @Around("@annotation(com.dashboardbe.aop.LoginCheck)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=== Session Check Start ===");
        // 세션값 가져오기
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        String memberId = (String)session.getAttribute("loginID");
        // 세션에 값이 없는 경우 처리
        if (memberId == null) {
            log.info("=== Wrong Session ===");
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "NO_LOGIN") {};
        }
        log.info("=== Session Check End ===");
        return joinPoint.proceed();
    }
}
