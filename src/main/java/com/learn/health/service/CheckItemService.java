package com.learn.health.service;

import com.learn.health.entity.CheckItem;
import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;

import java.util.List;

/**
 * @Data 2022/9/9
 * @Time 16:59
 * @Author Yan Taixin
 */
public interface CheckItemService {
    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult queryPage(QueryPageBean queryPageBean);

    /**
     * 通过id删除检查项
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 通过CheckItem的id查询在t_checkgroup_checkitem的记录数
     */
    Long findCountByCheckItemId(Integer id);

    /**
     * 更新检查项
     * @param checkItem
     */
    void updateCheckItem(CheckItem checkItem);


    List<CheckItem> queryAll();
}
