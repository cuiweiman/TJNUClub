package com.tjnu.club.info;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CategoryAllInfo {

    private Date gmtCreate;

    private Date gmtModified;

    //父版块ID
    private String categoryId;

    //父版块名
    private String categoryName;

    //子版块列表
    private List<CategoryInfo> child;

}
