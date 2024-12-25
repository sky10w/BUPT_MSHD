package com.example.SE_disaster.services;

import com.example.SE_disaster.services.DisasterInfo.DisasterCarrierInfo;
import com.example.SE_disaster.services.DisasterInfo.DisasterOriginInfo;
import com.example.SE_disaster.services.DisasterInfo.DisasterSituationInfo.DisasterSituationInfo;
import com.example.SE_disaster.services.DisasterInfo.DisasterSpaceTimeInfo.DisasterSpaceInfo;
import com.example.SE_disaster.services.DisasterInfo.DisasterSpaceTimeInfo.DisasterSpaceTimeInfo;
import com.example.SE_disaster.services.DisasterInfo.DisasterSpaceTimeInfo.DisasterTimeInfo;
import org.springframework.stereotype.Service;

@Service
public class CodeService
{
    public DisasterSpaceTimeInfo disasterSpaceTimeInfo;
    public DisasterOriginInfo disasterOriginInfo;
    public DisasterCarrierInfo disasterCarrierInfo;
    public DisasterSituationInfo disasterSituationInfo;
    
    public String encoder(String province, String city, String county,
                          String town, String village, String year,
                          String month, String day, String hour, String minute,
                          String second,String parento,String childo,
                          String carrier,String parentcate, String childcate,
                          String parentl, String childl)
    {
        disasterSpaceTimeInfo = new DisasterSpaceTimeInfo();
        String spaceTimeCode = disasterSpaceTimeInfo.encoder(province, city, county, town, village, year, month, day, hour, minute, second);
        disasterOriginInfo = new DisasterOriginInfo(parento, childo);
        String originCode = disasterOriginInfo.encoder();
        disasterCarrierInfo = new DisasterCarrierInfo(carrier);
        String carrierCode = disasterCarrierInfo.encoder();
        disasterSituationInfo = new DisasterSituationInfo();
        String situationCode = disasterSituationInfo.encoder(parentcate, childcate, parentl, childl);
        return spaceTimeCode + originCode + carrierCode + situationCode;
    }
    public void decoder(String code)
    {
        disasterSpaceTimeInfo.decoder(code.substring(0, 26));
        disasterOriginInfo.decoder(code.substring(26, 29));
        disasterCarrierInfo.decoder(code.substring(29, 30));
        disasterSituationInfo.decoder(code.substring(30));
    }
}
