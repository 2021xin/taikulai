package com.learn.health.mapper;

import com.learn.health.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Data 2022/9/14
 * @Time 20:22
 * @Author Yan Taixin
 */
@Mapper
public interface SetMealManner {
    /**
     * 新增套餐
     * @param setmeal
     */
    void addSerMeal(Setmeal setmeal);

    /**
     * 新增setmeal和checkgroup多对多关系
     * @param id
     * @param checkgroupIds
     */
    void addSetmealCheckgroup(@Param("setmealId") Integer id,@Param("checkgroupIds") Integer[] checkgroupIds);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    List<Setmeal> queryContain(String queryString);

    /**
     * 获取套餐
     * @return
     */
    List<Setmeal> getSetmeal();

    /**
     * 根据指定id查询到Setmeal
     * @param id
     * @return
     */
    Setmeal findById(Integer id);

    /**
     * 清除指定套餐id与检查组的关联关系
     * @param id
     */
    void clearAssociation(Integer id);

    /**
     * 根据id删除套餐
     * @param id
     */
    void deleteSetMeal(Integer id);

    /**
     * 查询套餐名及其预约数量
     * @return
     */
    ArrayList<Map<String, Object>> findSetmealCount();

    /**
     * 更新套餐
     * @param setmeal
     */
    void updateSetmeal(Setmeal setmeal);

    /**
     * 回显检查组
     * @param id
     * @return
     */
    List<Integer> echoById(Integer id);
}
