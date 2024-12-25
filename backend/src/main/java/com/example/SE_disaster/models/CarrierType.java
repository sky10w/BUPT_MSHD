package com.example.SE_disaster.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "dcarrier")
public class CarrierType
{
    @TableId(value = "cid",type = IdType.NONE)
    public String cid;
    @TableField(value = "cname")
    public String cname;
}
