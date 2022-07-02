package com.example.myblog.api.user;


import com.example.myblog.VO.LoginParam;
import com.example.myblog.VO.Result;
import com.example.myblog.entity.User;
import com.example.myblog.repository.UserRepository;
import com.example.myblog.service.LoginService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class LoginController {

    @Autowired
    LoginService loginService;


    @PostMapping("/login")
    public Result Login(@RequestBody LoginParam loginParam  ){
//        System.out.println("get");
//        LoginParam loginParam = new LoginParam("test", "123456");
//        return loginService.login(loginParam);
        return loginService.login(loginParam);
    }



}
