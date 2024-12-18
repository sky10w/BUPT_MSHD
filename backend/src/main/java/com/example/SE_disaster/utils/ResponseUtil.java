package com.example.SE_disaster.utils;

import com.alibaba.fastjson2.JSON;

import lombok.Data;

@Data
public class ResponseUtil {
    private int code = 0;
    private String msg = null;
    private Object data = null;

    public static ResponseUtil respond() {
        return new ResponseUtil();
    }

    public ResponseUtil setCode(int code) {
        this.code = code;
        return this;
    }

    public ResponseUtil setMessage(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseUtil setData(Object data) {
        this.data = data;
        return this;
    }

    public String json() {
        // System.err.println(code);
        // System.err.println(msg);
        // System.err.println(data);
        String fin = JSON.toJSONString(this);
        System.err.println(fin);
        return fin;
    }
}
