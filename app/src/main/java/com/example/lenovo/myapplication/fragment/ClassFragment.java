package com.example.lenovo.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.adapter.ClassLeftAdapter;
import com.example.lenovo.myapplication.adapter.ClassRightAdapter;
import com.example.lenovo.myapplication.api.ProductApi;
import com.example.lenovo.myapplication.contract.ProductContract;
import com.example.lenovo.myapplication.entity.ClassLeft;
import com.example.lenovo.myapplication.entity.ClassRight;
import com.example.lenovo.myapplication.presenter.ProductPresenter;
import com.google.gson.Gson;

import java.util.HashMap;

public class ClassFragment extends Fragment implements ProductContract.IProductView {

    private RecyclerView left_rec;
    private RecyclerView right_rec;
    private ProductPresenter presenter;
    private ClassLeftAdapter adapter;
    private ClassRightAdapter adapter1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_class,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        left_rec = view.findViewById(R.id.left_rec);
        left_rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        right_rec = view.findViewById(R.id.right_rec);
        right_rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter = new ProductPresenter(this);
        presenter.show(ProductApi.PRODUCT_LEFT,null);
        presenter.shopping(ProductApi.PRODUCT_RIGHT,null);
        adapter = new ClassLeftAdapter(getActivity());
        adapter1 = new ClassRightAdapter(getActivity());
        left_rec.setAdapter(adapter);
        right_rec.setAdapter(adapter1);
        adapter.setItemClickListener(new ClassLeftAdapter.ItemClickListener() {
            @Override
            public void click(int cid) {
                HashMap<String,String> params=new HashMap<>();
                params.put("cid",cid+"");
                presenter.shopping(ProductApi.PRODUCT_RIGHT,params);
            }
        });
    }

    @Override
    public void onFailUre(String msg) {

    }

    @Override
    public void onSuccessShow(String result) {
        ClassLeft classLeft = new Gson().fromJson(result, ClassLeft.class);
        adapter.setList(classLeft.data);
    }

    @Override
    public void onSuccessShopping(String result) {
        ClassRight classRight = new Gson().fromJson(result, ClassRight.class);
        adapter1.setList(classRight.data);
    }

    @Override
    public void onSuccessShoppingShow(String result) {

    }
}
