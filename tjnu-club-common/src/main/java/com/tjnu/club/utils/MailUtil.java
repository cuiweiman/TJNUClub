package com.tjnu.club.utils;

import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
@Slf4j
public class MailUtil {

    @Value("${tjnu.email.address}")
    private String FROM_MAIL;

    @Value("${tjnu.email.password}")
    private String PASSWORD;

    /**
     * 发送邮件
     *
     * @param address 收件人地址
     * @param code    自定义激活码
     * @throws MessagingException
     * @throws AddressException
     */
    public Boolean sendMail(String address, String code) {
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.163.com");    //设置服务器主机名
            prop.setProperty("mail.smtp.auth", "true");    //设置需要认证
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_MAIL, PASSWORD);//用户名和密码
                }
            };
            Session session = Session.getInstance(prop, auth);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_MAIL));
            message.setRecipient(RecipientType.TO, new InternetAddress(address));
            message.setSubject("TJNUClub致信");
            message.setContent("<a style='font-weight:bold;'>您正在进行安全邮箱验证操作，验证码</a><a style='color:blue;text-decoration:underline;'>" + code + "</a><a style='font-weight:bold;'>。（验证码告知他人将导致账号危险，请勿泄露）</a>", "text/html;charset=UTF-8");
            Transport.send(message);
            return Boolean.TRUE;
        } catch (MessagingException e) {
            log.error(e.getMessage(),e);
            throw new TJNUException(TJNUConstants.VERIFY_SEND_FAILURE);
        }
    }
}
