package com.hyb.travel.dao;

import com.hyb.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    /**
     * 根据cid查询总记录数
     * @param cid
     * @return
     */
    public int findTotalCount(int cid, String rname);

    /**
     * 根据cid。start。pageSize查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    public List<Route> findByPage(int cid,int start,int pageSize, String rname);
}
