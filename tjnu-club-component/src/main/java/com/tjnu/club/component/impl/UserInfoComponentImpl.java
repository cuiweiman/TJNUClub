package com.tjnu.club.component.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tjnu.club.component.UserInfoComponent;
import com.tjnu.club.enums.TJNUResultEnum;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.mapper.UserInfoMapper;
import com.tjnu.club.utils.KeyFactory;
import com.tjnu.club.utils.QiniuUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class UserInfoComponentImpl implements UserInfoComponent {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Boolean saveUserInfo(UserInfo userInfo) {
        // userId校验
        String userId = KeyFactory.genDefaultSerialNo();
        Boolean flag = Boolean.TRUE;
        while (flag) {
            UserInfo query = getUserInfoByUserId(userId);
            if (!ObjectUtil.isEmpty(query)) {
                userId = KeyFactory.genDefaultSerialNo();
            }
            flag = Boolean.FALSE;
        }
        userInfo.setUserId(userId);

        // nickName校验重复
        UserInfo query2 = getUserInfoByNickName(userInfo.getUserId(), userInfo.getNickName());
        if (!ObjectUtil.isEmpty(query2)) {
            throw new TJNUException(TJNUResultEnum.USER_NICKNAME_REPEATED);
        }

        //email校验重复
        UserInfo query3 = getUserInfoByEmail(userInfo.getUserId(), userInfo.getEmail());
        if (!ObjectUtil.isEmpty(query3)) {
            throw new TJNUException(TJNUResultEnum.USER_EMAIL_REPEATED);
        }
        Integer result = userInfoMapper.saveUserInfo(userInfo);
        if (result != 1) {
            throw new TJNUException(TJNUResultEnum.USER_SAVE_FAILURE);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {
        //用户是否存在
        UserInfo query = getUserInfoByUserId(userInfo.getUserId());
        if (ObjectUtil.isEmpty(query)) {
            throw new TJNUException(TJNUResultEnum.USER_NOT_EXISTS);
        }

        // nickName校验重复
        UserInfo query2 = getUserInfoByNickName(userInfo.getUserId(), userInfo.getNickName());
        if (!ObjectUtil.isEmpty(query2)) {
            throw new TJNUException(TJNUResultEnum.USER_NICKNAME_REPEATED);
        }

        //email校验重复
        UserInfo query3 = getUserInfoByEmail(userInfo.getUserId(), userInfo.getEmail());
        if (!ObjectUtil.isEmpty(query3)) {
            throw new TJNUException(TJNUResultEnum.USER_EMAIL_REPEATED);
        }

        Integer result = userInfoMapper.updateUserInfo(userInfo);
        if (result != 1) {
            throw new TJNUException(TJNUResultEnum.USER_UPDATE_FAILURE);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteUserInfo(String userId) {
        //用户是否存在
        UserInfo query = getUserInfoByUserId(userId);
        if (ObjectUtil.isEmpty(query)) {
            throw new TJNUException(TJNUResultEnum.USER_NOT_EXISTS);
        }
        Integer result = userInfoMapper.deleteUserInfo(userId);
        if (result != 1) {
            throw new TJNUException(TJNUResultEnum.USER_DELETE_FAILURE);
        }
        return Boolean.TRUE;
    }

    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        UserInfo info = userInfoMapper.getUserInfoByUserId(userId);
        String userKey = Optional.ofNullable(info.getUserImg()).orElse("");
        if(!StrUtil.isEmpty(userKey)){
            String url = QiniuUtil.readUrl(userKey);
            info.setUserImg(url);
        }
        return info;
    }

    @Override
    public UserInfo getUserInfoByNickName(String userId, String nickName) {
        UserInfo info = userInfoMapper.getUserInfoByNickName(userId, nickName);
        return info;
    }

    @Override
    public UserInfo getUserInfoByEmail(String userId, String email) {
        UserInfo info = userInfoMapper.getUserInfoByEmail(userId, email);
        return info;
    }

    @Override
    public Map<String,Object> listUserInfo(Integer page, Integer size) {
        Map<String,Object> map = new HashMap<>();
        Long count = Optional.ofNullable(userInfoMapper.countUserInfo()).orElse(0L);
        List<UserInfo> list = Optional.ofNullable(userInfoMapper.listUserInfo(page,size)).orElse(new ArrayList<>());
        map.put("count",count);
        map.put("data",list);
        return map;
    }

    @Override
    public UserInfo getUserInfoByNickNameOrEmail(String nickNameOrEmail) {
        UserInfo info = userInfoMapper.getUserInfoByNickNameOrEmail(nickNameOrEmail);
        return info;
    }
}
