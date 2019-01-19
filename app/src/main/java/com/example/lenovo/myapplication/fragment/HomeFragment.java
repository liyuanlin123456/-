package com.example.lenovo.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.adapter.ProductShowAdapter;
import com.example.lenovo.myapplication.api.ProductApi;
import com.example.lenovo.myapplication.contract.ProductContract;
import com.example.lenovo.myapplication.entity.ProductShopping;
import com.example.lenovo.myapplication.entity.ProductShow;
import com.example.lenovo.myapplication.entity.ShoppingShow;
import com.example.lenovo.myapplication.presenter.ProductPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements ProductContract.IProductView {

    private EditText ed_text;
    private Button btn;
    private XRecyclerView xrec;
    private int page=1;
    private ProductPresenter presenter;
    private ProductShowAdapter myAdapter;
    private String s="手机";
    private List<ProductShow.DataBean> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ed_text = view.findViewById(R.id.ed_text);
        btn = view.findViewById(R.id.btn);
        xrec = view.findViewById(R.id.xrec);
        xrec.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter = new ProductPresenter(this);
        myAdapter = new ProductShowAdapter(getActivity());
        xrec.setAdapter(myAdapter);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                page=1;
                s = ed_text.getText().toString();
                HashMap<String,String> params=new HashMap<>();
                params.put("keywords", s);
                params.put("page",page+"");
                presenter.show(ProductApi.PRODUCT_SHOW,params);
            }
        });
        xrec.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                HashMap<String,String> params=new HashMap<>();
                params.put("keywords",s);
                params.put("page",page+"");
                presenter.show(ProductApi.PRODUCT_SHOW,params);
                xrec.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                HashMap<String,String> params=new HashMap<>();
                params.put("keywords",s);
                params.put("page",page+"");
                presenter.show(ProductApi.PRODUCT_SHOW,params);
                xrec.loadMoreComplete();
            }
        });
        myAdapter.setBtnOnClick(new ProductShowAdapter.BtnOnClick() {
            @Override
            public void onClick(int pid) {
                HashMap<String,String> params=new HashMap<>();
                params.put("uid","24417");
                params.put("pid",pid+"");
                presenter.shopping(ProductApi.PRODUCT_SHOPPING,params);
            }
        });
    }

    @Override
    public void onFailUre(String msg) {

    }

    @Override
    public void onSuccessShow(String result) {
        ProductShow productShow = new Gson().fromJson(result, ProductShow.class);
        data = productShow.data;
        if (page==1){
            myAdapter.setList(productShow.data);
        }else{
            myAdapter.addList(productShow.data);
        }

    }

    @Override
    public void onSuccessShopping(String result) {
        ProductShopping productShopping = new Gson().fromJson(result, ProductShopping.class);
        Toast.makeText(getActivity(),productShopping.msg,Toast.LENGTH_SHORT).show();
        if (productShopping.msg.equals("加购成功")){
            HashMap<String,String> params=new HashMap<>();
            params.put("uid","24417");
            presenter.shoppingShow(ProductApi.SHOPPING_SHOW,params);
        }
    }

    @Override
    public void onSuccessShoppingShow(String result) {
        if (successShoppingShow!=null) {
            successShoppingShow.onsuccessShoppingShow(result);
        }
    }
    private SuccessShoppingShow successShoppingShow;

    public void setSuccessShoppingShow(SuccessShoppingShow successShoppingShow) {
        this.successShoppingShow = successShoppingShow;
    }

    public interface SuccessShoppingShow{
        void onsuccessShoppingShow(String result);
    }
}
