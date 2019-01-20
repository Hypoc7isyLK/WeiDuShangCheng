package com.bwie.likuo.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.bwie.likuo.R;
import com.bwie.likuo.adapter.ImageAdapter;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.WDActivity;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.fragment.CircleFragment;
import com.bwie.likuo.persenter.PublishCirclePresenter;
import com.bwie.likuo.util.StringUtils;
import com.bwie.likuo.util.UIUtils;
import com.greendao.gen.UserDaoDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishCircleActivity extends WDActivity implements DataCall<Result> {


    @BindView(R.id.bo_text)
    EditText mText;

    PublishCirclePresenter presenter;


    ImageAdapter mImageAdapter;
    @BindView(R.id.bo_image_list)
    RecyclerView mImageList;



    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mImageAdapter = new ImageAdapter();
        mImageAdapter.setSign(1);
        mImageAdapter.add(R.drawable.mask_01);
        mImageList.setLayoutManager(new GridLayoutManager(this, 3));
        mImageList.setAdapter(mImageAdapter);

        presenter = new PublishCirclePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_circle;
    }

    @Override
    protected void destoryData() {

    }

    @OnClick(R.id.back)
    public void back() {
        finish();
    }


    @OnClick(R.id.send)
    public void publish() {
        UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        List<UserDao> userDaos = userDaoDao.loadAll();

        presenter.reqeust(userDaos.get(0).getUserId(),
                userDaos.get(0).getSessionId(),
                1, mText.getText().toString(), mImageAdapter.getList());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值

            String filePath = getFilePath(null, requestCode, data);
            if (!StringUtils.isEmpty(filePath)) {
                mImageAdapter.add(filePath);
                mImageAdapter.notifyDataSetChanged();
//                Bitmap bitmap = UIUtils.decodeFile(new File(filePath),200);
//                mImage.setImageBitmap(bitmap);
            }
        }

    }



    @Override
    public void success(Result data) {
        if (data.getStatus().equals("0000")) {
            CircleFragment.addCircle = true;
            finish();
        } else {
            UIUtils.showToastSafe(data.getStatus() + "  " + data.getMessage());
        }
    }

    @Override
    public void fail(ApiException e) {
        UIUtils.showToastSafe(e.getCode() + "  " + e.getDisplayMessage());
    }




}
