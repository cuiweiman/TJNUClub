package com.tjnu.club.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/2 17:57
* @Description: Redis连接池
*/
@Configuration
public class RedisPoolFactoryConfig {

    @Value("${tjnu.redis.host}")
    private String host;

    @Value("${tjnu.redis.port}")
    private int port;

    @Value("${tjnu.redis.password}")
    private String password;

    @Value("${tjnu.redis.timeout}")
    private int timeout;

    //连接池最大线程数
    @Value("${tjnu.redis.jedis.pool.max-active}")
    private int maxActive;

    //等待时间
    @Value("${tjnu.redis.jedis.pool.max-wait}")
    private long maxWait;

    //最大空闲连接
    @Value("${tjnu.redis.jedis.pool.max-idle}")
    private int maxIdle;


    /**
     * @return 返回RedisPool 连接池
     * @Description Redis连接配置
     */
    @Bean
    public JedisPool JedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxWaitMillis(maxWait);

        JedisPool jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
        return jedisPool;
    }
}
