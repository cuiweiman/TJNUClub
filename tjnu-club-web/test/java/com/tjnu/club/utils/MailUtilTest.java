package com.tjnu.club.utils;

import com.tjnu.club.TJNUClubTest;
import org.junit.Test;

import javax.annotation.Resource;
import javax.mail.MessagingException;

public class MailUtilTest extends TJNUClubTest {

    @Resource
    private MailUtil mailUtil;

    @Test
    public void sendMail() throws MessagingException {
        String email = "1287024833@qq.com";
        String verifyCode = "8890";
        mailUtil.sendMail(email,verifyCode);
    }
}