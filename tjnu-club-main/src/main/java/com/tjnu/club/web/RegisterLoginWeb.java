package com.tjnu.club.web;

import cn.hutool.core.util.StrUtil;
import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.constants.TJNUWeb;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.api.RegisterLoginService;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/3 10:03
 * @Description: 全局的接口
 */
@Slf4j
@RestController
@RequestMapping("/global")
public class RegisterLoginWeb extends TJNUWeb {

    @Resource
    private RegisterLoginService registerLoginService;

    @PostMapping("/login")
    public ResultVO<UserInfoVO> login(String nickNameOrEmail, String password) {
        super.notNull("用户名/邮箱", nickNameOrEmail).notNull("密码", password);
        try {
            UserInfo info = registerLoginService.login(nickNameOrEmail, password);
            UserInfoVO vo = info2Vo(info);
            return new ResultVO<>(vo);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg());
        }
    }

    @PostMapping("/register")
    public ResultVO<Boolean> register(UserInfoVO userInfoVO) {
        checkParam(userInfoVO);
        try {
            String code = userInfoVO.getCode();
            UserInfo info = vo2Info(userInfoVO);
            Boolean result = registerLoginService.register(code, info);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg(), Boolean.FALSE);
        } catch (Exception e) {
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg(), Boolean.FALSE);
        }
    }

    @PostMapping("/logout")
    public ResultVO<Boolean> logout(String token, String userId) {
        super.notBlank("用户ID", userId);
        if (StrUtil.isEmpty(token)) { // TOKEN为空
            throw new TJNUException(TJNUConstants.PARAM_ERROR);
        }
        try {
            Boolean result = registerLoginService.logout(token, userId);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg(), Boolean.FALSE);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg(), Boolean.FALSE);
        }
    }

    @PostMapping("/email")
    public ResultVO<Boolean> emailVerify(String email) {
        super.notBlank("邮箱地址", email);
        try {
            Boolean result = registerLoginService.emailVerify(email);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg(), Boolean.FALSE);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg(), Boolean.FALSE);
        }
    }

    //参数校验
    private void checkParam(UserInfoVO userInfoVO) {
        super.notNull("用户信息", userInfoVO).notBlank("昵称", userInfoVO.getNickName())
                .notBlank("邮箱", userInfoVO.getEmail()).notBlank("密码", userInfoVO.getPassword())
                .notBlank("专业", userInfoVO.getMajor()).notBlank("学院", userInfoVO.getCollege())
                .notBlank("学校", userInfoVO.getUniversity()).notBlank("年级", userInfoVO.getGrade())
                .notNull("验证码", userInfoVO.getCode());
    }

    private UserInfo vo2Info(UserInfoVO vo) {
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(vo, info);
        return info;
    }

    private UserInfoVO info2Vo(UserInfo info) {
        if (ObjectUtils.isEmpty(info) || StrUtil.isEmpty(info.getUserId())) {
            return null;
        }
        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(info, vo);
        if (info.getGmtCreate() != null) {
            vo.setGmtCreate(info.getGmtCreate().getTime());
        }
        if (info.getGmtModified() != null) {
            vo.setGmtModified(info.getGmtModified().getTime());
        }
        if (info.getLastLoginTime() != null) {
            vo.setLastLoginTime(info.getLastLoginTime().getTime());
        }
        vo.setPassword(null);
        return vo;
    }

}
