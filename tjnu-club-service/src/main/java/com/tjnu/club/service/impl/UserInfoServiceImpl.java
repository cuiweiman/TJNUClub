package com.tjnu.club.service.impl;

import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.mapper.UserInfoMapper;
import com.tjnu.club.service.UserInfoService;
import com.tjnu.club.utils.KeyFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Transactional
    @Override
    public Boolean saveUserInfo(UserInfo userInfo) {
        // userId校验
        String userId = KeyFactory.genDefaultSerialNo();
        Boolean flag = Boolean.TRUE;
        while (flag) {
            UserInfo query = getUserInfoByUserId(userId);
            if (!ObjectUtils.isEmpty(query)) {
                userId = KeyFactory.genDefaultSerialNo();
            }
            flag = Boolean.FALSE;
        }

        // nickName校验重复
        UserInfo query2 = getUserInfoByNickName(userInfo.getNickName());
        if (!ObjectUtils.isEmpty(query2)) {
            throw new TJNUException(TJNUConstants.NICK_NAME_REPEAT);
        }

        //email校验重复
        UserInfo query3 = getUserInfoByEmail(userInfo.getEmail());
        if (!ObjectUtils.isEmpty(query2)) {
            throw new TJNUException(TJNUConstants.EMAIL_REPEAT);
        }
        Integer result = userInfoMapper.saveUserInfo(userInfo);
        if (result != 1) {
            throw new TJNUException(TJNUConstants.SAVE_USER_ERROR);
        }
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {
        //用户是否存在
        UserInfo query = getUserInfoByUserId(userInfo.getUserId());
        if (ObjectUtils.isEmpty(query)) {
            throw new TJNUException(TJNUConstants.USER_NOT_EXISTS);
        }

        // nickName校验重复
        UserInfo query2 = getUserInfoByNickName(userInfo.getNickName());
        if (!ObjectUtils.isEmpty(query2)) {
            throw new TJNUException(TJNUConstants.NICK_NAME_REPEAT);
        }

        Integer result = userInfoMapper.updateUserInfo(userInfo);
        if (result != 1) {
            throw new TJNUException(TJNUConstants.UPDATE_USER_ERROR);
        }
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public Boolean deleteUserInfo(String userId) {
        //用户是否存在
        UserInfo query = getUserInfoByUserId(userId);
        if (ObjectUtils.isEmpty(query)) {
            throw new TJNUException(TJNUConstants.USER_NOT_EXISTS);
        }
        Integer result = userInfoMapper.deleteUserInfo(userId);
        if (result != 1) {
            throw new TJNUException(TJNUConstants.DELETE_USER_ERROR);
        }
        return Boolean.TRUE;
    }

    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        UserInfo info = userInfoMapper.getUserInfoByUserId(userId);
        return info;
    }

    @Override
    public UserInfo getUserInfoByNickName(String nickName) {
        UserInfo info = userInfoMapper.getUserInfoByNickName(nickName);
        return info;
    }

    @Override
    public UserInfo getUserInfoByEmail(String email) {
        UserInfo info = userInfoMapper.getUserInfoByEmail(email);
        return info;
    }

    @Override
    public List<UserInfo> listUserInfo(Integer page, Integer size) {
        List<UserInfo> list = userInfoMapper.listUserInfo();
        return list;
    }
}
