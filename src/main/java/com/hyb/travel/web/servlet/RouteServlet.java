package com.hyb.travel.web.servlet;

import com.hyb.travel.domain.PageBean;
import com.hyb.travel.domain.Route;
import com.hyb.travel.service.RouteService;
import com.hyb.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();
    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        //接受rname线路名称
        String rname = request.getParameter("rname");

        //解决tomcat7乱码问题
        // 1.pom中配置<uriEncoding>UTF-8</uriEncoding>
        // 2.rname = new String(rname.getBytes("iso-8859-1"),"utf-8");

        //2.处理参数
        int cid = 0;//类别id
        if(cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }

        //3. 调用service查询PageBean对象
//        System.out.println("cid:"+cid+" "+"currentPage:"+currentPage+" "+"pageSize:"+pageSize+" "+ "rname:"+rname);
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);
        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }


}
