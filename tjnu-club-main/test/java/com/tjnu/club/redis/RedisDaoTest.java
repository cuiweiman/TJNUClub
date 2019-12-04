package com.tjnu.club.redis;

import com.tjnu.club.TJNUClubTest;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class RedisDaoTest extends TJNUClubTest {

    @Resource
    private RedisDao redisDao;

    @Test
    public void get() {
        String name = redisDao.get("name");
        System.out.println(name);
    }

    @Test
    public void mget() {
    }

    @Test
    public void set() {
        String key = "grade";
        String value = "大三";
        redisDao.set(key,value,-1);


    }

    @Test
    public void exists() {
    }

    @Test
    public void del() {
    }

    @Test
    public void getAllKey() {
    }

    @Test
    public void expire() {
    }
}