package com.tjnu.club.redis.impl;

import cn.hutool.core.util.StrUtil;
import com.tjnu.club.redis.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class RedisDaoImpl implements RedisDao {

    @Autowired
    JedisPool jedisPool;

    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成real  key
            String realKey = key;
            String str = jedis.get(realKey);
            return str;
        } catch (Exception e) {
            log.error("redis连接池异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> mget(String... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> list = jedis.mget(keys);
            return list;
        } catch (Exception e) {
            log.error("redis连接池异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean set(String key, String value) {
        return set(key, value, 0);
    }

    @Override
    public Boolean set(String key, String value, Integer outTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value_new = value;
            if (StrUtil.isEmpty(value_new) || StrUtil.isBlank(value_new)) {
                return false;
            }
            //生成real  key
            String realKey = key;
            //过期时间
            int seconds = Integer.parseInt(outTime.toString());
            if (seconds <= 0) {
                jedis.set(realKey, value_new);
            } else {
                jedis.setex(realKey, seconds, value_new);
            }
            return true;
        } catch (Exception e) {
            log.error("redis连接池异常" + e.getMessage());
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成real key
            String realKey = key;
            return jedis.exists(realKey);
        } catch (Exception e) {
            log.error("redis连接池异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成real key
            String realKey = key;
            if (jedis.del(realKey) > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            log.error("redis连接池异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> getAllKey(String perfix) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys(perfix);
        } catch (Exception e) {
            log.error("redis连接池异常" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Integer expire(String key, Integer seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.expire(key, seconds).intValue();
        } catch (Exception e) {
            log.error("redis连接池异常" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
