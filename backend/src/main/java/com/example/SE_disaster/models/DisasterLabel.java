package com.example.SE_disaster.models;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dlabel")
public class DisasterLabel {
    @TableField(value = "parentl")
    public String parentl = "";

    @MppMultiId
    @TableField(value = "parentid")
    public String parentid = "";

    @TableField(value = "childl")
    public String childl = "";

    @MppMultiId
    @TableField(value = "childid")
    public String childid = "";
}
