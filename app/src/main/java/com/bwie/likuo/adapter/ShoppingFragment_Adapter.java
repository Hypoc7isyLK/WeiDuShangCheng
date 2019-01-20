package com.bwie.likuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import com.bwie.likuo.R;
import com.bwie.likuo.bean.Bean_Cart_Checkbox;
import com.bwie.likuo.bean.Cart_List_Bean;
import com.bwie.likuo.view.MyAddSubView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/13
 * author:李阔(淡意衬优柔)
 * function:
 */
public class ShoppingFragment_Adapter extends RecyclerView.Adapter<ShoppingFragment_Adapter.ViewHolder> {


    private List<Cart_List_Bean> list;
    Context mContext;
    List<Bean_Cart_Checkbox> ckList;

    public ShoppingFragment_Adapter(Context context) {
        mContext = context;
        list = new ArrayList<>();
        ckList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext,R.layout.mycart_shopping_item,null);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.icon.setImageURI(Uri.parse(list.get(i).getPic()));
        viewHolder.name.setText(list.get(i).getCommodityName());
        viewHolder.price.setText("￥"+list.get(i).getPrice());
        viewHolder.mAddSubView.setNumber(list.get(i).getCount());
        viewHolder.ck.setChecked(ckList.get(i).isCk());



        //点击商品回调
        viewHolder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCartListener!=null){
                    boolean isGoodsOk=viewHolder.ck.isChecked();
                    ckList.get(i).setCk(isGoodsOk);
                    mCartListener.GoodsChange();

                }
            }
        });
        //点击商品数量时
        viewHolder.mAddSubView.setOnNumberChangeListener(new MyAddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int num) {
                if(mCartListener!=null){
                    mCartListener.NumChange(i,num);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        if(list.size()>0){
            for (int i = 0; i <list.size(); i++) {
                ckList.add(new Bean_Cart_Checkbox(false));
                if(ckList.size()==list.size()){
                    break;
                }
            }
        }
        return list.size();
    }

    public void setData(List<Cart_List_Bean> result) {
        list = result;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView icon;
        private TextView name,price;
        private MyAddSubView mAddSubView;
        private CheckBox ck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.mycart_simple);
            name=itemView.findViewById(R.id.mycart_name);
            price=itemView.findViewById(R.id.mycart_price);
            ck=itemView.findViewById(R.id.mycart_checkbot);
            mAddSubView=itemView.findViewById(R.id.mycart_addsub);
        }
    }


    //计算总价
    public float setZongji() {
        float zong=0.0f;
        for (int i = 0; i <list.size() ; i++) {
            if(ckList.get(i).isCk()==true){
                int num=list.get(i).getCount();
                float price=list.get(i).getPrice();
                zong+=num*price;
            }
        }
        return zong;
    }
    //改变商品数量
    public void changeGoodsNum(int index, int num) {
        list.get(index).setCount(num);
    }

    public void setAllGoodsIsSelected(boolean b) {
        for (int i = 0; i < ckList.size(); i++) {
            ckList.get(i).setCk(b?true:false);
            Log.e("hahahaha"+i, b+"" );
        }
    }
    //是否所有商品被选中
    public boolean isAllGoodsIsSelected() {
        for (int i = 0; i < list.size(); i++) {
            if(ckList.get(i).isCk()==false){
                return false;
            }
        }
        return true;
    }

    //是否所有商品被选中
    public boolean isAllGoodsIsSelected1() {

      mCartListener.GoodsChange();

        for (int i = 0; i < list.size(); i++) {
            if(ckList.get(i).isCk()==false){
                return false;
            }
        }
        return true;
    }

    //设置商品数量
    public int setNum() {
        int num=0;
        for (int i = 0; i <list.size() ; i++) {
            if(ckList.get(i).isCk()==true){
                num+=list.get(i).getCount();
            }
        }
        return num;
    }

    //定义接口回调
    public interface ShoppingListener{

        //商品选中状态改变
        void GoodsChange();
        //商品数量改变
        void NumChange(int index, int num);
    }

    public ShoppingListener mCartListener;

    public void setCartListener(ShoppingListener cartListener) {
        mCartListener = cartListener;
    }
}
