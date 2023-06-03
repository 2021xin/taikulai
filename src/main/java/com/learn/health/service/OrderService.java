package com.learn.health.service;

import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;
import com.learn.health.entity.Result;

import java.util.Map;

/**
 * @Data 2022/9/26
 * @Time 22:29
 * @Author Yan Taixin
 */
public interface OrderService {
    /**
     * 预约处理
     * @param map
     */
    Result order(Map map,String name) throws Exception;

    /**
     * 查询预约成功页面索要展示的数据
     * @param id
     * @return
     */
    Map findById(Integer id) throws Exception;

    /**
     * 分页查询
     * @param queryPageBean
     * @param uid
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean, Long uid);

    /**
     * 完善用户信息
     * @param map
     * @param name
     * @return
     */
    Result improveInformation(Map<String, Object> map, String name);

    /**
     * 查询用户是否是会员
     * @param name
     * @return
     */
    Result isMember(String name);
}
