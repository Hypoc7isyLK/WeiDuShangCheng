package com.bwie.likuo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.bean.Indent_All_Order_Bean;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/15
 * author:李阔(淡意衬优柔)
 * function:
 */
public class CompleteIndent_RecyclerView_Adapter extends RecyclerView.Adapter<CompleteIndent_RecyclerView_Adapter.MyViewHolder> {

    List<Indent_All_Order_Bean> list;
    Context context;

    public CompleteIndent_RecyclerView_Adapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void reset(List<Indent_All_Order_Bean> orderList) {
        list.clear();
        list.addAll(orderList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取布局文件
        View inflate = View.inflate(context, R.layout.completeindent_recyclerview_item, null);
        ScreenAdapterTools.getInstance().loadView(inflate);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        List<Indent_All_Order_Bean.DetailListBean> detailList = list.get(i).getDetailList();
        myViewHolder.completeindent_recyclerview_item_hao.setText(list.get(i).getOrderId()+"");

        //myViewHolder.completeindent_recyclerview_item_price.setText("共"+list.get(i).getDetailList()+"件商品，总计元￥："+list.get(i).getDetailList().get(0).getCommodityPrice()+"");
        //Log.e("lk","yi共"+list.get(i).getDetailList().size());
        //Log.e("lk","共$"+list.get(i).getDetailList().get(i).getCommodityPrice());





        CompleteIndent_RecyclerView_RecyclerView_Adapter completeIndent_recyclerView_recyclerView_adapter = new CompleteIndent_RecyclerView_RecyclerView_Adapter(detailList, context);
        myViewHolder.completeindent_recyclerview_item_recyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        myViewHolder.completeindent_recyclerview_item_recyclerview.setAdapter(completeIndent_recyclerView_recyclerView_adapter);


        //myViewHolder.completeindent_recyclerview_item_time.setText(list.get(i).get+"");
          // new CompleteIndent_RecyclerView_RecyclerView_Adapter();
        //myViewHolder.completeindent_recyclerview_item_recyclerview.setAdapter();



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView completeindent_recyclerview_item_hao;
        public TextView completeindent_recyclerview_item_time;
        public TextView completeindent_recyclerview_item_quxiao;
        public RecyclerView completeindent_recyclerview_item_recyclerview;
        //public TextView completeindent_recyclerview_item_price;
        public Button completeindent_recyclerview_item_zhifu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.completeindent_recyclerview_item_hao = (TextView) itemView.findViewById(R.id.completeindent_recyclerview_item_hao);
            this.completeindent_recyclerview_item_time = (TextView) itemView.findViewById(R.id.completeindent_recyclerview_item_time);
            this.completeindent_recyclerview_item_quxiao = (TextView) itemView.findViewById(R.id.completeindent_recyclerview_item_quxiao);
            this.completeindent_recyclerview_item_recyclerview = (RecyclerView) itemView.findViewById(R.id.completeindent_recyclerview_item_recyclerview);
            //this.completeindent_recyclerview_item_price = (TextView) itemView.findViewById(R.id.completeindent_recyclerview_item_price);
            this.completeindent_recyclerview_item_zhifu = (Button) itemView.findViewById(R.id.completeindent_recyclerview_item_zhifu);
        }
    }
}
