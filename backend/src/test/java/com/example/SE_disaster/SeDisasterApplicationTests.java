package com.example.SE_disaster;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.SE_disaster.services.CodeService;

@SpringBootTest
@RunWith(SpringRunner.class)
class SeDisasterApplicationTests {

    @Test
    void contextLoads() {
        //测试编码与解码
        CodeService codeService = new CodeService();
        String code = codeService.encoder("北京市", "市辖区", "东城区", "东华门街道", "多福巷社区居委会", "2024", "09", "23", "12", "30", "00", "业务报送数据", "后方地震应急指挥部", "图像", "人员伤亡及失踪", "死亡", "人员伤亡及失踪信息","受灾人数");
    }

}
