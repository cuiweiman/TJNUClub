package com.tjnu.club.utils;

import com.github.pagehelper.Page;
import com.tjnu.club.vo.PageInfoVO;
import org.springframework.beans.BeanUtils;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/2 23:55
* @Description: PageInfoVO<info>对象 转换成 PageInfoVO<vo>
*/
public class PageInfoUtil {
    public static  <P, D> PageInfoVO<D> pageInfo2VO(PageInfoVO<P> pageInfoPO, Class<D> dClass) {
        Page<D> page = new Page<>(pageInfoPO.getPageNum(), pageInfoPO.getPageSize());
        page.setTotal(pageInfoPO.getTotal());
        for (P p : pageInfoPO.getData()) {
            D d = null;
            try {
                d = dClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(p, d);
            page.add(d);
        }
        return new PageInfoVO<>(page);
    }
}
