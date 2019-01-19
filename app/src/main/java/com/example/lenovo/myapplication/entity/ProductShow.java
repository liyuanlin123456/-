package com.example.lenovo.myapplication.entity;

import java.util.List;

public class ProductShow {
    public String msg;
    public String code;
    public List<DataBean> data;
    public String page;

    public static class DataBean{
        public String images;
        public int pid;
        public double price;
        public String title;
    }
}
