package com.learn.health.service.impl;

import com.learn.health.entity.Permission;
import com.learn.health.mapper.PermissionManner;
import com.learn.health.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Data 2022/12/9
 * @Time 14:58
 * @Author Yan Taixin
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionManner permissionMapper;

    public List<Permission> getAllPermission(){
        return permissionMapper.getAllPermission();
    }
}
