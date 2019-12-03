package com.tjnu.club.web;

import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.redis.RedisDao;
import com.tjnu.club.service.UserInfoService;
import com.tjnu.club.utils.KeyFactory;
import com.tjnu.club.utils.MailUtil;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
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
    private UserInfoService userInfoService;

    @Resource
    private MailUtil mailUtill;

    @Resource
    private RedisDao redisDao;

    @PostMapping("/login")
    public ResultVO<UserInfoVO> login(String nickNameOrEmail, String password) {

        return null;
    }

    @PostMapping("/register")
    public ResultVO<Boolean> register(UserInfoVO userInfoVO) {
        // 校验邮箱验证码
        try {
            UserInfoWeb userInfoWeb = new UserInfoWeb();
            ResultVO<Boolean> result = userInfoWeb.saveUserInfo(userInfoVO);
            return result;
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg(), Boolean.FALSE);
        } catch (Exception e) {
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg(), Boolean.FALSE);
        }
    }

    @PostMapping("/logout")
    public ResultVO<Boolean> logout(String userId) {
        return null;
    }

    @PostMapping("/email")
    public ResultVO<Boolean> emailVerify(String email) {
        super.notBlank("邮箱地址", email);
        try {
            String code = KeyFactory.genRandomNumber();
            //保存到redis中
            String key = TJNUConstants.REDIS_EMAIL_CODE_KEY_PREFIX + email + TJNUConstants.REDIS_EMAIL_CODE_KEY_SUFIX;
            Boolean saveRedis = redisDao.set(key, email, 120);
            if(!saveRedis){
                throw new TJNUException(TJNUConstants.VERIFY_SAVE_FAILURE);
            }
            //发送到指定邮箱
            ResultVO<Boolean> resultVO = mailUtill.sendMail(email, code);
            return resultVO;
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg(), Boolean.FALSE);
        } catch (Exception e) {
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg(), Boolean.FALSE);
        }
    }


}
