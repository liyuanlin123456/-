package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.entity.Page;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

public class Fragment_pageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Page.DataBean dataBean;
    private static final int BANNER=0;
    private static final int FENLEI=1;
    private static final int MIAOSHA=2;
    private static final int TUIJIAN=3;

    public Fragment_pageAdapter(Context context) {
        this.context = context;
        this.dataBean=new Page.DataBean();
    }

    public void setList(Page.DataBean dataBean) {
        if (dataBean!=null) {
            this.dataBean = dataBean;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return BANNER;
        }else if(position==1){
            return FENLEI;
        }else if(position==2){
            return MIAOSHA;
        }else{
            return TUIJIAN;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i)==BANNER){
            View view = LayoutInflater.from(context).inflate(R.layout.page_banner, viewGroup, false);
            ViewHolder_Banner viewHolder_banner=new ViewHolder_Banner(view);
            return viewHolder_banner;
        }else if(getItemViewType(i)==FENLEI){
            View view = LayoutInflater.from(context).inflate(R.layout.page_fenlei, viewGroup, false);
            ViewHolder_Fenlei viewHolder_fenlei=new ViewHolder_Fenlei(view);
            return viewHolder_fenlei;
        }else if(getItemViewType(i)==MIAOSHA){
            View view = LayoutInflater.from(context).inflate(R.layout.page_miaosha, viewGroup, false);
            ViewHolder_Miaosha viewHolder_miaosha=new ViewHolder_Miaosha(view);
            return viewHolder_miaosha;
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.page_tuijian, viewGroup, false);
            ViewHolder_Tuijian viewHolderTuijian=new ViewHolder_Tuijian(view);
            return viewHolderTuijian;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (dataBean.banner!=null && dataBean.fenlei!=null && dataBean.miaosha!=null && dataBean.tuijian!=null){
            if (viewHolder instanceof ViewHolder_Banner){
                ArrayList<String> list1 = new ArrayList<>();
                for (int j=0;j<dataBean.banner.size();j++){
                    list1.add(dataBean.banner.get(j).icon);
                }
                ((ViewHolder_Banner) viewHolder).flyBanner.setImagesUrl(list1);
            }else if (viewHolder instanceof ViewHolder_Fenlei){
                ((ViewHolder_Fenlei) viewHolder).rec.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false));
                ((ViewHolder_Fenlei) viewHolder).rec.setAdapter(new Page_fenlei_Adapter(context,dataBean.fenlei));
            }else if (viewHolder instanceof ViewHolder_Miaosha){
                ((ViewHolder_Miaosha) viewHolder).name.setText(dataBean.miaosha.name);
                ((ViewHolder_Miaosha) viewHolder).rec.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                ((ViewHolder_Miaosha) viewHolder).rec.setAdapter(new Page_miaosha_Adapter(context,dataBean.miaosha.list));
            }else{
                ((ViewHolder_Tuijian) viewHolder).rec.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                ((ViewHolder_Tuijian) viewHolder).rec.setAdapter(new Page_tuijian_Adapter(context,dataBean.tuijian.list));
            }
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder_Banner extends RecyclerView.ViewHolder {

        private final FlyBanner flyBanner;

        public ViewHolder_Banner(@NonNull View itemView) {
            super(itemView);
            flyBanner = itemView.findViewById(R.id.flyBanner);
        }
    }
    public class ViewHolder_Fenlei extends RecyclerView.ViewHolder {

        private final RecyclerView rec;

        public ViewHolder_Fenlei(@NonNull View itemView) {
            super(itemView);
            rec = itemView.findViewById(R.id.rec);
        }
    }
    public class ViewHolder_Miaosha extends RecyclerView.ViewHolder {

        private final TextView name;
        private final RecyclerView rec;

        public ViewHolder_Miaosha(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            rec = itemView.findViewById(R.id.rec);
        }
    }
    public class ViewHolder_Tuijian extends RecyclerView.ViewHolder {

        private final RecyclerView rec;

        public ViewHolder_Tuijian(@NonNull View itemView) {
            super(itemView);
            rec = itemView.findViewById(R.id.rec);
        }
    }

}
