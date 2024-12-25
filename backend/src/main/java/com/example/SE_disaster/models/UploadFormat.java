package com.example.SE_disaster.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFormat {
    public String province;
    public String city;
    public String county;
    public String town;
    public String village;
    public String year;
    public String month;
    public String day;
    public String hour;
    public String minute;
    public String second;
    public String parento;
    public String childo;
    public String carrier;
    public String parentcate;
    public String childcate;
    public String parentl;
    public String childl;
    public Object content;
}

