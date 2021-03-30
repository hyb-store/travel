package com.hyb.travel.service.impl;

import com.hyb.travel.dao.UserDao;
import com.hyb.travel.dao.impl.UserDaoImpl;
import com.hyb.travel.domain.User;
import com.hyb.travel.service.UserService;

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
        userDao.save(user);
        return true;
    }
}
