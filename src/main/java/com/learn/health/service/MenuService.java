package com.learn.health.service;


import com.learn.health.entity.Menu;

import java.util.List;

/**
 * @Data 2022/12/20
 * @Time 17:12
 * @Author Yan Taixin
 */
public interface MenuService {
    List<Menu> getMenusByCurrentUser(String username);
}
