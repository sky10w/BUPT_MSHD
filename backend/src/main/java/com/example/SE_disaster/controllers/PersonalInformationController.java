package com.example.SE_disaster.controllers;

import com.example.SE_disaster.services.PersonalInformationService;
import com.example.SE_disaster.utils.ResponseUtil;
import com.example.SE_disaster.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personalInformation")
public class PersonalInformationController
{
    @Autowired
    private PersonalInformationService personalInformationService;
    
    @PostMapping("/insert")
    public String insert(String phone_number,
                         String email,
                         String nick_name)
    {
        Long uid = UserUtil.getId();
        boolean result = personalInformationService.insertPersonalInformation(uid, phone_number, email, nick_name);
        if(result)
        {
            return ResponseUtil.respond()
                    .setCode(200)
                    .setMessage("插入信息成功")
                    .json();
        }
        else
        {
            return ResponseUtil.respond()
                    .setCode(400)
                    .setMessage("用户已存在")
                    .json();
        }
    }
    
    @PostMapping("/update")
    public String update(String phone_number,
                         String email,
                         String nick_name)
    {
        Long uid = UserUtil.getId();
        boolean result = personalInformationService.updatePersonalInformation(uid, phone_number, email, nick_name);
        if(result)
        {
            return ResponseUtil.respond()
                    .setCode(200)
                    .setMessage("更新信息成功")
                    .json();
        }
        else
        {
            return ResponseUtil.respond()
                    .setCode(400)
                    .setMessage("用户不存在")
                    .json();
        }
    }
    
    @GetMapping("/get")
    public String get()
    {
        Long uid = UserUtil.getId();
        var result = personalInformationService.getPersonalInformation(uid);
        if(result == null)
        {
            return ResponseUtil.respond()
                    .setCode(400)
                    .setMessage("用户不存在")
                    .json();
        }
        else
        {
            return ResponseUtil.respond()
                    .setCode(200)
                    .setMessage("获取信息成功")
                    .setData(result)
                    .json();
        }
    }
    
    @PostMapping("/changePassword")
    public String changePassword(String newPassword)
    {
        Long uid = UserUtil.getId();
        boolean result = personalInformationService.changePassword(uid, newPassword);
        if(result)
        {
            return ResponseUtil.respond()
                    .setCode(200)
                    .setMessage("修改密码成功")
                    .json();
        }
        else
        {
            return ResponseUtil.respond()
                    .setCode(400)
                    .setMessage("用户不存在")
                    .json();
        }
    }
}
