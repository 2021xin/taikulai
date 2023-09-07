package com.learn.health.controller;

import com.learn.health.constant.MessageConstant;
import com.learn.health.entity.Result;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Data 2022/11/26
 * @Time 11:34
 * @Author Yan Taixin
 */
@RestController
@RequestMapping("/user")
public class UserController {

//    @RequestMapping("getUsername")
//    public Result getUsername(Authentication authentication){
//        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,authentication.getName());
//    }
}
