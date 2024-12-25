package com.example.SE_disaster.services.DisasterInfo.DisasterSpaceTimeInfo;

public class DisasterSpaceInfo
{
    public String province;
    public String city;
    public String county;
    public String town;
    public String village;
    public String community;

    static public String encode(DisasterSpaceInfo info) {
        return info.province + info.district + info.county + info.village + info.community;
    }

    static public DisasterSpaceInfo decode(String code) {
        DisasterSpaceInfo res = new DisasterSpaceInfo();
        res.province =

    }
}
