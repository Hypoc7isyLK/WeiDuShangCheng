package com.bwie.likuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.bean.MyFootPrint_Bean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * date:2018/12/15
 * author:李阔(淡意衬优柔)
 * function:
 */
public class MyFootprintActivity_RecyclerView extends RecyclerView.Adapter<MyFootprintActivity_RecyclerView.ViewHolder> {
    Context context;
    List<MyFootPrint_Bean> mResultBeans;


    public MyFootprintActivity_RecyclerView(Context context) {
        this.context = context;
        mResultBeans = new ArrayList<>();
    }

    public void reset(List<MyFootPrint_Bean> result) {
        mResultBeans.addAll(result);
        notifyDataSetChanged();
    }

    public void clear() {
        mResultBeans.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.mine_fragment_foot_item, null);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.foot_simpledraweeview.setImageURI(Uri.parse(mResultBeans.get(i).getMasterPic()));
        viewHolder.foot_textview_name.setText(mResultBeans.get(i).getCommodityName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        viewHolder.foot_textview_time.setText(simpleDateFormat.format(mResultBeans.get(i).getBrowseTime()));
        viewHolder.foot_textview_price.setText("￥"+mResultBeans.get(i).getPrice()+".00");
        viewHolder.foot_textview_liu.setText("已浏览"+mResultBeans.get(i).getBrowseNum()+"次");
    }

    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView foot_simpledraweeview;
        private TextView foot_textview_name;
        private TextView foot_textview_price;
        private TextView foot_textview_liu;
        private TextView foot_textview_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foot_simpledraweeview = itemView.findViewById(R.id.foot_simpledraweeview);
            foot_textview_name = itemView.findViewById(R.id.foot_textview_name);
            foot_textview_price = itemView.findViewById(R.id.foot_textview_price);
            foot_textview_liu = itemView.findViewById(R.id.foot_textview_liu);
            foot_textview_time = itemView.findViewById(R.id.foot_textview_time);
        }
    }
}
