package com.bwie.likuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.bean.HomeFragment_SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/4
 * author:李阔(淡意衬优柔)
 * function:
 */
public class HomeFragment_SearchXRecyclearView_Adapter extends XRecyclerView.Adapter<HomeFragment_SearchXRecyclearView_Adapter.ViewHolder> {
    Context context;
    List<HomeFragment_SearchBean> mHomeFragment_searchBeans;


    public HomeFragment_SearchXRecyclearView_Adapter(Context context) {
        this.context = context;
        mHomeFragment_searchBeans = new ArrayList<>();
    }

    public void reset(List<HomeFragment_SearchBean> result) {
        mHomeFragment_searchBeans.addAll(result);
        notifyDataSetChanged();
    }

    public void clear() {
        mHomeFragment_searchBeans.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.serchview_xre_grid_serch_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.search_item_name.setText(mHomeFragment_searchBeans.get(i).getCommodityName());
        viewHolder.search_item_num.setText(mHomeFragment_searchBeans.get(i).getSaleNum()+"");
        viewHolder.search_item_price.setText("￥"+mHomeFragment_searchBeans.get(i).getPrice()+".00");
        viewHolder.search_item_img.setImageURI(Uri.parse(mHomeFragment_searchBeans.get(i).getMasterPic()));
    }

    @Override
    public int getItemCount() {
        return mHomeFragment_searchBeans.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView search_item_img;
        private TextView search_item_name;
        private TextView search_item_price;
        private TextView search_item_num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            search_item_img = itemView.findViewById(R.id.search_item_img);
            search_item_name = itemView.findViewById(R.id.search_item_name);
            search_item_price = itemView.findViewById(R.id.search_item_price);
            search_item_num = itemView.findViewById(R.id.search_item_num);
        }
    }
}
