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
import java.util.List;

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
        disasterData.setDate(code.year + " " + code.month + " " + code.day+" "+code.hour+" "+code.minute+" "+code.second);
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
    
    public DisasterData getDisasterDataByCode(String res_code) {
        return disasterDataMapper.selectById(res_code);
    }
    
    public void deleteDisasterDataByCode(String res_code) {
        disasterDataMapper.deleteById(res_code);
    }
    
    public void updateDisasterDataByCode(String res_code, DisasterData disasterData) {
        disasterDataMapper.updateById(disasterData);
    }
    
    public List<DisasterData> getDisasterDataByLocation(String province, String city, String county, String town, String village) {
        QueryWrapper<DisasterData> queryWrapper = new QueryWrapper<>();
        String condition = "";
        if(province != null && !province.isEmpty())
            condition += province;
        if(city != null && !city.isEmpty())
            condition += " " + city;
        if(county != null && !county.isEmpty())
            condition += " " + county;
        if(town != null && !town.isEmpty())
            condition += " " + town;
        if(village != null && !village.isEmpty())
            condition += " " + village;
        queryWrapper.like("location", condition);
        var res = disasterDataMapper.selectList(queryWrapper);
        System.out.println(res);
        return res;
    }
    
    public List<DisasterData> getAllLogs()
    {
        return disasterDataMapper.selectList(null);
    }
}
