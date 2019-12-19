package com.tjnu.club.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.tjnu.club.enums.TJNUResultEnum;
import com.tjnu.club.exceptions.TJNUException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class QiniuUtil {

    private static String accessKey = "vnImaAMCMs9vWxu1UhnMgQ9O7AtJOPIqol090qNw";

    private static String secretKey = "gYLb4zyr19s5kC-M8holbvknWI-GmQNCnazChTZi";

    private static String bucketName = "tjnu-club-img";

    private static String domain = "http://img.hanxiaofei.club";

    private static Auth auth = Auth.create(accessKey, secretKey);

    private static Configuration cfg = new Configuration(Zone.zone1());

    private static UploadManager uploadManager = new UploadManager(cfg);

    private static List<String> imgTypeList = Arrays.asList("png", "jpg", "jpeg");

    private static Long expireInSeconds = 3600L;


    /**
     * 上传图片 到 七牛云 OSS中
     *
     * @param image
     * @return
     */
    public static String uploadImg(MultipartFile image) {
        try {
            String originalName = image.getOriginalFilename();
            int dotIndex = originalName.lastIndexOf(".");
            if (dotIndex < 0) {
                throw new TJNUException(TJNUResultEnum.UPLOAD_IMG_NAME_INVALID);
            }
            String fileExt = originalName.substring(dotIndex + 1).toLowerCase();
            if (!imgTypeList.contains(fileExt)) {
                throw new TJNUException(TJNUResultEnum.UPLOAD_IMG_TYPE_INVALID);
            }

            String imgName = KeyFactory.genDateNo() + "." + fileExt;
            String upToken = auth.uploadToken(bucketName);
            Response res = uploadManager.put(image.getBytes(), imgName, upToken);
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                String key = (String) JSONObject.parseObject(res.bodyString()).get("key");
                return key;
            } else {
                log.error("七牛异常:" + res.bodyString());
                throw new TJNUException(TJNUResultEnum.UPLOAD_IMG_FAILURE);
            }
        } catch (IOException e) {
            log.error(e.getMessage() + e);
            throw new TJNUException(TJNUResultEnum.UPLOAD_IMG_FAILURE);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new TJNUException(TJNUResultEnum.UPLOAD_IMG_FAILURE.getCode(), e.getMessage());
        }
    }


    /**
     * 根据 key 获取图片 图片的 外链
     *
     * @param key
     * @return
     */
    public static String readUrl(String key) {
        if (StrUtil.isEmpty(key)){
            return "";
        }
        try {
            String encodedFileName = URLEncoder.encode(key, "utf-8").replace("+", "%20");
            String publicUrl = String.format("%s/%s", domain, encodedFileName);
            String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
            return finalUrl;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new TJNUException(TJNUResultEnum.DOWN_IMG_KEY_FAILURE);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new TJNUException(TJNUResultEnum.DOWN_IMG_FAILURE);
        }
    }

}
