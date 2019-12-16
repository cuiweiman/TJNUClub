package com.tjnu.club;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/1 23:18
 * @Description: 启动类
 */
@Configuration
@SpringBootApplication
@MapperScan("com.tjnu.club.mapper")
public class TJNUClub {

    public static void main(String[] args) {
        SpringApplication.run(TJNUClub.class, args);
    }



    /**
     * 设置 SpringBoot 对文件大小 的限制
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize("2MB"); //单个文件大小
        //config.setMaxRequestSize("100MB"); //总文件大小
        return config.createMultipartConfig();
    }

}
