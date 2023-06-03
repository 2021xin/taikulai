package com.learn.health.controller;

import com.learn.health.constant.MessageConstant;
import com.learn.health.entity.CheckGroup;
import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;
import com.learn.health.entity.Result;
import com.learn.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Data 2022/9/11
 * @Time 22:56
 * @Author Yan Taixin
 */
@RestController
@RequestMapping("/admin/checkgroup")
public class CheckGroupController {
    @Autowired
    private CheckGroupService checkGroupService;

    /**
     * 插入检查组
     * @param checkitemIds
     * @param checkGroup
     * @return
     */
    @RequestMapping("/add")
    public Result add(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.addCheckGroup(checkitemIds,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，添加失败
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        // 检查组添加成功
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return checkGroupService.findPage(queryPageBean);
    }

    /**
     * 根据检查组id查询选中的检查项
     * @param id
     * @return
     */
    @RequestMapping("/echoById")
    public Result echoById(Integer id){
        List<Integer> ids;
        try {
            ids = checkGroupService.echoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，查询失败
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        // 检查组查询成功
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,ids);
    }

    /**
     * 更新检查组
     * @param checkitemIds
     * @param checkGroup
     * @return
     */
    @RequestMapping("/update")
    public Result update(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.updateCheckGroup(checkitemIds,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，更新检查组失败
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        // 更新检查组成功
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * 根据id删除检查组
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkGroupService.deleteCheckGroup(id);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，删除失败
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        // 检查组删除成功
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    /**
     * 查询所有检查组
     * @return
     */
    @RequestMapping("/queryAll")
    public Result queryAll(){
        List<CheckGroup> checkGroups;
        try {
            checkGroups = checkGroupService.queryAll();
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，查询失败
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        // 查询成功
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroups);
    }
}
