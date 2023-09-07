package com.learn.health.service;

import com.learn.health.entity.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @Data 2022/11/5
 * @Time 20:43
 * @Author Yan Taixin
 */
@Service
//public interface UserService extends UserDetailsService {
public interface UserService {
    /**
     * 检查用户是否存在
     * @param username
     * @return
     */
    User checkUser(String username);

    /**
     * 注册用户
     * @param user
     */
    void register(User user);
}
