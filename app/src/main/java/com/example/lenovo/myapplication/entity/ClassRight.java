package com.example.lenovo.myapplication.entity;

import java.util.List;

public class ClassRight {

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean{
        public String name;
        public List<ListBean> list;

        public static class ListBean{
            public String icon;
            public String name;
        }
    }
}
