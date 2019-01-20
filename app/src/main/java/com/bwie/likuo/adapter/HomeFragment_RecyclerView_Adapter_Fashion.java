package com.bwie.likuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bwie.likuo.R;
import com.bwie.likuo.bean.HomeFragment_HotBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/01/03
 * author:李阔(淡意衬优柔)
 * function:
 */
public class HomeFragment_RecyclerView_Adapter_Fashion extends RecyclerView.Adapter<HomeFragment_RecyclerView_Adapter_Fashion.ViewHolder> {
    Context context;
    List<HomeFragment_HotBean.MlssBean.CommodityListBeanXX> mCommodityListBeanXXES;

    public HomeFragment_RecyclerView_Adapter_Fashion(Context context) {
        this.context = context;
        mCommodityListBeanXXES = new ArrayList<>();
    }

    public void reset(List<HomeFragment_HotBean.MlssBean.CommodityListBeanXX> commodityList) {
        mCommodityListBeanXXES.clear();
        mCommodityListBeanXXES.addAll(commodityList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.homefrag_re_line_item, null);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.home_re_line_img.setImageURI(Uri.parse(mCommodityListBeanXXES.get(i).getMasterPic()));
        viewHolder.home_re_line_name.setText(mCommodityListBeanXXES.get(i).getCommodityName());
        viewHolder.home_re_line_price.setText("￥"+mCommodityListBeanXXES.get(i).getPrice()+".00");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnIdClicklistener.success(mCommodityListBeanXXES.get(i).getCommodityId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCommodityListBeanXXES.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView home_re_line_img;
        private TextView home_re_line_name;
        private TextView home_re_line_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_re_line_img = itemView.findViewById(R.id.home_re_line_img);
            home_re_line_name = itemView.findViewById(R.id.home_re_line_name);
            home_re_line_price = itemView.findViewById(R.id.home_re_line_price);
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
