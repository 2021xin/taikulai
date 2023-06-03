package com.learn.health.service.impl;

import com.learn.health.entity.Menu;
import com.learn.health.entity.Role;
import com.learn.health.entity.User;
import com.learn.health.mapper.MenuManner;
import com.learn.health.mapper.RoleManner;
import com.learn.health.mapper.UserManner;
import com.learn.health.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Data 2022/12/20
 * @Time 17:15
 * @Author Yan Taixin
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private UserManner userManner;
    @Autowired
    private RoleManner roleManner;
    @Autowired
    private MenuManner menuManner;

    @Override
    public List<Menu> getMenusByCurrentUser(String username) {
        User user = userManner.loadUserByUsername(username);
        // 获取当前用户对应所有角色的id
        List<Long> rids = roleManner.findRoleByUserId(user.getId()).stream().map(Role::getId).collect(Collectors.toList());
        // 查询出这些角色对应的所有菜单项
        List<Long> mids = menuManner.findAllMidByRid(rids);
        List<Menu> menus = menuManner.findAllByMid(mids).stream().distinct().collect(Collectors.toList());
        handleMenus(menus);
        return menus;
    }

    private void handleMenus(List<Menu> menus) {
        for (Menu menu : menus) {
            List<Menu> children = menuManner.getAllByParentId(menu.getId());
            menu.setChildren(children);
        }

        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            if (menu.getParentId() != 0) {
                iterator.remove();
            }
        }
    }
}
