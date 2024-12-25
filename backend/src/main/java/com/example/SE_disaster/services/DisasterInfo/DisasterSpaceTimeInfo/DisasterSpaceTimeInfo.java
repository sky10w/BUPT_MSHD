package com.example.SE_disaster.services.DisasterInfo.DisasterSpaceTimeInfo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class DisasterSpaceTimeInfo {
    public DisasterSpaceInfo spaceInfo;
    public DisasterTimeInfo timeInfo;

    public String encoder(String province, String city, String county, String town, String village, String year, String month, String day, String hour, String minute, String second)
    {
        spaceInfo = new DisasterSpaceInfo(province, city, county, town, village);
        timeInfo = new DisasterTimeInfo(year, month, day, hour, minute, second);;
        return spaceInfo.encode() + timeInfo.encode();
    }
    
    public void decoder(String code)
    {
        spaceInfo.decode(code.substring(0, 12));
        timeInfo.decode(code.substring(12));
    }
}