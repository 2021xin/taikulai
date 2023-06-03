package com.learn.health.mapper;

import com.learn.health.entity.CheckItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Data 2022/9/10
 * @Time 21:12
 * @Author Yan Taixin
 */
@Mapper
public interface CheckItemManner {
    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 查询检查项
     * @param value
     * @return
     */
    List<CheckItem> queryContain(String value);

    /**
     * 删除检查项
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 通过CheckItemId查询t_checkgroup_checkitem的关系映射是否存在
     * @param id
     * @return
     */
    Long findCountByCheckItemId(Integer id);

    /**
     * 更新检查项
     * @param checkItem
     */
    void updateCheckItem(CheckItem checkItem);

    /**
     * 查询所有的检查项
     * @return
     */
    List<CheckItem> queryAll();

    /**
     * 根据检查组的id查询到CheckItem
     * @param checkgroupId
     * @return
     */
    CheckItem findCheckItemById(Integer checkgroupId);
}
