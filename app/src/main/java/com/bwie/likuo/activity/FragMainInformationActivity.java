package com.bwie.likuo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.likuo.R;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.fragment.MineFragment;
import com.bwie.likuo.persenter.MineUpdateNamePresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.greendao.gen.UserDaoDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragMainInformationActivity extends BaseActivity {

    @BindView(R.id.mine_image_head)
    SimpleDraweeView mineImageHead;
    @BindView(R.id.information_name)
    TextView informationName;
    @BindView(R.id.information_password)
    TextView informationPassword;
    @BindView(R.id.mydata_back)
    ImageView mydataBack;
    private UserDaoDao userDaoDao;
    private List<UserDao> userDaos;
    private UserDao userDao;
    private MineUpdateNamePresenter mMineUpdateNamePresenter;
    private String nikeName;

    @Override
    protected int contentView() {
        return R.layout.activity_frag_main_information;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        userDaos = userDaoDao.loadAll();
        userDao = userDaos.get(0);
        String headPic = userDao.getHeadPic();
        String nickName = userDao.getNickName();
        String pwd = userDao.getUserpwd();

        mineImageHead.setImageURI(headPic);
        informationName.setText(nickName);
        informationPassword.setText(pwd);

    }


    @OnClick({R.id.mydata_back, R.id.mine_image_head, R.id.information_name, R.id.information_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mydata_back:
                finish();
                break;
            case R.id.mine_image_head:

                break;
            case R.id.information_name:
                final EditText name = new EditText(this);
                new AlertDialog.Builder(this).setTitle("请输入昵称")
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(name)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                                nikeName = name.getText().toString().trim();
                                requestNetData(nikeName);


                            }
                        }).setNegativeButton("取消",null).show();

                break;
            case R.id.information_password:

                break;
        }
    }

    private void requestNetData(String nikeName) {
        mMineUpdateNamePresenter = new MineUpdateNamePresenter(new UpdatName());
        mMineUpdateNamePresenter.reqeust(userDao.getUserId(),userDao.getSessionId(),nikeName);
    }


    private class UpdatName implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getMessage().equals("修改成功")) {
                Toast.makeText(FragMainInformationActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                informationName.setText(nikeName);
                /*Intent intent = new Intent(FragMainInformationActivity.this,MineFragment.class);
                intent.putExtra("nikeName",nikeName);*/

            }else {
                Toast.makeText(FragMainInformationActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
