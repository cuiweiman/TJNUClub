package com.tjnu.club.vo;

import lombok.Data;

@Data
public class TJNUCode {

    private Integer code;

    private String msg;

    public TJNUCode(){

    }

    public TJNUCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
