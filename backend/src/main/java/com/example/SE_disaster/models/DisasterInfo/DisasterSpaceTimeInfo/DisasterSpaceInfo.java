package com.example.SE_disaster.models.DisasterInfo.DisasterSpaceTimeInfo;

public class DisasterSpaceInfo {
    public String province;
    public String district;
    public String county;
    public String village;
    public String community;

    static public String encoder(DisasterSpaceInfo info) {
        return info.province + info.district + info.county + info.village + info.community;
    }

    static public DisasterSpaceInfo decoder(String code) {
        DisasterSpaceInfo res = new DisasterSpaceInfo();
        res.province = 

    }
}
