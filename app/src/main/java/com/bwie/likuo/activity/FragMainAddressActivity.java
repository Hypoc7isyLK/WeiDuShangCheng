package com.bwie.likuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.adapter.Mine_Address_RecyclerView_Adapter;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.Mine_Address_RecyclerView_Bean;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.persenter.MineAddressPresenter;
import com.greendao.gen.UserDaoDao;

import java.util.List;

public class FragMainAddressActivity extends BaseActivity {


    private TextView mine_address_finish;
    private RecyclerView mine_address_recyclerview;
    private Button mine_address_add;
    private MineAddressPresenter mMineAddressPresenter;
    private UserDaoDao mUserDaoDao;
    private Mine_Address_RecyclerView_Adapter mMine_address_recyclerView_adapter;

    @Override
    protected int contentView() {
        return R.layout.activity_frag_main_address;
    }

    @Override
    protected void initView() {
        mine_address_add = findViewById(R.id.mine_address_add);
        mine_address_recyclerview = findViewById(R.id.mine_address_recyclerview);
        mine_address_finish = findViewById(R.id.mine_address_finish);

        mine_address_recyclerview.setLayoutManager(new LinearLayoutManager(FragMainAddressActivity.this,LinearLayoutManager.VERTICAL,false));

    }

    @Override
    protected void initData() {
        loadData();
        mMine_address_recyclerView_adapter = new Mine_Address_RecyclerView_Adapter(this);
        mine_address_recyclerview.setAdapter(mMine_address_recyclerView_adapter);
        mine_address_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(FragMainAddressActivity.this,HomeActivity.class));
                finish();
            }
        });
        //点击添加收货地址
        mine_address_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FragMainAddressActivity.this,Mine_Address_AddActivity.class));
            }
        });

    }

    private void loadData() {
        mUserDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        List<UserDao> userDaos = mUserDaoDao.loadAll();
        int userId = userDaos.get(0).getUserId();
        String sessionId = userDaos.get(0).getSessionId();
        mMineAddressPresenter = new MineAddressPresenter(new AddressCall());
        mMineAddressPresenter.reqeust(userId,sessionId);
    }


    private class AddressCall implements DataCall<Result<List<Mine_Address_RecyclerView_Bean>>> {
        @Override
        public void success(Result<List<Mine_Address_RecyclerView_Bean>> data) {
            List<Mine_Address_RecyclerView_Bean> result = data.getResult();
            mMine_address_recyclerView_adapter.reSet(result);
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
