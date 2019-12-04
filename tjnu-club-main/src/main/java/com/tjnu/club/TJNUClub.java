package com.tjnu.club;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/1 23:18
* @Description: 启动类
*/
@SpringBootApplication
@MapperScan("com.tjnu.club.mapper")
public class TJNUClub {

    public static void main(String[] args) {
        SpringApplication.run(TJNUClub.class, args);
    }

}
