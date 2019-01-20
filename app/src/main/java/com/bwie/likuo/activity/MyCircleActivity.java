package com.bwie.likuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.ImageView;

import com.bwie.likuo.R;
import com.bwie.likuo.adapter.CircleFragment_RecyclerView_Adapter_End;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.CircleFragment_ShowCircle;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.persenter.CircleFragmentPresenter;
import com.bwie.likuo.persenter.MyCirclePresenter;
import com.bwie.likuo.utils.SpaceItemDecoration;
import com.greendao.gen.UserDaoDao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCircleActivity extends BaseActivity {


    @BindView(R.id.fragment_circle_recyclerview)
    XRecyclerView fragmentCircleRecyclerview;
    public static boolean addCircle;//如果添加成功，则为true

    private MyCirclePresenter mCirclePresenter;
    int page = 1;
    private CircleFragment_RecyclerView_Adapter_End mCircleFragment_recyclerView_adapter_end;
    @Override
    protected int contentView() {
        return R.layout.activity_my_circle;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_circle)
    public void addCircle(){
        Intent intent = new Intent(this,PublishCircleActivity.class);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        if (addCircle){//publish new message,so you have to refresh
            addCircle = false;
            mCircleFragment_recyclerView_adapter_end.notifyDataSetChanged();//////////////////////////
        }
    }



    @Override
    protected void initData() {
        fragmentCircleRecyclerview.setLayoutManager(new LinearLayoutManager(this,OrientationHelper.VERTICAL,false));
        fragmentCircleRecyclerview.addItemDecoration(new SpaceItemDecoration(5));
        mCircleFragment_recyclerView_adapter_end = new CircleFragment_RecyclerView_Adapter_End(this);
        fragmentCircleRecyclerview.setAdapter(mCircleFragment_recyclerView_adapter_end);
        request(page);
        fragmentCircleRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                request(page);
                mCircleFragment_recyclerView_adapter_end.clear();
                fragmentCircleRecyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                request(page);
                /*mCircleFragment_recyclerView_adapter_end.notifyDataSetChanged();*/
                fragmentCircleRecyclerview.loadMoreComplete();
            }
        });
    }
    private void request(int page) {
        UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        List<UserDao> userDaos = userDaoDao.loadAll();
        int userId = userDaos.get(0).getUserId();
        String sessionId = userDaos.get(0).getSessionId();

        mCirclePresenter = new MyCirclePresenter(new CircleCall());
        mCirclePresenter.reqeust(userId,sessionId,5, page);
    }
    private class CircleCall implements DataCall<Result<List<CircleFragment_ShowCircle>>> {
        @Override
        public void success(Result<List<CircleFragment_ShowCircle>> data) {
            List<CircleFragment_ShowCircle> result = data.getResult();
            mCircleFragment_recyclerView_adapter_end.reset(result);
        }

        @Override
        public void fail(ApiException e) {

        }
    }


}
