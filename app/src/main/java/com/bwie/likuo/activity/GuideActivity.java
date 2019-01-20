package com.bwie.likuo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bwie.likuo.MainActivity;
import com.bwie.likuo.R;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.core.dao.UserDao;
import com.greendao.gen.UserDaoDao;

import java.util.List;

public class GuideActivity extends BaseActivity {


    private TextView main_countDown;
    /*private TextView main_skip;*/
    private Button main_go;
    private int temp = 5;

    private UserDaoDao mUserDaoDao;
    private List<UserDao> mUserDaos;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            temp--;
            if (temp <= 0){

                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }else {
                main_countDown.setText(temp + "S");
                mHandler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };


    @Override
    protected int contentView() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        main_countDown = findViewById(R.id.main_countDown);
        main_go = findViewById(R.id.main_go);
        mUserDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        mUserDaos = mUserDaoDao.loadAll();
    }

    @Override
    protected void initData() {
        //第一次进入显示倒计时页面  第二次进入时 直接进入登录页面 跳过倒计时页面
        //得到SharedPreferences
        /*SharedPreferences sharedPreferences = getSharedPreferences("lk",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean x = sharedPreferences.getBoolean("x",false);
        if (x) {
            startActivity(new Intent(GuideActivity.this, MainActivity.class));
            finish();
        }else {
            editor.putBoolean("x",true);
            editor.commit();
        }*/
        mHandler.sendEmptyMessage(1);
        main_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserDaos!=null){
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(GuideActivity.this, HomeActivity.class));
                    finish();
                }

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(1);

    }
}
