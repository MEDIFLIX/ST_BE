package com.dashboardbe.dashboardbe.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginCheck {
    /**
     * 세션 유지가 필요한 메소드에 @LoginCheck 어노테이션 사용하면 됨!
     */
}
