package com.learn.health.service.impl;

import com.learn.health.entity.User;
import com.learn.health.mapper.UserManner;
import com.learn.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Data 2022/12/9
 * @Time 14:59
 * @Author Yan Taixin
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserManner userManner;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userManner.loadUserByUsername(username);
//        if(user == null) throw new UsernameNotFoundException("用户名不存在！");
//        return user;
//    }

    @Override
    public User checkUser(String username) {
        return userManner.loadUserByUsername(username);
    }

    @Override
    public void register(User user) {
        userManner.register(user);
        userManner.correlationRole(user);
    }
}
