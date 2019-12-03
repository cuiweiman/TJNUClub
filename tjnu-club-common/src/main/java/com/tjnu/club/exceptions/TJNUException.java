package com.tjnu.club.exceptions;

import com.tjnu.club.constants.TJNUCode;
import lombok.Data;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/2 19:10
* @Description: 自定义异常类
*/
@Data
public class TJNUException extends RuntimeException {

    private Integer code;

    private String msg;

    public TJNUException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public TJNUException(TJNUCode tjnuCode){
        this.code = tjnuCode.getCode();
        this.msg = tjnuCode.getMsg();
    }

    public TJNUException(Integer code, String msg, Throwable cause) {
        super(cause);
        this.code = code;
        this.msg= msg;
    }

}
