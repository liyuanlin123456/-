package com.example.lenovo.myapplication.entity;

import java.util.List;

public class ClassLeft {

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean{
        public int cid;
        public String name;
    }
}
