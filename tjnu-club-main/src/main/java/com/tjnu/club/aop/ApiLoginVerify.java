package com.tjnu.club.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/4 20:53
 * @Description: 切面，暂时还没想好干啥，先留着
 */
@Slf4j
@Aspect
@Component
public class ApiLoginVerify {

    @Pointcut("execution(public * com.tjnu.club.api..*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void beforeQuery(JoinPoint joinPoint) {
        Date now = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
        log.info("aop_" + dateStr, joinPoint.toString());
    }

}
