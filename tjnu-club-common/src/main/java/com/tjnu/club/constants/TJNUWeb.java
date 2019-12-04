package com.tjnu.club.constants;

import cn.hutool.core.util.StrUtil;
import com.tjnu.club.exceptions.TJNUException;

import java.util.List;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/2 19:55
* @Description: 父类，定义通用校验方法
*/
public class TJNUWeb {

    public TJNUWeb() {
    }

    public TJNUWeb notBlank(String paramName, String value) {
        if (StrUtil.isBlank(value) || StrUtil.isEmpty(value)) {
            throw new TJNUException(TJNUConstants.PARAM_ERROR.getCode(), paramName + " 输入错误");
        } else {
            return this;
        }
    }

    public TJNUWeb notNull(String paramName, Object value) {
        if (null == value) {
            throw new TJNUException(TJNUConstants.PARAM_ERROR.getCode(), paramName + " 输入错误");
        } else {
            return this;
        }
    }

    public TJNUWeb checkLength(String paramName, Object value, Integer minLength, Integer maxLength) {
        if (value.toString().length() < minLength) {
            throw new TJNUException(TJNUConstants.PARAM_ERROR.getCode(), paramName + " 输入的长度太短，请重新输入");
        } else if (value.toString().length() > maxLength) {
            throw new TJNUException(TJNUConstants.PARAM_ERROR.getCode(), paramName + " 输入的长度太长，请重新输入");
        } else {
            return this;
        }
    }

    public <T> TJNUWeb checkEnums(String paramName, T value, List<T> enums) {
        if (!enums.contains(value)) {
            throw new TJNUException(TJNUConstants.PARAM_ERROR.getCode(), paramName + " 值不符合规定");
        } else {
            return this;
        }
    }

    public TJNUWeb checkPage(Integer currentPage, Integer pageSize) {
        this.notNull("currentPage", currentPage);
        this.notNull("pageSize", pageSize);
        if (currentPage >= 1 && pageSize >= 1 && pageSize <= 100) {
            return this;
        } else {
            throw new TJNUException(TJNUConstants.PARAM_ERROR.getCode(), "分页参数错误");
        }
    }

    public TJNUWeb checkDate(Long begin, Long end) {
        if (begin != null && end != null) {
            if (begin >= end) {
                throw new TJNUException(TJNUConstants.PARAM_ERROR.getCode(), "开始时间不能大于截止时间");
            }
        }
        return this;
    }
}
