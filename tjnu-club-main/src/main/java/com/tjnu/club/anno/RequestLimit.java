package com.tjnu.club.anno;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/19 16:09
 * @Description: 自定义 注解类——规定时间内，限制IP访问次数
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {

    /**
     * 允许访问的最大次数
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间段，单位为秒，默认值 60秒（1分钟）
     */
    int time() default 60;

}
