package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.entity.ShoppingShow;
import com.example.lenovo.myapplication.net.CartCallBack;
import com.example.lenovo.myapplication.net.CartUiCallBack;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingShowAdapter extends XRecyclerView.Adapter<ShoppingShowAdapter.ViewHolder> implements CartCallBack {
    private Context context;
    private List<ShoppingShow.DataBean> list;
    private CartUiCallBack cartUiCallBack;

    public ShoppingShowAdapter(Context context,CartUiCallBack cartUiCallBack) {
        this.context = context;
        this.cartUiCallBack=cartUiCallBack;
        this.list=new ArrayList<>();
    }

    public void setList(List<ShoppingShow.DataBean> list) {
        if (list!=null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_item, viewGroup, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.checkBox.setChecked(list.get(i).isChecked);
        for (ShoppingShow.DataBean.ListBean listBean : list.get(i).list) {
            listBean.pos=i;
        }
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).isChecked=viewHolder.checkBox.isChecked();
                for (ShoppingShow.DataBean.ListBean listBean : list.get(i).list) {
                    listBean.isShoppingChecked=list.get(i).isChecked;
                }
                notifyDataSetChanged();
                if (cartUiCallBack!=null){
                    cartUiCallBack.notifyPrice();
                }
            }
        });
        viewHolder.name.setText(list.get(i).sellerName);
        viewHolder.xrec.setLayoutManager(new LinearLayoutManager(context));
        viewHolder.xrec.setAdapter(new ShoppingShowItemAdapter(context,list.get(i).list,this));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void notifyChecked(boolean isChecked, int position) {
        list.get(position).isChecked=isChecked;
        notifyDataSetChanged();
        if (cartUiCallBack!=null){
            cartUiCallBack.notifyPrice();
        }
    }

    @Override
    public void notifyChange() {
        if (cartUiCallBack!=null){
            cartUiCallBack.notifyPrice();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final XRecyclerView xrec;
        private final CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            checkBox = itemView.findViewById(R.id.checkBox);
            xrec = itemView.findViewById(R.id.xrec);
        }
    }
}
