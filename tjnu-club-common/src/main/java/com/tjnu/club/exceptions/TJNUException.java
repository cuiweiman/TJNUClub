package com.tjnu.club.exceptions;

import com.tjnu.club.enums.TJNUResultEnum;
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

    public TJNUException() {
        this.code = TJNUResultEnum.SYSTEM_ERROR.getCode();
        this.msg= TJNUResultEnum.SYSTEM_ERROR.getMessage();
    }

    public TJNUException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public TJNUException(TJNUResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

}
