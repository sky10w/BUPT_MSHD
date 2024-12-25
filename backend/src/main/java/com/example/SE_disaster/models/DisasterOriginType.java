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
@TableName(value = "dorigin")
public class DisasterOriginType
{
    @TableId(value = "oid",type = IdType.NONE)
    public String oid;
    @TableField(value = "parentoid")
    public String parentoid;
    @TableField(value = "parento")
    public String parento;
    @TableField(value = "childoid")
    public String childoid;
    @TableField(value = "childo")
    public String childo;
}
