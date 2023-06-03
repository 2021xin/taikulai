package com.learn.health.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;
import com.learn.health.entity.Setmeal;
import com.learn.health.mapper.OrderManner;
import com.learn.health.mapper.SetMealManner;
import com.learn.health.mapper.UserManner;
import com.learn.health.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Data 2022/9/14
 * @Time 20:21
 * @Author Yan Taixin
 */
@Service
@Transactional
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealManner setMealManner;
    @Autowired
    private UserManner userManner;
    @Autowired
    private OrderManner orderManner;

    @Override
    public void addSetMeal(Setmeal setmeal, Integer[] checkgroupIds) {
        // 新增套餐
        setMealManner.addSerMeal(setmeal);
        // 新增setmeal与checkgroup的多对多关系
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            setMealManner.addSetmealCheckgroup(setmeal.getId(), checkgroupIds);
        }
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 分页查询
        List<Setmeal> checkGroups = setMealManner.queryContain(queryPageBean.getQueryString());
        PageInfo<Setmeal> checkItemPageInfo = new PageInfo<>(checkGroups);
        return new PageResult(checkItemPageInfo.getTotal(), checkGroups);
    }

    @Override
    public List<Setmeal> getSetmeal() {
        return setMealManner.getSetmeal();
    }

    @Override
    public Setmeal findById(Integer id) {
        // 根据id查询套餐
        return setMealManner.findById(id);
    }

    @Override
    public void deleteSetMeal(Integer id) {
        // 清除检查组和套餐的关联
        setMealManner.clearAssociation(id);
        // 根据id删除套餐
        setMealManner.deleteSetMeal(id);
    }

    @Override
    public ArrayList<Map<String, Object>> findSetmealCount() {
        return setMealManner.findSetmealCount();
    }
}
