package com.tjnu.club.vo;

import com.tjnu.club.constants.TJNUCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public ResultVO(T data) {
        this.code = 200;
        this.msg = "成功";
        this.data = data;
    }

    public ResultVO(TJNUCode tjnuCode) {
        this.code = tjnuCode.getCode();
        this.msg = tjnuCode.getMsg();
    }

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


}
