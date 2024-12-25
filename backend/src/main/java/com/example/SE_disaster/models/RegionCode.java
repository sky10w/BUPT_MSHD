package com.example.SE_disaster.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("region_code")
public class RegionCode {
    @TableId(value="rid",type = IdType.NONE)
    public String code;
    @TableField(value = "province")
    public String province;
    @TableField(value = "city")
    public String city;
    @TableField(value = "county")
    public String county;
    @TableField(value = "town")
    public String town;
    @TableField(value = "village")
    public String village;
}
