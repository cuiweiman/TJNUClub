package com.tjnu.club.api;

import cn.hutool.core.util.StrUtil;
import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.enums.TJNUResultEnum;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.redis.RedisDao;
import com.tjnu.club.utils.QiniuUtil;
import com.tjnu.club.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/tjnu")
public class FileController {

    @Resource
    private RedisDao redisDao;

    @PostMapping("/upload")
    public ResultVO<Object> upload(@RequestParam("TJNUToken") String token, @RequestParam MultipartFile image) {
        try {
            checkParam(token,image);
            String key = QiniuUtil.uploadImg(image);
            return new ResultVO<>(key);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    public void checkParam(String token,MultipartFile image){
        if (StrUtil.isEmpty(token)) {
            throw new TJNUException(TJNUResultEnum.SYSTEM_TOKEN_FAILURE);
        }
        if (image.isEmpty()) {
            throw new TJNUException(TJNUResultEnum.UPLOAD_IMG_EMPTY);
        }
        String key = TJNUConstants.TOKEN_KEY_PREFIX + token + TJNUConstants.TOKEN_KEY_SUFIX;
        Boolean isExists = redisDao.exists(key);
        if (!isExists) { // TOKEN不合法
            throw new TJNUException(TJNUResultEnum.USER_NOT_LOG_IN);
        }
    }

}
