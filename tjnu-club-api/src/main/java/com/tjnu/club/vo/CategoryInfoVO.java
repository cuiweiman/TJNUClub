package com.tjnu.club.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryInfoVO implements Serializable {

    private Long id;

    private Long gmtCreate;

    private Long gmtModified;

    //版块ID
    private String categoryId;

    //版块名
    private String categoryName;

    //是否是父版块。0-是，1-否
    private Integer topCategory;

    // 父版块ID
    private String parentCategoryId;

    // 属于该板块的帖子的数量
    private Integer blogNum;

    //逻辑删除。0-未删除，时间戳-删除。
    private Long isDeleted;

}
