package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.entity.Page;

import java.util.List;

public class Page_miaosha_Adapter extends RecyclerView.Adapter<Page_miaosha_Adapter.ViewHolder> {
    private Context context;
    private List<Page.DataBean.Miaosha.ListBean> list;

    public Page_miaosha_Adapter(Context context, List<Page.DataBean.Miaosha.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.page_miaosha_item, viewGroup, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String[] split = list.get(i).images.split("!");
        Glide.with(context).load(split[0]).into(viewHolder.image);
        viewHolder.price.setText("￥"+list.get(i).price);
        viewHolder.yprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView price;
        private final TextView yprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            price = itemView.findViewById(R.id.price);
            yprice = itemView.findViewById(R.id.yprice);
        }
    }
}
