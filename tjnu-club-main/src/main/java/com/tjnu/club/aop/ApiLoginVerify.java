package com.tjnu.club.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiLoginVerify {

    /*@Pointcut("execution(public * com.tjnu.club.web..*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void beforeQuery(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String userId = args[1].toString();
        System.out.println(userId);
        String token = args[0].toString();
        System.out.println(token);
    }*/

}
