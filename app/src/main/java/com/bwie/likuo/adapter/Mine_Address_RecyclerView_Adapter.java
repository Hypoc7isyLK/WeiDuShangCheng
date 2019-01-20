package com.bwie.likuo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.bwie.likuo.R;
import com.bwie.likuo.bean.Mine_Address_RecyclerView_Bean;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/14
 * author:李阔(淡意衬优柔)
 * function:
 */
public class Mine_Address_RecyclerView_Adapter extends RecyclerView.Adapter<Mine_Address_RecyclerView_Adapter.MyViewHolder> {
    List<Mine_Address_RecyclerView_Bean> list;
    Context context;

    public Mine_Address_RecyclerView_Adapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void reSet(List<Mine_Address_RecyclerView_Bean> result) {
        list.clear();
        list.addAll(result);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //获取布局文件
        View inflate = View.inflate(context, R.layout.fragment_mine_address_recyclerview_item, null);
        ScreenAdapterTools.getInstance().loadView(inflate);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.mine_adddress_item_adrs.setText(list.get(i).getAddress()+"");
        myViewHolder.mine_adddress_item_name.setText(list.get(i).getRealName());
        myViewHolder.mine_adddress_item_phone.setText(list.get(i).getPhone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mine_adddress_item_name;
        public TextView mine_adddress_item_phone;
        public TextView mine_adddress_item_adrs;
        public RadioButton mine_adddress_item_moren;
        public Button mine_adddress_item_updata;
        public Button mine_adddress_item_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mine_adddress_item_name = (TextView) itemView.findViewById(R.id.mine_adddress_item_name);
            this.mine_adddress_item_phone = (TextView) itemView.findViewById(R.id.mine_adddress_item_phone);
            this.mine_adddress_item_adrs = (TextView) itemView.findViewById(R.id.mine_adddress_item_adrs);
            this.mine_adddress_item_moren = (RadioButton) itemView.findViewById(R.id.mine_adddress_item_moren);
            this.mine_adddress_item_updata = (Button) itemView.findViewById(R.id.mine_adddress_item_updata);
            this.mine_adddress_item_delete = (Button) itemView.findViewById(R.id.mine_adddress_item_delete);
        }
    }

}
