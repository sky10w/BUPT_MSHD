package com.example.SE_disaster.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.SE_disaster.mappers.RegionCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonOPService
{
    public <T> List<T> getColumnDistinct(BaseMapper<T> mapper, String col) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT " + col);
        return mapper.selectList(queryWrapper);
    }
    
    public <T> List<T> getColumnDistinct(BaseMapper<T> mapper, String col, QueryWrapper queryWrapper) {
        queryWrapper.select("DISTINCT " + col);
        return mapper.selectList(queryWrapper);
    }
}
