package com.hyb.travel.service.impl;

import com.hyb.travel.dao.FavoriteDao;
import com.hyb.travel.dao.RouteDao;
import com.hyb.travel.dao.RouteImgDao;
import com.hyb.travel.dao.SellerDao;
import com.hyb.travel.dao.impl.FavoriteDaoImpl;
import com.hyb.travel.dao.impl.RouteDaoImpl;
import com.hyb.travel.dao.impl.RouteImgDaoImpl;
import com.hyb.travel.dao.impl.SellerDaoImpl;
import com.hyb.travel.domain.PageBean;
import com.hyb.travel.domain.Route;
import com.hyb.travel.domain.RouteImg;
import com.hyb.travel.domain.Seller;
import com.hyb.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findOne(String rid) {
        //1.根据id去route表中查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));

        //2.1根据route的id查询图片集合信息
        List<RouteImg> routeImgList = routeImgDao.findById(route.getRid());
        //2.2讲集合设置到route对象
        route.setRouteImgList(routeImgList);

        //3.根据route的sid（商家id）查询商家对象
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);

        //4.查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);

        return route;
    }
}
