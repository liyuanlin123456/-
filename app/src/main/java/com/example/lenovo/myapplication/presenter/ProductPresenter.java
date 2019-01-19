package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.contract.ProductContract;
import com.example.lenovo.myapplication.model.ProductModel;
import com.example.lenovo.myapplication.net.RequestCallBack;

import java.util.HashMap;

public class ProductPresenter extends ProductContract.ProductPresenter {
    private ProductModel productModel;
    private ProductContract.IProductView iProductView;

    public ProductPresenter(ProductContract.IProductView iProductView) {
        this.productModel=new ProductModel();
        this.iProductView = iProductView;
    }

    @Override
    public void show(String apiUrl, HashMap<String, String> params) {
        if (productModel!=null){
            productModel.show(apiUrl, params, new RequestCallBack() {
                @Override
                public void onFailUre(String msg) {
                    if (iProductView!=null){
                        iProductView.onFailUre(msg);
                    }
                }

                @Override
                public void onSuccessShow(String result) {
                    if (iProductView!=null){
                        iProductView.onSuccessShow(result);
                    }
                }

                @Override
                public void onSuccessShopping(String result) {

                }

                @Override
                public void onSuccessShoppingShow(String result) {

                }
            });
        }
    }

    @Override
    public void shopping(String apiUrl, HashMap<String, String> params) {
        if (productModel!=null){
            productModel.shopping(apiUrl, params, new RequestCallBack() {
                @Override
                public void onFailUre(String msg) {
                    if (iProductView!=null){
                        iProductView.onFailUre(msg);
                    }
                }

                @Override
                public void onSuccessShow(String result) {

                }

                @Override
                public void onSuccessShopping(String result) {
                    if (iProductView!=null){
                        iProductView.onSuccessShopping(result);
                    }
                }

                @Override
                public void onSuccessShoppingShow(String result) {

                }
            });
        }
    }

    @Override
    public void shoppingShow(String apiUrl, HashMap<String, String> params) {
        if (productModel!=null){
            productModel.shoppingShow(apiUrl, params, new RequestCallBack() {
                @Override
                public void onFailUre(String msg) {
                    if (iProductView!=null){
                        iProductView.onFailUre(msg);
                    }
                }

                @Override
                public void onSuccessShow(String result) {

                }

                @Override
                public void onSuccessShopping(String result) {

                }

                @Override
                public void onSuccessShoppingShow(String result) {
                    if (iProductView!=null){
                        iProductView.onSuccessShoppingShow(result);
                    }
                }
            });
        }
    }
}
