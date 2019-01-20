package com.bwie.likuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bwie.likuo.R;
import com.bwie.likuo.bean.Indent_All_Order_Bean;
import com.bwie.likuo.view.MyAddSubView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * date:2018/12/15
 * author:李阔(淡意衬优柔)
 * function:
 */
public class CompleteIndent_RecyclerView_RecyclerView_Adapter extends RecyclerView.Adapter<CompleteIndent_RecyclerView_RecyclerView_Adapter.MyViewHolder> {

    List<Indent_All_Order_Bean.DetailListBean> list;
    Context context;

    public CompleteIndent_RecyclerView_RecyclerView_Adapter(List<Indent_All_Order_Bean.DetailListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.completeindent_recyclerview_item_recyclerview, null);
        ScreenAdapterTools.getInstance().loadView(inflate);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    /**
     * @param myViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.childorde_item_name.setText(list.get(i).getCommodityName()+"");
        myViewHolder.childorde_item_price.setText("￥"+list.get(i).getCommodityPrice()+"");
        myViewHolder.childorde_item_addsub.setNumber(list.get(i).getCommodityCount());
        String commodityPic = list.get(i).getCommodityPic();
        String[] split = commodityPic.split(",");
        Uri parse = Uri.parse(split[0]);
        myViewHolder.childorde_item_image.setImageURI(parse);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView childorde_item_image;
        public TextView childorde_item_name;
        public TextView childorde_item_price;
        public MyAddSubView childorde_item_addsub;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.childorde_item_image = (SimpleDraweeView) itemView.findViewById(R.id.childorde_item_image);
            this.childorde_item_name = (TextView) itemView.findViewById(R.id.childorde_item_name);
            this.childorde_item_price = (TextView) itemView.findViewById(R.id.childorde_item_price);
            this.childorde_item_addsub = (MyAddSubView) itemView.findViewById(R.id.childorde_item_addsub);
        }
    }
}
