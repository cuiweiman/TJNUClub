package com.tjnu.club.redis;

import java.util.List;
import java.util.Set;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/2 17:57
* @Description: Redis操作
*/
public interface RedisDao {


    /**
     * 获取键对应值
     *
     * @param key
     * @return
     */
    String get(String key);


    /**
     * 根据key数组，获取多个对应值
     *
     * @param keys
     * @return
     */
    List<String> mget(String... keys);


    /**
     * 通过set方法向缓存中插入新的键值对
     *
     * @param key
     * @param value
     * @return
     */
    Boolean set(String key, String value);

    /**
     * 通过set方法向缓存中插入新的键值对
     *
     * @param key
     * @param value
     * @param outTime 超时时间
     * @return
     */
    Boolean set(String key, String value, Integer outTime);


    /**
     * 通过键判断 key-value 是否存在
     *
     * @param key
     * @return
     */
    Boolean exists(String key);


    /**
     * 删除
     *
     * @param key
     * @return
     */
    Boolean del(String key);


    /**
     * 获取包含前缀perfix的所有的key值
     *
     * @param perfix
     * @return
     */
    Set<String> getAllKey(String perfix);


    /**
     * 针对指定的key设定过期时间
     *
     * @param key
     * @param seconds
     * @return
     */
    Integer expire(String key, Integer seconds);


}
