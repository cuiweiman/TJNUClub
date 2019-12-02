package com.tjnu.club.service.impl;

import com.github.pagehelper.PageHelper;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.mapper.UserInfoMapper;
import com.tjnu.club.service.UserInfoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> listUserInfo(Integer page,Integer size) {
        PageHelper.startPage(page,size);
        return userInfoMapper.listUserInfo();
    }

}
