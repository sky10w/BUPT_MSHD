package com.example.SE_disaster.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SE_disaster.mappers.DisasterDataMapper;
import com.example.SE_disaster.mappers.RegionCodeMapper;
import com.example.SE_disaster.models.DisasterData;
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
import java.util.List;

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
    public String uploadInformation(@RequestParam("data") UploadFormat data) throws IOException {
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


    public <T> List<String> getColumnDistinct(BaseMapper mapper, String col) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT " + col);
        return mapper.selectList(queryWrapper);
    }

    @GetMapping("/province")
    public String getAllProvinces() throws IOException {
        var list = getColumnDistinct(regionCodeMapper, "province");
        return ResponseUtil.respond().setCode(200).setData(list).setMessage("Get provinces success").json();
    }

    @GetMapping("/city")
    public String getAllCities() throws IOException {
        var list = getColumnDistinct(regionCodeMapper, "city");
        return ResponseUtil.respond().setCode(200).setData(list).setMessage("Get cities success").json();
    }

    @GetMapping("/county")
    public String getAllCounties() throws IOException {
        var list = getColumnDistinct(regionCodeMapper, "county");
        return ResponseUtil.respond().setCode(200).setData(list).setMessage("Get counties success").json();
    }

    @GetMapping("/town")
    public String getAllTowns() throws IOException {
        var list = getColumnDistinct(regionCodeMapper, "town");
        return ResponseUtil.respond().setCode(200).setData(list).setMessage("Get towns success").json();
    }

    @GetMapping("/village")
    public String getAllVillages() throws IOException {
        var list = getColumnDistinct(regionCodeMapper, "village");
        return ResponseUtil.respond().setCode(200).setData(list).setMessage("Get towns success").json();
    }


}
