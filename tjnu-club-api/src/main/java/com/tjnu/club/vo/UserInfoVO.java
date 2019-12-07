package com.tjnu.club.vo;

import lombok.Data;

import java.io.Serializable;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/5 9:50
* @Description: 用户信息
*/
@Data
public class UserInfoVO implements Serializable {

    //创建时间
    private Long gmtCreate;

    //修改时间
    private Long gmtModified;

    //用户标识符
    private String userId;

    //用户头像
    private String userImg;

    //真实姓名
    private String userName;

    //昵称/用户名
    private String nickName;

    //密码
    private String password;

    //邮箱
    private String email;

    //账号状态：0正常，1封号
    private Integer status;

    //年级
    private String grade;

    //专业
    private String major;

    //学院
    private String college;

    //学校
    private String university;

    //最后一次登陆时间
    private Long lastLoginTime;

    //登陆次数
    private Long loginTimes;

    //个人介绍、说明
    private String description;

    //登录令牌，有效期2小时
    private String token;

    //邮箱验证码
    private String code;

}
