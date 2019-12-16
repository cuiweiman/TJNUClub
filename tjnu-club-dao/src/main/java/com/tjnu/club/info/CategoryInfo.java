package com.tjnu.club.info;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryInfo {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    //版块ID
    private String categoryId;

    //版块名
    private String categoryName;

    //是否是父版块。0-是，1-否
    private Integer topCategory;

    // 父版块ID
    private String parentCategoryId;

    //该版块下帖子数量
    private Integer blogNum;

    //逻辑删除。0-未删除，时间戳-删除。
    private Long isDeleted;

}
