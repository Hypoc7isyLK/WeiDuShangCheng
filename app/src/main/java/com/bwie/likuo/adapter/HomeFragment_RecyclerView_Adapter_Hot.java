package com.bwie.likuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.bean.HomeFragment_HotBean;
import com.bwie.likuo.bean.Result;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/01/02
 * author:李阔(淡意衬优柔)
 * function:
 */
public class HomeFragment_RecyclerView_Adapter_Hot extends RecyclerView.Adapter<HomeFragment_RecyclerView_Adapter_Hot.ViewHolder> {

    Context context;
    List<HomeFragment_HotBean.RxxpBean.CommodityListBean> mCommodityListBeans;

    public HomeFragment_RecyclerView_Adapter_Hot(Context context) {
        this.context = context;
        mCommodityListBeans = new ArrayList<>();
    }

    public void reset(List<HomeFragment_HotBean.RxxpBean.CommodityListBean> commodityList){
        mCommodityListBeans.clear();
        mCommodityListBeans.addAll(commodityList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.fragment_home_recyclerview_hot_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //List<HomeFragment_HotBean.ResultBean.RxxpBean.CommodityListBean> mCommodityList = mHotRxxpBeanList.get(i).getCommodityList();
       // Log.e("lk","asd"+mCommodityList);
        String CommodityName = mCommodityListBeans.get(i).getCommodityName();
        Log.e("lk","lk11"+CommodityName);
        /*String substring = CommodityName.substring(0,3);*/

        viewHolder.homefragment_hot_fresco.setImageURI(Uri.parse(mCommodityListBeans.get(i).getMasterPic()));
        viewHolder.homefragment_hot_textview_shop.setText(CommodityName);
        viewHolder.homefragment_hot_textview_price.setText("￥"+mCommodityListBeans.get(i).getPrice()+".00");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnIdClicklistener.success(mCommodityListBeans.get(i).getCommodityId()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCommodityListBeans.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView homefragment_hot_fresco;
        private TextView homefragment_hot_textview_shop;
        private TextView homefragment_hot_textview_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homefragment_hot_fresco = itemView.findViewById(R.id.homefragment_hot_fresco);
            homefragment_hot_textview_shop = itemView.findViewById(R.id.homefragment_hot_textview_shop);
            homefragment_hot_textview_price = itemView.findViewById(R.id.homefragment_hot_textview_price);
        }
    }
    public interface onIdClicklistener{
        void success(String id);
    }
    private onIdClicklistener mOnIdClicklistener;

    public void setOnIdClicklistener(onIdClicklistener onIdClicklistener) {
        mOnIdClicklistener = onIdClicklistener;
    }
}
