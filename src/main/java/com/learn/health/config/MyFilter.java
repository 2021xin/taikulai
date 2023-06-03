package com.learn.health.config;

import com.learn.health.entity.Permission;
import com.learn.health.entity.Role;
import com.learn.health.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @Data 2022/11/5
 * @Time 21:33
 * @Author Yan Taixin
 */
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String url = ((FilterInvocation) o).getRequestUrl();
        List<Permission> menus = permissionService.getAllPermission();
        for (Permission menu : menus) {
            if(pathMatcher.match(menu.getPattern(),url)){
                List<Role> roles = menu.getRoles();
                String[] rolesStr = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    rolesStr[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(rolesStr);
            }
        }
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
