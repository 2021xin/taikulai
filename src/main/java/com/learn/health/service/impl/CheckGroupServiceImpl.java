package com.learn.health.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.health.entity.CheckGroup;
import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;
import com.learn.health.mapper.CheckGroupManner;
import com.learn.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Data 2022/9/11
 * @Time 22:53
 * @Author Yan Taixin
 */
@Service
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupManner groupManner;

    @Override
    public void addCheckGroup(Integer[] checkitemIds, CheckGroup checkGroup) {
        // 向t_checkgroup表插入数据
        groupManner.addCheckGroup(checkGroup);
        // 向t_checkgroup_checkitem表插入多对多的关系
        Integer checkGroupId = checkGroup.getId();
        if(checkitemIds != null && checkitemIds.length > 0){
            groupManner.addCheckGroupAndCheckItem(checkGroupId,checkitemIds);
        }
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 分页查询
        List<CheckGroup> checkGroups = groupManner.queryContain(queryPageBean.getQueryString());
        PageInfo<CheckGroup> checkItemPageInfo = new PageInfo<>(checkGroups);
        return new PageResult(checkItemPageInfo.getTotal(),checkGroups);
    }

    @Override
    public List<Integer> echoById(Integer id) {
        return groupManner.echoById(id);
    }

    @Override
    public void updateCheckGroup(Integer[] checkitemIds, CheckGroup checkGroup) {
        // 更新检查组的基本信息
        groupManner.updateCheckGroup(checkGroup);
        // 清除检查组和检查项的关联
        groupManner.clearAssociation(checkGroup.getId());
        // 重新建立关系
        if(checkitemIds != null && checkitemIds.length > 0){
            groupManner.addCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
        }
    }

    @Override
    public void deleteCheckGroup(Integer id) {
        // 清除检查组和检查项的关联
        groupManner.clearAssociation(id);
        // 根据id删除检查组
        groupManner.deleteCheckGroup(id);
    }

    @Override
    public List<CheckGroup> queryAll() {
        return groupManner.queryAll();
    }
}
