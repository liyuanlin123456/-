package com.example.lenovo.myapplication.entity;

import java.util.List;

public class ShoppingShow {
    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean{
        public boolean isChecked;

        public List<ListBean> list;
        public String sellerName;
        public String sellerid;

        public static class ListBean{
            public boolean isShoppingChecked;

            public String images;
            public double price;
            public String title;

            public int shoppingNum=1;
            public int pos;
        }
    }
}
