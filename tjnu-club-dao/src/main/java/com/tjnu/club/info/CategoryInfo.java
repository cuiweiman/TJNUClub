package com.tjnu.club.info;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryInfo {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String categoryId;

    private String categoryName;

    private Integer topCategory;

    private String parentCategoryId;

    private Long isDeleted;

}
