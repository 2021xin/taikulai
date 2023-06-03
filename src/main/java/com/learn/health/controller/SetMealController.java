package com.learn.health.controller;

import com.learn.health.constant.MessageConstant;
import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;
import com.learn.health.entity.Result;
import com.learn.health.entity.Setmeal;
import com.learn.health.service.OrderService;
import com.learn.health.service.SetMealService;
import com.learn.health.tool.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

/**
 * @Data 2022/9/13
 * @Time 21:20
 * @Author Yan Taixin
 */
@RestController
public class SetMealController {
    @Autowired
    private SetMealService setMealService;

    /**
     * 把图片上传到七牛云
     * @param imgFile
     * @return
     */
    @RequestMapping("/admin/setmeal/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        // 获取原始文件名
        String originalFilename = imgFile.getOriginalFilename();
        // 生成文件名
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        try{
            // 把图片上传到七牛云上
            QiniuUtils.upload(imgFile.getBytes(),newFileName);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,newFileName);
    }

    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @RequestMapping("/admin/setmeal/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try{
            setMealService.addSetMeal(setmeal,checkgroupIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/setmeal/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setMealService.findPage(queryPageBean);
    }

    /**
     * 删除套餐
     * @param id
     * @return
     */
    @RequestMapping("/admin/setmeal/delete")
    public Result delete(Integer id){
        try{
            setMealService.deleteSetMeal(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }
}
