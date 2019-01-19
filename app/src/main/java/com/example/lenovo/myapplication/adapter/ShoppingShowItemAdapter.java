package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.entity.ShoppingShow;
import com.example.lenovo.myapplication.net.CartCallBack;
import com.example.lenovo.myapplication.view.AddMinusNum;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ShoppingShowItemAdapter extends XRecyclerView.Adapter<ShoppingShowItemAdapter.ViewHolder> {
    private Context context;
    private List<ShoppingShow.DataBean.ListBean> list;
    private CartCallBack cartCallBack;

    public ShoppingShowItemAdapter(Context context, List<ShoppingShow.DataBean.ListBean> list,CartCallBack cartCallBack) {
        this.context = context;
        this.list = list;
        this.cartCallBack=cartCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_item_item, viewGroup, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        String[] split = list.get(i).images.split("!");
        Glide.with(context).load(split[0]).into(viewHolder.image);
        viewHolder.title.setText(list.get(i).title);
        viewHolder.price.setText("￥"+list.get(i).price);
        viewHolder.checkBox.setChecked(list.get(i).isShoppingChecked);
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).isShoppingChecked=viewHolder.checkBox.isChecked();
                for (ShoppingShow.DataBean.ListBean listBean : list) {
                    if (!listBean.isShoppingChecked){
                        cartCallBack.notifyChecked(false,list.get(i).pos);
                        return;
                    }
                    cartCallBack.notifyChecked(true,list.get(i).pos);
                }
            }
        });
        viewHolder.addMinusNum.setAddMinusNums(new AddMinusNum.AddMinusNums() {
            @Override
            public void addMinusNum(int num) {
                list.get(i).shoppingNum=num;
                if (cartCallBack!=null){
                    cartCallBack.notifyChange();
                }
            }
        });
        viewHolder.addMinusNum.setNum(list.get(i).shoppingNum);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final CheckBox checkBox;
        private final TextView title;
        private final TextView price;
        private final AddMinusNum addMinusNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            addMinusNum = itemView.findViewById(R.id.addMinusNum);
        }
    }
}
