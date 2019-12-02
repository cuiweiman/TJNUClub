package com.tjnu.club.constants;

import lombok.Data;

@Data
public class TJNUCode {

    private Integer code;

    private String msg;

    public TJNUCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
