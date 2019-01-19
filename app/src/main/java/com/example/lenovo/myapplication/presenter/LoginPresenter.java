package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.contract.LoginContract;
import com.example.lenovo.myapplication.model.LoginModel;
import com.example.lenovo.myapplication.net.RequestLoginCallback;

import java.util.HashMap;

public class LoginPresenter extends LoginContract.LoginPresenter {
    private LoginModel loginModel;
    private LoginContract.ILoginView iLoginView;

    public LoginPresenter(LoginContract.ILoginView iLoginView) {
        this.loginModel=new LoginModel();
        this.iLoginView = iLoginView;
    }

    @Override
    public void login(HashMap<String, String> params) {
        if (loginModel!=null){
            loginModel.ligin(params, new RequestLoginCallback() {
                @Override
                public void onFailUre(String error) {
                    if (iLoginView!=null){
                        iLoginView.onFailUre(error);
                    }
                }

                @Override
                public void onSuccess(String result) {
                    if (iLoginView!=null){
                        iLoginView.onSuccess(result);
                    }
                }
            });
        }
    }
    public void onDestroy(){
        if (iLoginView!=null){
            iLoginView=null;
        }
    }
}
