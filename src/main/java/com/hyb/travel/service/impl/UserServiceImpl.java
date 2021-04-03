package com.hyb.travel.service.impl;

import com.hyb.travel.dao.UserDao;
import com.hyb.travel.dao.impl.UserDaoImpl;
import com.hyb.travel.domain.User;
import com.hyb.travel.service.UserService;
import com.hyb.travel.util.MailUtils;
import com.hyb.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public Boolean regist(User user) {
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if(u != null) {
            return false;
        }
        //2.保存用户信息
        //2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //2.2设置激活状态
        user.setStatus("N");

        userDao.save(user);

        //3.激活邮件发送，邮件正文
        String content="<a href='http://localhost:8080/travel/activeUserServlet?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";

        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public Boolean active(String code) {
        //1.根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if(user != null) {
            //2.调用dao修改激活状态
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 登录方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
