package com.learn.health.controller;

import com.learn.health.entity.Result;
import com.learn.health.entity.User;
import com.learn.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Data 2022/12/12
 * @Time 22:57
 * @Author Yan Taixin
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/checkuser")
    public Result checkUser(String username){
        User user = null;
        try{
            user = userService.checkUser(username);
            if(user != null) return new Result(true,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false,null);
    }

    @PostMapping("/doRegister")
    public Result register(@RequestBody Map<String,String> map){
        try{
            User user = new User();
            user.setUsername(map.get("username"));
            user.setPassword(passwordEncoder.encode(map.get("password")));
            userService.register(user);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,null);
        }
        return new Result(true,null);
    }
}
