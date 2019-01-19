package com.example.lenovo.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.contract.LoginContract;
import com.example.lenovo.myapplication.entity.Login;
import com.example.lenovo.myapplication.presenter.LoginPresenter;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements LoginContract.ILoginView {

    private EditText mobile;
    private EditText password;
    private Button btn_login;
    private Button btn_qq;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_qq = findViewById(R.id.btn_qq);
        presenter = new LoginPresenter(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取edittext的文本
                String phone = mobile.getText().toString();
                String pwd = password.getText().toString();
                HashMap<String,String> params=new HashMap<>();
                //存放到hashmap中
                params.put("mobile",phone);
                params.put("password",pwd);
                //传递值
                presenter.login(params);
            }
        });
    }

    @Override
    public void onFailUre(String msg) {

    }

    @Override
    public void onSuccess(String result) {
        Login login = new Gson().fromJson(result, Login.class);
        Toast.makeText(LoginActivity.this,login.getMsg(),Toast.LENGTH_SHORT).show();
        if (login.getMsg().equals("登录成功")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
