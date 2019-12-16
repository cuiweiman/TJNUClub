package com.tjnu.club.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/16 11:18
* @Description: category all info vo。父、子版块，及子版块帖子数量
*/
@Data
public class CategoryAllInfoVO implements Serializable {

    private Long gmtCreate;

    private Long gmtModified;

    //父版块ID
    private String categoryId;

    //父版块名
    private String categoryName;

    //子版块列表
    private List<CategoryInfoVO> child;

}
