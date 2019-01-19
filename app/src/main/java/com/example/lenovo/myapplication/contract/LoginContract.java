package com.example.lenovo.myapplication.contract;

import com.example.lenovo.myapplication.net.RequestLoginCallback;

import java.util.HashMap;

public interface LoginContract {
    //p层的方法
    public abstract class LoginPresenter{
        public abstract void login(HashMap<String,String> params);
    }
    //model 层的方法
    interface ILoginModel{
        void ligin(HashMap<String,String> params, RequestLoginCallback requestLoginCallback);
    }
    //view层的方法
    interface ILoginView{
        void onFailUre(String msg);
        void onSuccess(String result);
    }
}
