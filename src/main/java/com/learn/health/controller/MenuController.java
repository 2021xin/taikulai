package com.learn.health.controller;

import com.learn.health.entity.Menu;
import com.learn.health.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Data 2022/12/20
 * @Time 19:52
 * @Author Yan Taixin
 */
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/api/menu")
    public List<Menu> menu(Authentication authentication) {
        return menuService.getMenusByCurrentUser(authentication.getName());
    }
}
