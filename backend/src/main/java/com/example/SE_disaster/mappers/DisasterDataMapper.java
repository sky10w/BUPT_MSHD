package com.example.SE_disaster.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SE_disaster.models.DisasterData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DisasterDataMapper extends BaseMapper<DisasterData> {
}