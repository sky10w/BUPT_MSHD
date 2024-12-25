package com.example.SE_disaster.controllers;

import com.example.SE_disaster.services.CodeService;
import com.example.SE_disaster.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class CodeController
{
    @Autowired
    private CodeService codeService;
    
    @RequestMapping("/encode")
    public String encoder(String province,
                          String city,
                          String county,
                          String town,
                          String village,
                          String year,
                          String month,
                          String day,
                          String hour,
                          String minute,
                          String second,
                          String parento,
                          String childo,
                          String carrier,
                          String parentcate,
                          String childcate,
                          String parentl,
                          String childl
    )
    {
        String result = codeService.encoder(province,city,county,town,village,year,month,day,hour,minute,second,parento,childo,carrier,parentcate,childcate,parentl,childl);
        System.out.println(result);
        return ResponseUtil.respond()
                .setCode(200)
                .setMessage("success")
                .setData(result)
                .json();
    }
    
}
