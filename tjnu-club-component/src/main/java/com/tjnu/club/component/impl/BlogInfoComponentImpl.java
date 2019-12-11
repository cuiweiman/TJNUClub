package com.tjnu.club.component.impl;

import com.tjnu.club.mapper.BlogInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BlogInfoComponentImpl {

    @Resource
    private BlogInfoMapper blogInfoMapper;



}
