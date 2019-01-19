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
import com.example.lenovo.myapplication.adapter.Fragment_pageAdapter;
import com.example.lenovo.myapplication.api.ProductApi;
import com.example.lenovo.myapplication.contract.ProductContract;
import com.example.lenovo.myapplication.entity.Page;
import com.example.lenovo.myapplication.presenter.ProductPresenter;
import com.google.gson.Gson;

public class PageFragment extends Fragment implements ProductContract.IProductView {

    private RecyclerView rec;
    private Fragment_pageAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rec = view.findViewById(R.id.rec);
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        ProductPresenter presenter = new ProductPresenter(this);
        presenter.show(ProductApi.HOME,null);
        myAdapter = new Fragment_pageAdapter(getActivity());
        rec.setAdapter(myAdapter);
    }

    @Override
    public void onFailUre(String msg) {

    }

    @Override
    public void onSuccessShow(String result) {
        Page page = new Gson().fromJson(result, Page.class);
        myAdapter.setList(page.data);
    }

    @Override
    public void onSuccessShopping(String result) {

    }

    @Override
    public void onSuccessShoppingShow(String result) {

    }
}
