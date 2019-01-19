package com.example.lenovo.myapplication.model;

import com.example.lenovo.myapplication.contract.ProductContract;
import com.example.lenovo.myapplication.net.OkhttpCallBack;
import com.example.lenovo.myapplication.net.RequestCallBack;
import com.example.lenovo.myapplication.utils.OkhttpUtils;

import java.util.HashMap;

public class ProductModel implements ProductContract.IProductModel {

    @Override
    public void show(String apiUrl, HashMap<String, String> params, final RequestCallBack requestCallBack) {
        OkhttpUtils.getmInstance().doPost(apiUrl, params, new OkhttpCallBack() {
            @Override
            public void onFailUre(String msg) {
                if (requestCallBack!=null){
                    requestCallBack.onFailUre("网络异常，请稍后再试");
                }
            }

            @Override
            public void onSuccess(String result) {
                if (requestCallBack!=null){
                    requestCallBack.onSuccessShow(result);
                }
            }
        });
    }

    @Override
    public void shopping(String apiUrl, HashMap<String, String> params, final RequestCallBack requestCallBack) {
        OkhttpUtils.getmInstance().doPost(apiUrl, params, new OkhttpCallBack() {
            @Override
            public void onFailUre(String msg) {
                if (requestCallBack!=null){
                    requestCallBack.onFailUre("网络异常，请稍后再试");
                }
            }

            @Override
            public void onSuccess(String result) {
                if (requestCallBack!=null){
                    requestCallBack.onSuccessShopping(result);
                }
            }
        });
    }

    @Override
    public void shoppingShow(String apiUrl, HashMap<String, String> params, final RequestCallBack requestCallBack) {
        OkhttpUtils.getmInstance().doPost(apiUrl, params, new OkhttpCallBack() {
            @Override
            public void onFailUre(String msg) {
                if (requestCallBack!=null){
                    requestCallBack.onFailUre("网络异常，请稍后再试");
                }
            }

            @Override
            public void onSuccess(String result) {
                if (requestCallBack!=null){
                    requestCallBack.onSuccessShoppingShow(result);
                }
            }
        });
    }
}
