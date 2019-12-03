package com.tjnu.club.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.redis.RedisDao;
import com.tjnu.club.service.RegisterLoginService;
import com.tjnu.club.service.UserInfoService;
import com.tjnu.club.utils.KeyFactory;
import com.tjnu.club.utils.MailUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class RegisterLoginServiceImpl implements RegisterLoginService {

    @Resource
    private MailUtil mailUtill;

    @Resource
    private RedisDao redisDao;

    @Resource
    private UserInfoService userInfoService;

    @Override
    public UserInfo login(String nickNameOrEmail, String password) {
        UserInfo info = userInfoService.getUserInfoByNickNameOrEmail(nickNameOrEmail);
        if (ObjectUtil.isEmpty(info) || StrUtil.isBlank(info.getUserId())) {
            throw new TJNUException(TJNUConstants.USER_NOT_EXISTS);
        }
        if (!info.getPassword().equals(password)) {
            throw new TJNUException(TJNUConstants.USER_PASSWORD_ERROR);
        }
        String key = TJNUConstants.TOKEN_KEY_PREFIX + info.getUserId() + TJNUConstants.TOKEN_KEY_SUFIX;
        String token = KeyFactory.genToken();
        Boolean saveRedis = redisDao.set(key, token, TJNUConstants.TOKEN_EFFECTIVE_TIME);
        if (!saveRedis) {
            throw new TJNUException(TJNUConstants.SYSTEM_ERROR);
        }
        info.setLastLoginTime(new Date());
        info.setLoginTimes(info.getLoginTimes() + 1);
        userInfoService.updateUserInfo(info);
        info.setToken(token);
        return info;
    }

    @Override
    public Boolean register(String code, UserInfo info) {
        String key = TJNUConstants.VERIFY_CODE_KEY_PREFIX + info.getEmail() + TJNUConstants.VERIFY_CODE_KEY_SUFIX;
        String realCode = redisDao.get(key);
        if (!realCode.equals(code)) {
            throw new TJNUException(TJNUConstants.VERIFY_CODE_ERROR);
        }
        Boolean result = userInfoService.saveUserInfo(info);
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
