package com.example.SE_disaster;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SE_disaster.controllers.InformationController;
import com.example.SE_disaster.mappers.RegionCodeMapper;
import com.example.SE_disaster.models.RegionCode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.SE_disaster.services.CodeService;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
class SeDisasterApplicationTests {
    @Autowired
    private RegionCodeMapper regionCodeMapper;

    @Test
    void contextLoads() {
        //测试编码与解码
        try {
            QueryWrapper<RegionCode> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("DISTINCT province");
            var list = regionCodeMapper.selectList(queryWrapper);
            System.out.println("province list: " + list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        CodeService codeService = new CodeService();
//        String code = codeService.encoder("北京市", "市辖区", "东城区", "东华门街道", "多福巷社区居委会", "2024", "09", "23", "12", "30", "00", "业务报送数据", "后方地震应急指挥部", "图像", "人员伤亡及失踪", "死亡", "人员伤亡及失踪信息","受灾人数");
    }

}
