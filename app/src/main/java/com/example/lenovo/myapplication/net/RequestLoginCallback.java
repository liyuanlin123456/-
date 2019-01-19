package com.example.lenovo.myapplication.net;

public interface RequestLoginCallback {
    void onFailUre(String error);
    void onSuccess(String result);
}
