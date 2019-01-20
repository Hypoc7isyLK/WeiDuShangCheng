package com.bwie.likuo.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.likuo.R;
import com.bwie.likuo.activity.PaymentActivity;
import com.bwie.likuo.adapter.ShoppingFragment_Adapter;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.Cart_List_Bean;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.persenter.CartPresenter;
import com.greendao.gen.UserDaoDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.shopping_recy)
    RecyclerView shopping_recy;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.shopping_qx)
    CheckBox shopping_qx;
    @BindView(R.id.shopping_price)
    TextView shopping_price;
    @BindView(R.id.shopping_button)
    Button shopping_button;
    @BindView(R.id.linear)
    LinearLayout linear;
    Unbinder unbinder;

    ShoppingFragment_Adapter mAdapter;
    private CartPresenter mCartPresenter;

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        shopping_qx.setOnClickListener(this);
        shopping_button.setOnClickListener(this);
    }

    @Override
    public void initData(View view) {
        UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        List<UserDao> userDaos = userDaoDao.loadAll();
        int userId = userDaos.get(0).getUserId();
        String sessionId = userDaos.get(0).getSessionId();

        mAdapter = new ShoppingFragment_Adapter(getActivity());
        mCartPresenter = new CartPresenter(new CartCall());
        mCartPresenter.reqeust(userId,sessionId);

        shopping_recy.setAdapter(mAdapter);
        shopping_recy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public int getContent() {
        return R.layout.fragment_shopping;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shopping_button:
                //判断商品是否被选中
                boolean isChcek=mAdapter.isAllGoodsIsSelected1();
                if(isChcek){
                    //跳转到结算页面
                    Intent intent=new Intent(getActivity(),PaymentActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getActivity(),"至少选择一件商品进行支付",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.shopping_qx:

                boolean checked = shopping_qx.isChecked();
                mAdapter.setAllGoodsIsSelected(checked);
                mAdapter.notifyDataSetChanged();

                alterPrices();
                break;

        }
    }

    private class CartCall implements DataCall<Result<List<Cart_List_Bean>>> {
        @Override
        public void success(Result<List<Cart_List_Bean>> data) {
            List<Cart_List_Bean> result = data.getResult();
            if(result == null){

            }else {
                mAdapter.setData(result);
                //接口回调 调用adapter方法
                mAdapter.setCartListener(new ShoppingFragment_Adapter.ShoppingListener() {

                    @Override
                    public void GoodsChange() {
                        boolean isAllOk = mAdapter.isAllGoodsIsSelected();
                        shopping_qx.setChecked(isAllOk);
                        //刷新适配器
                        mAdapter.notifyDataSetChanged();
                        alterPrices();
                    }

                    @Override
                    public void NumChange(int index, int num) {
                        mAdapter.changeGoodsNum(index, num);
                        alterPrices();
                    }


                });
            }
        }


        @Override
        public void fail(ApiException e) {

        }
    }

    //当数量和选中状态改变时，商品价格发生改变
    private void alterPrices() {
        //价格
        float zongji=mAdapter.setZongji();
        shopping_price.setText("￥"+zongji);
    }
}
