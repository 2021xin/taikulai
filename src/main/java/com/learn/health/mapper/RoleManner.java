package com.learn.health.mapper;

import com.learn.health.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @Data 2022/10/11
 * @Time 22:37
 * @Author Yan Taixin
 */
@Mapper
public interface RoleManner {
    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    Set<Role> findUserById(Integer id);

    /**
     * 根据用户id查询角色
     * @param id
     * @return
     */
    List<Role> findRoleByUserId(Long id);

    /**
     * 根据菜单查询角色
     * @param id
     * @return
     */
    Role findRoleByPermissionId(Long id);
}
