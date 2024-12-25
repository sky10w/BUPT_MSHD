package com.example.SE_disaster.services.DisasterInfo.DisasterSpaceTimeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class DisasterTimeInfo {
    public String year;
    public String month;
    public String day;
    public String hour;
    public String minute;
    public String second;
    
    public String encode() {
        return year + month + day + hour + minute + second;
    }
    
    public void decode(String code) {
        year = code.substring(0, 4);
        month = code.substring(4, 6);
        day = code.substring(6, 8);
        hour = code.substring(8, 10);
        minute = code.substring(10, 12);
        second = code.substring(12);
    }
}
