package com.example.SE_disaster.models.DisasterInfo.DisasterSpaceTimeInfo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("region_code")
public class Region_code {
    public String code;
    public String province;
    public String city;
    public String county;
    public String town;
    public String village;
}
