package com.tjnu.club.vo;

import com.tjnu.club.constants.TJNUCode;
import com.tjnu.club.exceptions.TJNUException;
import lombok.Data;

import java.io.Serializable;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/4 9:04
* @Description: 返回类型统一
*/
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

    public ResultVO(TJNUException e){
        this.code = e.getCode();
        this.msg = e.getMsg();
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
