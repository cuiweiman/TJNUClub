package com.tjnu.club.info;

import lombok.Data;

import java.util.Date;

@Data
public class ZanInfo {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String blogId;

    private String userId;

    private Long isDeleted;

}
