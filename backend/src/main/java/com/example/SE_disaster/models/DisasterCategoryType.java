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
@TableName(value = "dcategory")
public class DisasterCategoryType
{
    @TableId(value = "cateid",type = IdType.NONE)
    public String cate_id = "";
    @TableField(value = "parentcate")
    public String parent_cate = "";
    @TableField(value = "parentcateid")
    public String parent_cate_id = "";
    @TableField(value = "childcate")
    public String child_cate = "";
    @TableField(value = "childcateid")
    public String child_cate_id = "";
}
