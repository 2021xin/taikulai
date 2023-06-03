package com.learn.health.controller;

import com.learn.health.constant.MessageConstant;
import com.learn.health.entity.CheckItem;
import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;
import com.learn.health.entity.Result;
import com.learn.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Data 2022/9/9
 * @Time 16:53
 * @Author Yan Taixin
 */
@RestController
@RequestMapping("/admin/checkitem")
public class CheckItemController {
    @Autowired
    private CheckItemService checkItemService;

    /**
     * 添加检查项
     * @param checkItem
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，添加失败
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        // 检查项添加成功
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/queryPage")
    public PageResult queryPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.queryPage(queryPageBean);
        return pageResult;
    }

    /**
     * 通过id删除检查项
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('CHECKITEM_DELETE')") // 权限验证
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            if(checkItemService.findCountByCheckItemId(id) > 0){
                return new Result(false,MessageConstant.DELETE_CHECKITEM_FATL_FROM_CHECKGROUP);
            }
            checkItemService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，添加失败
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        // 检查项添加成功
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    /**
     * 更新检查项
     * @param checkItem
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody CheckItem checkItem){
        try {
            checkItemService.updateCheckItem(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，添加失败
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        // 检查项添加成功
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/queryAll")
    public Result queryAll(){
        List<CheckItem> checkItems;
        try {
            checkItems = checkItemService.queryAll();
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，添加失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        // 检查项添加成功
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItems);
    }
}
