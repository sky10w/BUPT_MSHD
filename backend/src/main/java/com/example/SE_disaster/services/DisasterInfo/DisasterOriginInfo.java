package com.example.SE_disaster.services.DisasterInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.mappers.DisasterOriginMapper;
import com.example.SE_disaster.models.DisasterOriginType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class DisasterOriginInfo
{
    public String originParent;
    public String originChild;
    @Autowired
    private DisasterOriginMapper disasterOriginMapper;
    
    public DisasterOriginInfo(String originChildCode, String originParentCode) {
        this.originParent = originParentCode;
        this.originChild = originChildCode;
    }
    
    public String encoder() {
        QueryWrapper<DisasterOriginType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parento", originParent);
        queryWrapper.eq("childo", originChild);
        return disasterOriginMapper.selectOne(queryWrapper).oid;
    }
    
    public void decoder(String code) {
        DisasterOriginType disasterOriginType = disasterOriginMapper.selectById(code);
        originParent = disasterOriginType.parent_o;
        originChild = disasterOriginType.child_o;
    }
}
