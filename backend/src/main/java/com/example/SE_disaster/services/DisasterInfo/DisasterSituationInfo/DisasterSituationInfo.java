package com.example.SE_disaster.services.DisasterInfo.DisasterSituationInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class DisasterSituationInfo
{
    public DisasterCategoryInfo disasterCategoryInfo;
    public DisasterInfoInfo disasterInfoInfo;
    
    public String encoder(String parentcate, String childcate, String parentl, String childl)
    {
        disasterCategoryInfo = new DisasterCategoryInfo(parentcate, childcate);
        disasterInfoInfo = new DisasterInfoInfo(parentl, childl);
        return disasterCategoryInfo.encoder() + disasterInfoInfo.encoder();
    }
    
    public void decoder(String code)
    {
        disasterCategoryInfo.decoder(code.substring(0, 3));
        disasterInfoInfo.decoder(code.substring(3));
    }
}
