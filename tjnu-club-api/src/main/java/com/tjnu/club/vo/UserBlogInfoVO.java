package com.tjnu.club.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBlogInfoVO implements Serializable {

    private UserInfoVO userInfoVO;

    private BlogInfoVO blogInfoVO;

}
