package com.example.lenovo.myapplication.contract;

import com.example.lenovo.myapplication.net.RequestCallBack;

import java.util.HashMap;

public interface ProductContract {
    public abstract class ProductPresenter{
        public abstract void show(String apiUrl,HashMap<String,String> params);
        public abstract void shopping(String apiUrl,HashMap<String,String> params);
        public abstract void shoppingShow(String apiUrl,HashMap<String,String> params);
    }
    interface IProductModel{
        void show(String apiUrl,HashMap<String,String> params, RequestCallBack requestCallBack);
        void shopping(String apiUrl,HashMap<String,String> params, RequestCallBack requestCallBack);
        void shoppingShow(String apiUrl,HashMap<String,String> params, RequestCallBack requestCallBack);
    }
    interface IProductView{
        void onFailUre(String msg);
        void onSuccessShow(String result);
        void onSuccessShopping(String result);
        void onSuccessShoppingShow(String result);
    }
}
