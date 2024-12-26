package com.example.SE_disaster.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.mappers.DisasterDataMapper;
import com.example.SE_disaster.models.DisasterData;
import com.example.SE_disaster.models.UploadFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

@Service
public class FileSystemService {
    @Value("${server.upload.path}")
    static private String uploadPath;
    
    @Autowired
    private DisasterDataMapper disasterDataMapper;

    static public byte[] readFile(String filePath) {
        try (FileInputStream fread = new FileInputStream(uploadPath + filePath)){
            return fread.readAllBytes();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void writeFile(String filePath, byte[] data) {
        try (FileOutputStream fwrite = new FileOutputStream(uploadPath + filePath)){
            fwrite.write(data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DisasterData getDisasterData(UploadFormat code, String res_code, String description) {
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
    
    public void saveDisasterData(DisasterData disasterData) {
        disasterDataMapper.insert(disasterData);
    }
    
    public boolean checkCode(String res_code) {
        QueryWrapper<DisasterData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", res_code);
        return disasterDataMapper.exists(queryWrapper);
    }
}
