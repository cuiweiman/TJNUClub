package com.tjnu.club.info;

import lombok.Data;

import java.util.Date;

@Data
public class BlogInfo {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

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
