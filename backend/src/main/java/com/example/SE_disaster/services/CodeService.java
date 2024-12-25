package com.example.SE_disaster.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.mappers.*;
import com.example.SE_disaster.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService
{
    @Autowired
    RegionCodeMapper regionCodeMapper;
    @Autowired
    DisasterOriginMapper disasterOriginMapper;
    @Autowired
    CarrierTypeMapper carrierTypeMapper;
    @Autowired
    DisasterCategoryTypeMapper disasterCategoryTypeMapper;
    @Autowired
    DisasterInfoTypeMapper disasterInfoTypeMapper;
    
    public String encoder(String province, String city, String county,
                          String town, String village, String year,
                          String month, String day, String hour, String minute,
                          String second,String parento,String childo,
                          String carrier,String parentcate, String childcate,
                          String parentl, String childl)
    {
        System.out.println(province+city+county+town+village+year+month+day+hour+minute+second+parento+childo+carrier+parentcate+childcate+parentl+childl);
        String spaceTimeCode = "";
        QueryWrapper<RegionCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("province", province).eq("city", city).eq("county", county).eq("town", town).eq("village", village);
        spaceTimeCode = regionCodeMapper.selectOne(queryWrapper).code;
        spaceTimeCode +=year + month + day + hour + minute + second;
        String originCode = "";
        QueryWrapper<DisasterOriginType> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("parento", parento).eq("childo", childo);
        originCode = disasterOriginMapper.selectOne(queryWrapper1).oid;
        String carrierCode = "";
        QueryWrapper<CarrierType> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("cname", carrier);
        carrierCode = carrierTypeMapper.selectOne(queryWrapper2).cid;
        String situationCode = "";
        QueryWrapper<DisasterCategoryType> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("parentcate", parentcate).eq("childcate", childcate);
        situationCode =  disasterCategoryTypeMapper.selectOne(queryWrapper3).cateid;
        QueryWrapper<DisasterLabel> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.eq("parentl", parentl).eq("childl", childl);
        situationCode += disasterInfoTypeMapper.selectOne(queryWrapper4).childid;
        return spaceTimeCode + originCode + carrierCode + situationCode;
    }
    public String decoder(String code)
    {
        String province, city, county,
            town, village, year,
            month, day, hour, minute,
            second,parento,childo,
            carrier,parentcate, childcate,
            parentl, childl;
        String spaceCode = code.substring(0, 12);
        RegionCode regionCode = regionCodeMapper.selectById(spaceCode);
        province = regionCode.province;
        city = regionCode.city;
        county = regionCode.county;
        town = regionCode.town;
        village = regionCode.village;
        String timeCode = code.substring(12, 26);
        year = timeCode.substring(0, 4);
        month = timeCode.substring(4, 6);
        day = timeCode.substring(6, 8);
        hour = timeCode.substring(8, 10);
        minute = timeCode.substring(10, 12);
        second = timeCode.substring(12);
        String originCode = code.substring(26, 29);
        DisasterOriginType disasterOriginType = disasterOriginMapper.selectById(originCode);
        parento = disasterOriginType.parento;
        childo = disasterOriginType.childo;
        String carrierCode = code.substring(29, 30);
        CarrierType carrierType = carrierTypeMapper.selectById(carrierCode);
        carrier = carrierType.cname;
        String categoryCode = code.substring(30,33);
        DisasterCategoryType disasterCategoryType = disasterCategoryTypeMapper.selectById(categoryCode);
        parentcate = disasterCategoryType.parentcate;
        childcate = disasterCategoryType.childcate;
        String labelCode = code.substring(33);
        QueryWrapper<DisasterLabel > queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("childlid", labelCode).eq("parentlid", categoryCode.substring(0,1));
        DisasterLabel disasterLabel = disasterInfoTypeMapper.selectOne(queryWrapper);
        parentl = disasterLabel.parentl;
        childl = disasterLabel.childl;
        return province + city + county + town + village + year + month + day + hour + minute + second + parento + childo + carrier + parentcate + childcate + parentl + childl;
    }
}
