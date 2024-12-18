package com.example.SE_disaster.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SE_disaster.models.User;
import com.example.SE_disaster.services.UserService;
import com.example.SE_disaster.utils.JwtUtil;
import com.example.SE_disaster.utils.ResponseUtil;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin
    public String login(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseUtil.respond().setCode(400)
                    .setMessage("用户不存在")
                    .json();
        }
        // System.err.println(user.getPassword());
        // System.err.println(password);
        // System.err.println(user.getPassword() == password);
        if (!user.getPassword().equals(password)) {
            return ResponseUtil.respond().setCode(300)
                    .setMessage("用户名和密码不匹配")
                    .json();
        }
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", user.getUid());
        claims.put("username", user.getUsername());
        return ResponseUtil.respond().setCode(200)
                .setMessage("登陆成功")
                .setData(JwtUtil.genToken(claims))
                .json();
    }

    @PostMapping("/register")
    @CrossOrigin
    public String register(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseUtil.respond().setCode(401)
                    .setMessage("用户已存在")
                    .json();
        }
        userService.insertUser(username, password);
        return ResponseUtil.respond().setCode(200)
                .setMessage("注册成功")
                .json();
    }
}
