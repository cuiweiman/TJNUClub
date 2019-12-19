package com.tjnu.club.aop;

import com.tjnu.club.anno.RequestLimit;
import com.tjnu.club.enums.TJNUResultEnum;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.redis.RedisDao;
import com.tjnu.club.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/4 20:53
 * @Description: 切面，暂时还没想好干啥，先留着
 */
@Slf4j
@Aspect
@Component
public class TJNUClubAspect {

    @Resource
    private RedisDao redisDao;

    @Around("@annotation(limit)")
    public Object requestLimit(ProceedingJoinPoint joinPoint, RequestLimit limit) throws TJNUException {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String ip = getIpAddress(request);
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat("_").concat(ip);
            boolean checkResult = checkWithRedis(limit, key);
            if (!checkResult) {
                log.error("requestLimited," + "[用户ip:{}],[访问地址:{}]超过了限定的次数[{}]次", ip, url, limit.count());
                return new ResultVO<>(new TJNUException(TJNUResultEnum.ASPECT_IP_VISITED_LIMIT));
            }else{
                try {
                    Object result = joinPoint.proceed();
                    return result;
                } catch (Throwable t) {
                    log.error(t.getMessage(), t);
                    return "Aspect ERROR";
                }
            }
        } catch (TJNUException e) {
            log.error(e.getMsg(),e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error("RequestLimitAop.requestLimit has some exceptions: ", e);
            return new ResultVO<>(new TJNUException());
        }
    }

    /**
     * 以redis实现请求记录
     *
     * @param limit
     * @param key
     * @return
     */
    private boolean checkWithRedis(RequestLimit limit, String key) {
        long count = redisDao.incr(key);
        if (count == 1) {
            redisDao.expire(key, limit.time());
        }
        if (count > limit.count()) {
            return false;
        }
        return true;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.info("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        log.info("获取客户端ip: " + ip);
        return ip;
    }

}
