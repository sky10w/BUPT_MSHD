package com.example.SE_disaster.models;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dinfo_type")
public class DisasterInfoType {
    @TableField(value = "parentl")
    public String parentl = "";

    @TableField(value = "parentid")
    public String parentid = "";

    @TableField(value = "childl")
    public String childl = "";

    @TableField(value = "childid")
    public String childid = "";
}
