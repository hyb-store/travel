package com.hyb.travel.dao;

import com.hyb.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    /**
     * 根据route的id查询图片
     * @param rid
     * @return
     */
    public List<RouteImg> findById(int rid);
}
