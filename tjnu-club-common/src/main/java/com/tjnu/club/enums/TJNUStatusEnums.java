package com.tjnu.club.enums;

import lombok.Getter;

import java.io.Serializable;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/10 11:35
* @Description: 状态信息
*/
@Getter
public enum TJNUStatusEnums implements Serializable {

    CATEGORY_TOP(0,"顶级版块"),
    CATEGORY_NOT_TOP(1,"子版块"),
    BLOG_TOP(0,"主贴"),
    BLOG_NOT_TOP(1,"回帖")
    ;

    private Integer code;

    private String message;

    TJNUStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
