package com.tjnu.club.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/2 19:24
 * @Description: 创建密码等
 */
public class KeyFactory {

    public static final String number = "123456789";


    /**
     * 获取限定长度的数字字符串，不含0
     *
     * @return
     */
    public static String genRandomNumber(Integer length) {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (Integer i = 0; i < length; i++) {
            int num = random.nextInt(9);
            buf.append(number.charAt(num));
        }
        return buf.toString();
    }


    /**
     * 时间戳 + 2个随机数字
     *
     * @return
     */
    public static String genDefaultSerialNo() {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (Integer i = 0; i < 2; i++) {
            int num = random.nextInt(9);
            buf.append(number.charAt(num));
        }
        Long timestamp = new Date().getTime();
        return (timestamp + buf.toString());
    }


    /**
     * 获取 yyyyMMddHHmmss + 2个随机数字
     *
     * @return
     */
    public static String genDateNo() {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (Integer i = 0; i < 2; i++) {
            int num = random.nextInt(9);
            buf.append(number.charAt(num));
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(date);
        return (timestamp + buf.toString());
    }

}
