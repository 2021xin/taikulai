package com.learn.health.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.health.entity.CheckItem;
import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;
import com.learn.health.mapper.CheckItemManner;
import com.learn.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Data 2022/9/10
 * @Time 21:14
 * @Author Yan Taixin
 */
@Service
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemManner checkItemManner;

    @Override
    public void add(CheckItem checkItem) {
        checkItemManner.add(checkItem);
    }

    @Override
    public PageResult queryPage(QueryPageBean queryPageBean) {
        // 设定分页数据：开启分页功能。开启后，后面执行的 SELECT 语句会自动被附加 LIMIT 子句，
        // 而且会自动查询总记录数
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 分页查询
        List<CheckItem> checkItems = checkItemManner.queryContain(queryPageBean.getQueryString());
        PageInfo<CheckItem> checkItemPageInfo = new PageInfo<>(checkItems);
        return new PageResult(checkItemPageInfo.getTotal(),checkItems);
    }

    @Override
    public void deleteById(Integer id) {
        checkItemManner.deleteById(id);
    }

    @Override
    public Long findCountByCheckItemId(Integer id) {
        return checkItemManner.findCountByCheckItemId(id);
    }

    @Override
    public void updateCheckItem(CheckItem checkItem) {
        checkItemManner.updateCheckItem(checkItem);
    }

    @Override
    public List<CheckItem>  queryAll() {
        return checkItemManner.queryAll();
    }
}
