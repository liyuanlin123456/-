package com.example.lenovo.myapplication.entity;

import java.util.List;

public class Page {
    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean{
        public List<BannerBean> banner;
        public List<FenleiBean> fenlei;
        public Miaosha miaosha;
        public Tuijian tuijian;

        public static class BannerBean{
            public String icon;
        }

        public static class FenleiBean{
            public String icon;
            public String name;
        }

        public static class Miaosha{
            public List<ListBean> list;
            public String name;

            public static class ListBean{
                public String images;
                public double price;
            }
        }

        public static class Tuijian{
            public List<ListBean> list;
            public String name;

            public static class ListBean{
                public String images;
                public String title;
                public double price;
            }
        }
    }
}
