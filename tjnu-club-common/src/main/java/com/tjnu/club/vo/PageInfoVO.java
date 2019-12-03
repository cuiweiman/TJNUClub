package com.tjnu.club.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageInfoVO<T> {

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
