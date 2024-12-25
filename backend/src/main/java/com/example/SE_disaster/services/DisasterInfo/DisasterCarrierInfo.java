package com.example.SE_disaster.services.DisasterInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.mappers.CarrierTypeMapper;
import com.example.SE_disaster.models.CarrierType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class DisasterCarrierInfo
{
    public String carrier_name;
    @Autowired
    private CarrierTypeMapper carrierTypeMapper;
    
    public DisasterCarrierInfo(String carrier_name)
    {
        this.carrier_name = carrier_name;
    }
    
    public String encoder()
    {
        QueryWrapper<CarrierType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cname", carrier_name);
        return carrierTypeMapper.selectOne(queryWrapper).cid;
    }
    
    public void decoder(String code)
    {
        CarrierType carrierType = carrierTypeMapper.selectById(code);
        this.carrier_name = carrierType.cname;
    }
}
