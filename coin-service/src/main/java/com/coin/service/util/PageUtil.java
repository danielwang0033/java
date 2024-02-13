package com.coin.service.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtil {

    public static <P, V> PageInfo<V> pageInfo2PageRsp(PageInfo<P> pageInfo, List<V> list) {
        Page<V> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        page.setTotal(pageInfo.getTotal());
        PageInfo<V> pv = new PageInfo<>(page);
        pv.setList(list);
        return pv;
    }
}
