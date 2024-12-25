package com.example.SE_disaster.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.mappers.DisasterDataMapper;
import com.example.SE_disaster.mappers.RegionCodeMapper;
import com.example.SE_disaster.models.DisasterData;
import com.example.SE_disaster.models.RegionCode;
import com.example.SE_disaster.models.UploadFormat;
import com.example.SE_disaster.services.CodeService;
import com.example.SE_disaster.services.DisasterInfo.DisasterSpaceTimeInfo.DisasterSpaceInfo;
import com.example.SE_disaster.services.FileSystemService;
import com.example.SE_disaster.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/info")
public class InformationController {
    @Autowired
    private DisasterDataMapper disasterDataMapper;
    @Autowired
    private DisasterSpaceInfo disasterSpaceInfo;
    @Autowired
    private RegionCodeMapper regionCodeMapper;

    private static DisasterData getDisasterData(UploadFormat code, String res_code, String description) {
        DisasterData disasterData = new DisasterData();
        disasterData.setCode(res_code);
        disasterData.setLocation(
                String.join(" ",
                        code.province , code.city,
                        code.county , code.town , code.village)
        );
        disasterData.setDate(code.year + " " + code.month + " " + code.day);
        disasterData.setDescription(description);
        disasterData.setCarrier(code.carrier);
        disasterData.setCategory(code.parentcate + " " + code.childcate);
        disasterData.setOrigin(code.parento + " " + code.childo);
        disasterData.setLabel(code.parentl + " " + code.childl);
        return disasterData;
    }

    @PostMapping("/upload")
    public String uploadInformation(@RequestParam("code") UploadFormat code,
                                    @RequestParam("data") Object data) throws IOException {
        CodeService codeService = new CodeService();
        String res_code =
                codeService.encoder(code.province, code.city, code.county, code.town, code.village,
                code.year, code.month, code.day, code.hour, code.minute, code.second,
                code.parento, code.childo, code.carrier, code.parentcate, code.childcate, code.parentl, code. childl);

        { // check if instance with the same code exists
            QueryWrapper<DisasterData> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("code", res_code);
            if (disasterDataMapper.exists(queryWrapper)) {
                return ResponseUtil.respond().setCode(200)
                        .setMessage("Existing Data has the same code")
                        .json();
            }
        }

        String description;
        ArrayList<String> otherCarrier = new ArrayList<>(Arrays.asList("图像", "音频", "视频", "其他"));
        if(code.carrier == "文字") {
            description = data.toString();
        }
        else if (otherCarrier.contains(code.carrier)) {
            MultipartFile file = data instanceof MultipartFile ? (MultipartFile) data : null;
            String[] nameContent = null;
            if (file != null) {
                nameContent = file.getName().split("\\.");
            }
            String filepath = "/" + res_code +
                    (nameContent.length != 0 ? "." + nameContent[nameContent.length - 1] : "");
            FileSystemService.writeFile(filepath, file.getBytes());
            description = filepath;
        } else {
            return ResponseUtil.respond().setCode(200).setMessage("Unknown data carrier").json();
        }
        DisasterData disasterData = getDisasterData(code, res_code, description);
        disasterDataMapper.insert(disasterData);
        return ResponseUtil.respond().setCode(200).setMessage("Uploading data successful").json();
    }


    @GetMapping("/province")
    public String getAllProvinces() throws IOException {
        QueryWrapper<RegionCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT province");
        var list = regionCodeMapper.selectObjs(queryWrapper);
        list = list.stream().distinct().toList();
        System.out.println("province list: " + list);
        return ResponseUtil.respond().setCode(200).setMessage("Get provinces success").json();
    }

    @GetMapping("/city")
    public String getAllCities() throws IOException {
        return ResponseUtil.respond().setCode(200).setMessage("Get cities success").json();
    }

    @GetMapping("/county")
    public String getAllCounties() throws IOException {
        return ResponseUtil.respond().setCode(200).setMessage("Get counties success").json();
    }

    @GetMapping("/town")
    public String getAllTowns() throws IOException {
        return ResponseUtil.respond().setCode(200).setMessage("Get towns success").json();
    }

    @GetMapping("/village")
    public String getAllVillages() throws IOException {
        return ResponseUtil.respond().setCode(200).setMessage("Get towns success").json();
    }


}
