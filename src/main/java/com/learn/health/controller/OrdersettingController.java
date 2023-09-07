package com.learn.health.controller;

import com.learn.health.constant.MessageConstant;
import com.learn.health.entity.OrderSetting;
import com.learn.health.entity.Result;
import com.learn.health.service.OrdersettingService;
import com.learn.health.tool.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Data 2022/9/16
 * @Time 17:25
 * @Author Yan Taixin
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin/ordersetting")
public class OrdersettingController {
    @Autowired
    private OrdersettingService ordersettingService;

    /**
     * 批量导入预约设置数据
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile")MultipartFile multipartFile){
        try {
            // 使用POI解析数据
            List<String[]> excel = POIUtils.readExcel(multipartFile);
            List<OrderSetting> data = new ArrayList<>();
            for (String[] row : excel) {
                data.add(new OrderSetting(new Date(row[0]),Integer.parseInt(row[1])));
            }

            // 把批量导入的预约设置存入数据库
            ordersettingService.add(data);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，文件上传失败
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        // 文件上传成功
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    /**
     * 获取预约数据
     * @param date
     * @return
     */
    @RequestMapping("/getOrderData")
    public Result getOrderData(@RequestParam("date") String date){
        List<Map<String,Object>> orderSettings;
        try {
            orderSettings = ordersettingService.getOrderData(date);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，获取预约数据失败
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
        // 获取预约数据成功
        return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS,orderSettings);
    }

    /**
     * 修改可预约人数
     * @param orderSetting
     * @return
     */
    @RequestMapping("/updateOrderNumber")
    public Result updateOrderNumber(@RequestBody OrderSetting orderSetting){
        try {
            ordersettingService.updateOrderNumber(orderSetting);
        } catch (Exception e) {
            e.printStackTrace();
            // 抛出异常则，修改预约数据失败
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
        // 修改预约数据成功
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
    }
}
