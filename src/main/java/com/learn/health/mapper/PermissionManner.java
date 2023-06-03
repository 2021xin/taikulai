package com.learn.health.mapper;

import com.learn.health.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Data 2022/11/5
 * @Time 20:56
 * @Author Yan Taixin
 */
@Mapper
public interface PermissionManner {
    List<Permission> getAllPermission();
}
