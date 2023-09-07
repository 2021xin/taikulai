package com.learn.health.service;

import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;
import com.learn.health.entity.Setmeal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Data 2022/9/13
 * @Time 21:21
 * @Author Yan Taixin
 */
public interface SetMealService {
    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void addSetMeal(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 获取套餐
     * @return
     */
    List<Setmeal> getSetmeal();

    /**
     * 查询套餐详情
     * @param id
     * @return
     */
    Setmeal findById(Integer id);

    /**
     * 删除套餐
     * @param id
     */
    void deleteSetMeal(Integer id);

    /**
     * 统计套餐名和预约成功的套餐数量
     * @return
     */
    ArrayList<Map<String, Object>> findSetmealCount();

    /**
     * 编辑菜单
     * @param setmeal
     * @param checkgroupIds
     */
    void editSetMeal(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 回显检查组
     * @param id
     * @return
     */
    List<Integer> echoById(Integer id);
}
