package com.learn.health.service;

import com.learn.health.entity.CheckGroup;
import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;

import java.util.List;

/**
 * @Data 2022/9/11
 * @Time 22:53
 * @Author Yan Taixin
 */
public interface CheckGroupService {
    /**
     * 添加检查组
     * @param checkitemIds
     * @param checkGroup
     */
    void addCheckGroup(Integer[] checkitemIds, CheckGroup checkGroup);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 根据检查组id查询选中的检查项
     * @param id
     */
    List<Integer> echoById(Integer id);

    /**
     * 更新检查组
     * @param checkitemIds
     * @param checkGroup
     */
    void updateCheckGroup(Integer[] checkitemIds, CheckGroup checkGroup);

    /**
     * 根据id删除检查组
     * @param id
     */
    void deleteCheckGroup(Integer id);

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> queryAll();
}
