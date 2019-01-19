package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.entity.ProductShow;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductShowAdapter extends XRecyclerView.Adapter<ProductShowAdapter.ViewHolder> {
    private Context context;
    private List<ProductShow.DataBean> list;

    public ProductShowAdapter(Context context) {
        this.context = context;
        this.list=new ArrayList<>();
    }

    public void setList(List<ProductShow.DataBean> list) {
        if (list!=null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }
    public void addList(List<ProductShow.DataBean> list1) {
        if (list!=null) {
            this.list.addAll(list1);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_item, viewGroup, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String[] split = list.get(i).images.split("!");
        Glide.with(context).load(split[0]).into(viewHolder.image);
        viewHolder.title.setText(list.get(i).title);
        viewHolder.price.setText("￥"+list.get(i).price);
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnClick.onClick(list.get(i).pid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView title;
        private final TextView price;
        private final Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            btn = itemView.findViewById(R.id.btn);
        }
    }
    private BtnOnClick btnOnClick;

    public void setBtnOnClick(BtnOnClick btnOnClick) {
        this.btnOnClick = btnOnClick;
    }

    public interface BtnOnClick{
        void onClick(int pid);
    }
}
