package com.example.SE_disaster.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("personalInformation")
public class PersonalInformation
{
    @TableId(value = "uid",type = IdType.NONE)
    public Long uid;
    @TableField("phonenumber")
    public String phonenumber;
    @TableField("email")
    public String email;
    @TableField("nickname")
    public String nickname;
}
