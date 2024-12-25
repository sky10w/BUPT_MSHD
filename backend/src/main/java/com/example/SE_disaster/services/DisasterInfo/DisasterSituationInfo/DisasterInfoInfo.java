package com.example.SE_disaster.services.DisasterInfo.DisasterSituationInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.mappers.DisasterInfoTypeMapper;
import com.example.SE_disaster.models.DisasterLabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class DisasterInfoInfo
{
    public String parentl = "";
    public String childl = "";
    @Autowired
    private DisasterInfoTypeMapper disasterInfoTypeMapper;
    
    public DisasterInfoInfo(String parentl, String childl)
    {
        this.parentl = parentl;
        this.childl = childl;
    }
    
    public String encoder()
    {
        QueryWrapper<DisasterLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentl", parentl);
        queryWrapper.eq("childl", childl);
        return disasterInfoTypeMapper.selectOne(queryWrapper).childid;
    }
    
    public void decoder(String code)
    {
        DisasterLabel disasterLabel = disasterInfoTypeMapper.selectById(code);
        this.parentl = disasterLabel.parentl;
        this.childl = disasterLabel.childl;
    }
}
