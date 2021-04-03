package com.hyb.travel.service;

import com.hyb.travel.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    Boolean regist(User user);

    Boolean active(String code);

    User login(User user);
}
