package com.learn.health.mapper;

import com.learn.health.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Data 2022/10/11
 * @Time 22:18
 * @Author Yan Taixin
 */
@Mapper
public interface UserManner {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User loadUserByUsername(String username);

    /**
     * 注册用户
     * @param user
     */
    void register(User user);

    /**
     * 关联角色表
     * @param user
     */
    void correlationRole(User user);

    /**
     * 关联会员表
     * @param user
     */
    void correlationMember(User user);

    /**
     * 根据用户id查询mid
     * @param uid
     * @return
     */
    Long queryMidByUid(Long uid);
}
