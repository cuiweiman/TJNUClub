package com.tjnu.club.component.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import com.tjnu.club.component.RegisterLoginComponent;
import com.tjnu.club.component.UserInfoComponent;
import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.redis.RedisDao;
import com.tjnu.club.utils.KeyFactory;
import com.tjnu.club.utils.MailUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Component
public class RegisterLoginComponentImpl implements RegisterLoginComponent {

    @Resource
    private MailUtil mailUtill;

    @Resource
    private RedisDao redisDao;

    @Resource
    private UserInfoComponent userInfoComponent;

    @Transactional
    @Override
    public UserInfo login(String nickNameOrEmail, String password) {
        UserInfo info = userInfoComponent.getUserInfoByNickNameOrEmail(nickNameOrEmail);
        if (ObjectUtil.isEmpty(info) || StrUtil.isBlank(info.getUserId())) {
            throw new TJNUException(TJNUConstants.USER_NOT_EXISTS);
        }
        if (!info.getPassword().equals(password)) {
            throw new TJNUException(TJNUConstants.USER_PASSWORD_ERROR);
        }
        String token = KeyFactory.genToken();
        info.setToken(token);

        //更新用户的最近登录时间和登录次数
        info.setLastLoginTime(new Date());
        info.setLoginTimes(Optional.ofNullable(info.getLoginTimes()).orElse(0L) + 1);
        Boolean updateResult = userInfoComponent.updateUserInfo(info);
        if (!updateResult) {
            throw new TJNUException(TJNUConstants.SYSTEM_ERROR);
        }

        //token保存到redis中
        String key = TJNUConstants.TOKEN_KEY_PREFIX + token + TJNUConstants.TOKEN_KEY_SUFIX;
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(info, userInfo, new String[]{"password"});
        Boolean saveRedis = redisDao.set(key, JSON.toJSONString(userInfo), TJNUConstants.TOKEN_EFFECTIVE_TIME);
        if (!saveRedis) {
            throw new TJNUException(TJNUConstants.SYSTEM_ERROR);
        }
        return info;
    }

    @Override
    public Boolean register(String code, UserInfo info) {
        String key = TJNUConstants.VERIFY_CODE_KEY_PREFIX + info.getEmail() + TJNUConstants.VERIFY_CODE_KEY_SUFIX;
        String realCode = redisDao.get(key);
        if (!realCode.equals(code)) {
            throw new TJNUException(TJNUConstants.VERIFY_CODE_ERROR);
        }
        Boolean result = userInfoComponent.saveUserInfo(info);
        return result;
    }

    @Override
    public Boolean logout(String token) {
        String key = TJNUConstants.TOKEN_KEY_PREFIX + token + TJNUConstants.TOKEN_KEY_SUFIX;
        if (!redisDao.exists(key)) {
            throw new TJNUException(TJNUConstants.USER_NOT_LOG_IN);
        }
        Boolean result = redisDao.del(key);
        return result;
    }

    @Override
    public Boolean emailVerify(String email) {
        String code = KeyFactory.genRandomNumber();
        //保存到redis中
        String key = TJNUConstants.VERIFY_CODE_KEY_PREFIX + email + TJNUConstants.VERIFY_CODE_KEY_SUFIX;
        Boolean saveRedis = redisDao.set(key, code, 120);
        if (!saveRedis) {
            throw new TJNUException(TJNUConstants.VERIFY_SAVE_FAILURE);
        }
        //发送到指定邮箱
        Boolean sendFlag = mailUtill.sendMail(email, code);
        return sendFlag;
    }
}
