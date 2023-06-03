package com.learn.health.mapper;

import com.learn.health.entity.CheckGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Data 2022/9/11
 * @Time 22:54
 * @Author Yan Taixin
 */
@Mapper
public interface CheckGroupManner {
    /**
     * 向t_checkgroup表插入数据
     * @param checkGroup
     */
    void addCheckGroup(CheckGroup checkGroup);

    /**
     * 向t_checkgroup_checkitem表插入多对多的关系
     * @param checkGroupId
     * @param checkitemIds
     */
    void addCheckGroupAndCheckItem(@Param("checkGroupId") Integer checkGroupId,@Param("checkitemIds") Integer[] checkitemIds);

    /**
     * 查询检查项
     * @param value
     * @return
     */
    List<CheckGroup> queryContain(String value);

    /**
     * 根据检查组id查询选中的检查项
     * @param id
     * @return
     */
    List<Integer> echoById(Integer id);

    /**
     * 更新检查组
     * @param checkGroup
     */
    void updateCheckGroup(CheckGroup checkGroup);

    /**
     * 清除检查组和检查项的关联
     * @param id
     */
    void clearAssociation(Integer id);

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

    /**
     * 根据套餐的id查询到CheckGroup
     * @param setmealId
     * @return
     */
    CheckGroup findCheckGroupById(Integer setmealId);
}
