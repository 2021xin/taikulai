package com.learn.health.controller;

import com.learn.health.entity.PageResult;
import com.learn.health.entity.QueryPageBean;
import com.learn.health.entity.Result;
//import com.learn.health.entity.User;
import com.learn.health.service.OrderService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Data 2022/12/12
 * @Time 20:26
 * @Author Yan Taixin
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 用户预约
     * @param map
     * @return
     */
//    @RequestMapping("/reservation")
//    public Result reservation(@RequestBody Map<String,Object> map, Authentication authentication){
//        Result order = null;
//        try{
//            map.put("setmealId",((Integer)map.get("setmealId")).toString());
//            order = orderService.order(map,authentication.getName());
//        }catch (Exception e){
//            e.printStackTrace();
//            return order;
//        }
//        return order;
//    }
    @RequestMapping("/reservation")
    public Result reservation(@RequestBody Map<String,Object> map){
        Result order = null;
        try{
            map.put("setmealId",((Integer)map.get("setmealId")).toString());
            order = orderService.order(map,"admin");
        }catch (Exception e){
            e.printStackTrace();
            return order;
        }
        return order;
    }

    /**
     * 用户完善信息预约
     * @param map
     * @return
     */
//    @RequestMapping("/improveInformation")
//    public Result improveInformation(@RequestBody Map<String,Object> map, Authentication authentication){
//        Result order = null;
//        try{
//            order = orderService.improveInformation(map,authentication.getName());
//        }catch (Exception e){
//            e.printStackTrace();
//            return order;
//        }
//        return order;
//    }

    /**
     * 查询用户是否是会员
     * @return
     */
//    @RequestMapping("/isMember")
//    public Result isMember(Authentication authentication){
//        Result order = null;
//        try{
//            order = orderService.isMember(authentication.getName());
//        }catch (Exception e){
//            e.printStackTrace();
//            return order;
//        }
//        return order;
//    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return orderService.findPage(queryPageBean,null);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
//    @RequestMapping("/userFindPage")
//    public PageResult userFindPage(@RequestBody QueryPageBean queryPageBean,Authentication authentication){
//        return orderService.findPage(queryPageBean,((User)authentication.getPrincipal()).getId());
//    }
}
