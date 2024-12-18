package com.example.SE_disaster.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SE_disaster.models.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
