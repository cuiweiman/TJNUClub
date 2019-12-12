package com.tjnu.club.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BlogInfoVO implements Serializable {

    private Long id;

    private Long gmtCreate;

    private Long gmtModified;

    private String blogId;

    private String blogName;

    private String blogContent;

    private String categoryId;

    private Integer topBlog;

    private String parentBlogId;

    private String userId;

    private Integer readTimes;

    private Integer zanTimes;

    private Long isDeleted;

}
