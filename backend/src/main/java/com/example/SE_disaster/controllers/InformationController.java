package com.example.SE_disaster.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.mappers.DisasterDataMapper;
import com.example.SE_disaster.mappers.RegionCodeMapper;
import com.example.SE_disaster.models.DisasterData;
import com.example.SE_disaster.models.RegionCode;
import com.example.SE_disaster.models.UploadFormat;
import com.example.SE_disaster.services.CodeService;
import com.example.SE_disaster.services.CommonOPService;
import com.example.SE_disaster.services.FileSystemService;
import com.example.SE_disaster.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/info")
public class InformationController {
    @Autowired
    private DisasterDataMapper disasterDataMapper;
    @Autowired
    private RegionCodeMapper regionCodeMapper;
    @Autowired
    private CommonOPService commonOPService;

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
    public String uploadInformation(UploadFormat data) throws IOException {
        System.out.println(data);
        CodeService codeService = new CodeService();
        String res_code =
                codeService.encoder(data.province, data.city, data.county, data.town, data.village,
                data.year, data.month, data.day, data.hour, data.minute, data.second,
                data.parento, data.childo, data.carrier, data.parentcate, data.childcate, data.parentl, data. childl);

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
        if(data.carrier == "文字") {
            description = data.content.toString();
        }
        else if (otherCarrier.contains(data.carrier)) {
            MultipartFile file = data.content instanceof MultipartFile ? (MultipartFile) data.content : null;
            if(file == null) {
                return ResponseUtil.respond().setCode(200)
                        .setMessage("Fail to parse file")
                        .json();
            }
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
        DisasterData disasterData = getDisasterData(data, res_code, description);
        disasterDataMapper.insert(disasterData);
        return ResponseUtil.respond().setCode(200).setMessage("Uploading data successful").json();
    }

    @GetMapping("/province")
    public String getAllProvinces() throws IOException {
        var list = commonOPService.getColumnDistinct(regionCodeMapper, "province");
        List<String> res = new ArrayList<>();
        for (var item : list) {
            res.add(item.province);
        }
        return ResponseUtil.respond().setCode(200).setData(res).setMessage("Get provinces success").json();
    }

    @GetMapping("/city")
    public String getAllCities(@RequestParam("province") String province) throws IOException {
        QueryWrapper<RegionCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("province",province);
        var list = commonOPService.getColumnDistinct(regionCodeMapper, "city",queryWrapper);
        List<String> res = new ArrayList<>();
        for (var item : list) {
            res.add(item.city);
        }
        return ResponseUtil.respond().setCode(200).setData(res).setMessage("Get cities success").json();
    }

    @GetMapping("/county")
    public String getAllCounties(@RequestParam("city") String city) throws IOException {
        QueryWrapper<RegionCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city",city);
        var list = commonOPService.getColumnDistinct(regionCodeMapper, "county");
        List<String> res = new ArrayList<>();
        for (var item : list) {
            res.add(item.county);
        }
        return ResponseUtil.respond().setCode(200).setData(res).setMessage("Get counties success").json();
    }

    @GetMapping("/town")
    public String getAllTowns(@RequestParam("county") String county) throws IOException {
        QueryWrapper<RegionCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("county",county);
        var list = commonOPService.getColumnDistinct(regionCodeMapper, "town",queryWrapper);
        List<String> res = new ArrayList<>();
        for (var item : list) {
            res.add(item.town);
        }
        return ResponseUtil.respond().setCode(200).setData(res).setMessage("Get towns success").json();
    }

    @GetMapping("/village")
    public String getAllVillages(@RequestParam("town") String town) throws IOException {
        QueryWrapper<RegionCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("town",town);
        var list = commonOPService.getColumnDistinct(regionCodeMapper, "village",queryWrapper);
        List<String> res = new ArrayList<>();
        for (var item : list) {
            res.add(item.village);
        }
        return ResponseUtil.respond().setCode(200).setData(res).setMessage("Get towns success").json();
    }


}
