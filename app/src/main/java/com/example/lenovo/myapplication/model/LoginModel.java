package com.example.lenovo.myapplication.model;

import android.text.TextUtils;

import com.example.lenovo.myapplication.api.LoginApi;
import com.example.lenovo.myapplication.contract.LoginContract;
import com.example.lenovo.myapplication.net.OkhttpCallBack;
import com.example.lenovo.myapplication.net.RequestLoginCallback;
import com.example.lenovo.myapplication.utils.OkhttpUtils;

import java.util.HashMap;

public class LoginModel implements LoginContract.ILoginModel {
    @Override
    public void ligin(HashMap<String, String> params, final RequestLoginCallback requestLoginCallback) {
        OkhttpUtils.getmInstance().doPost(LoginApi.LOGIN, params, new OkhttpCallBack(){
            @Override
            public void onFailUre(String msg) {
                if (requestLoginCallback!=null){
                    requestLoginCallback.onFailUre("网络异常，请稍后再试");
                }
            }

            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    requestLoginCallback.onSuccess(result);
                }
            }
        });
    }
}
