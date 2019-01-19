package com.example.lenovo.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.adapter.ShoppingShowAdapter;
import com.example.lenovo.myapplication.api.ProductApi;
import com.example.lenovo.myapplication.contract.ProductContract;
import com.example.lenovo.myapplication.entity.ShoppingShow;
import com.example.lenovo.myapplication.net.CartUiCallBack;
import com.example.lenovo.myapplication.presenter.ProductPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

public class ShoppingFragment extends Fragment implements ProductContract.IProductView,CartUiCallBack {

    private ProductPresenter presenter;
    private ShoppingShowAdapter myAdapter;
    private HomeFragment homeFragment;
    private CheckBox checkBox;
    private TextView price;
    private ShoppingShow shoppingShow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        XRecyclerView xrec = view.findViewById(R.id.xrec);
        checkBox = view.findViewById(R.id.checkBox);
        price = view.findViewById(R.id.price);
        xrec.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new ShoppingShowAdapter(getActivity(),this);
        presenter = new ProductPresenter(this);
        HashMap<String, String> params = new HashMap<>();
        params.put("uid","24417");
        presenter.shoppingShow(ProductApi.SHOPPING_SHOW,params);
        xrec.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    for (ShoppingShow.DataBean datum : shoppingShow.data) {
                        datum.isChecked=true;
                        for (ShoppingShow.DataBean.ListBean listBean : datum.list) {
                            listBean.isShoppingChecked=true;
                        }
                    }
                }else{
                    for (ShoppingShow.DataBean datum : shoppingShow.data) {
                        datum.isChecked=false;
                        for (ShoppingShow.DataBean.ListBean listBean : datum.list) {
                            listBean.isShoppingChecked=false;
                        }
                    }
                }
                myAdapter.notifyDataSetChanged();
                getPrice();
            }
        });
    }

    public void getResult(String result){
        if (!result.equals("null")) {
            ShoppingShow shoppingShow = new Gson().fromJson(result, ShoppingShow.class);
            myAdapter.setList(shoppingShow.data);
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailUre(String msg) {

    }

    @Override
    public void onSuccessShow(String result) {

    }

    @Override
    public void onSuccessShopping(String result) {

    }

    @Override
    public void onSuccessShoppingShow(String result) {
        if (!result.equals("null")) {
            shoppingShow = new Gson().fromJson(result, ShoppingShow.class);
            myAdapter.setList(shoppingShow.data);
        }
    }
    public void getPrice(){
        double pricee=0;
        for (ShoppingShow.DataBean datum : shoppingShow.data) {
            for (ShoppingShow.DataBean.ListBean listBean : datum.list) {
                if (listBean.isShoppingChecked){
                    pricee+=listBean.shoppingNum*listBean.price;
                }
            }
        }
        price.setText("￥"+pricee);
    }

    @Override
    public void notifyPrice() {
        getPrice();
    }
}
