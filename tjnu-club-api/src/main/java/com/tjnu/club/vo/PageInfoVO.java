package com.tjnu.club.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/4 9:10
* @Description: 分页信息
*/
@Data
public class PageInfoVO<T> implements Serializable {

    private Long count;

    private List<T> data;

    public PageInfoVO(){
        this.count = 0L;
        this.data = new ArrayList<>();
    }

    public PageInfoVO(Long count,List<T> data){
        this.count = count;
        this.data = data;
    }

}
