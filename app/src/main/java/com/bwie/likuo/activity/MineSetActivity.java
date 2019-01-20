package com.bwie.likuo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bwie.likuo.MainActivity;
import com.bwie.likuo.R;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.core.dao.UserDao;
import com.greendao.gen.UserDaoDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineSetActivity extends BaseActivity {


    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.logout_btn)
    Button logoutBtn;

    @Override
    protected int contentView() {
        return R.layout.activity_mine_set;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.logout_btn)
    public void onViewClicked() {
        UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        userDaoDao.deleteAll();
        //Intent清除栈FLAG_ACTIVITY_CLEAR_TASK会把当前栈内所有Activity清空；
        //FLAG_ACTIVITY_NEW_TASK配合使用，才能完成跳转
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
