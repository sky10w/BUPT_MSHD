package com.example.SE_disaster.services.DisasterInfo.DisasterSituationInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.mappers.DisasterCategoryTypeMapper;
import com.example.SE_disaster.models.DisasterCategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Encoder;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DisasterCategoryInfo
{
    public String parentcate = "";
    public String childcate = "";
    @Autowired
    private DisasterCategoryTypeMapper disasterCategoryTypeMapper;
    
    public DisasterCategoryInfo(String parentcate, String childcate)
    {
        this.parentcate = parentcate;
        this.childcate = childcate;
    }
    
    public String encoder()
    {
        QueryWrapper<DisasterCategoryType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentcate", parentcate);
        queryWrapper.eq("childcate", childcate);
        return disasterCategoryTypeMapper.selectOne(queryWrapper).cate_id;
    }
    
    public void decoder(String code)
    {
        DisasterCategoryType disasterCategoryType = disasterCategoryTypeMapper.selectById(code);
        this.parentcate = disasterCategoryType.parent_cate;
        this.childcate = disasterCategoryType.child_cate;
    }
    
}
