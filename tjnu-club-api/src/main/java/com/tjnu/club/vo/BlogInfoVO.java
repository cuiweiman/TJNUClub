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

    private Byte blogContent;

    private String categoryId;

    private String topBlog;

    private String parentBlogId;

    private String userId;

    private Integer readTimes;

    private Integer zanTimes;

    private Long isDeleted;

}
