package com.tjnu.club.info;

import lombok.Data;

import java.util.Date;

@Data
public class ApiMetaInfo {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String apiName;

    private String className;

    private String methodName;

    private String paramType;

    private String paramsMeta;

    private Integer needLogin;

    private Integer isRecord;

    private Long isDeleted;

}
