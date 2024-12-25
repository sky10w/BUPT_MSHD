package com.example.SE_disaster.services.DisasterInfo.DisasterSpaceTimeInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.mappers.RegionCodeMapper;
import com.example.SE_disaster.models.RegionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class DisasterSpaceInfo
{
    public String province;
    public String city;
    public String county;
    public String town;
    public String village;
    @Autowired
    private RegionCodeMapper regionCodeMapper;
    
    public DisasterSpaceInfo(String province, String city, String county, String town, String village)
    {
        this.province = province;
        this.city = city;
        this.county = county;
        this.town = town;
        this.village = village;
    }

    public String encode() {
        QueryWrapper<RegionCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("province", province).eq("city", city).eq("county", county).eq("town", town).eq("village", village);
        return regionCodeMapper.selectOne(queryWrapper).code;
    }

    public void decode(String code)
    {
        RegionCode region_code = regionCodeMapper.selectById(code);
        province = region_code.province;
        city = region_code.city;
        county = region_code.county;
        town = region_code.town;
        village = region_code.village;
    }
}
