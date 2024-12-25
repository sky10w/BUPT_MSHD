package com.example.SE_disaster.models;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dlog")
public class DisasterData {
    @TableId(value = "code")
    public String code;
    @TableField(value = "location")
    public String location;
    @TableField(value = "date")
    public String date;
    @TableField(value = "origin")
    public String origin;
    @TableField(value = "carrier")
    public String carrier;
    @TableField(value = "category")
    public String category;
    @TableField(value = "label")
    public String label;
    @TableField(value = "description")
    public String description;
}
