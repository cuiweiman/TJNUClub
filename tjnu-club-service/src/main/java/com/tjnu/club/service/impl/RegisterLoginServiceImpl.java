package com.tjnu.club.service.impl;

import com.tjnu.club.api.RegisterLoginService;
import com.tjnu.club.component.RegisterLoginComponent;
import com.tjnu.club.constants.TJNUService;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.service.util.ServiceTransferUtil;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class RegisterLoginServiceImpl extends TJNUService implements RegisterLoginService {

    @Resource
    private RegisterLoginComponent registerLoginComponent;

    @Override
    public ResultVO<UserInfoVO> login(String nickNameOrEmail, String password) {
        super.notNull("用户名/邮箱", nickNameOrEmail).notNull("密码", password);
        try {
            UserInfo info = registerLoginComponent.login(nickNameOrEmail, password);
            UserInfoVO vo = ServiceTransferUtil.userInfo2VO(info);
            return new ResultVO<>(vo);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<Boolean> register(UserInfoVO userInfoVO) {
        checkParam(userInfoVO);
        try {
            String code = userInfoVO.getCode();
            UserInfo info = ServiceTransferUtil.userVo2Info(userInfoVO);
            Boolean result = registerLoginComponent.register(code, info);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<Boolean> logout(String token) {
        super.notBlank("用户凭证",token);
        try {
            Boolean result = registerLoginComponent.logout(token);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<Boolean> emailVerify(String email) {
        super.notBlank("邮箱地址", email);
        try {
            Boolean result = registerLoginComponent.emailVerify(email);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    //参数校验
    private void checkParam(UserInfoVO userInfoVO) {
        super.notNull("用户信息", userInfoVO).notBlank("昵称", userInfoVO.getNickName())
                .notBlank("邮箱", userInfoVO.getEmail()).notBlank("密码", userInfoVO.getPassword())
                //.notBlank("专业", userInfoVO.getMajor()).notBlank("学院", userInfoVO.getCollege())
                //.notBlank("学校", userInfoVO.getUniversity()).notBlank("年级", userInfoVO.getGrade())
                .notBlank("验证码", userInfoVO.getCode());
    }

}
