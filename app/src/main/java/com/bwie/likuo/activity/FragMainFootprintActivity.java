package com.bwie.likuo.activity;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bwie.likuo.R;
import com.bwie.likuo.adapter.MyFootprintActivity_RecyclerView;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.MyFootPrint_Bean;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.persenter.MineFootPrintPresenter;
import com.greendao.gen.UserDaoDao;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragMainFootprintActivity extends BaseActivity {


    @BindView(R.id.mine_foot_recyclerview)
    XRecyclerView mineFootRecyclerview;
    private MineFootPrintPresenter mMineFootPrintPresenter;
    int page = 1;
    private MyFootprintActivity_RecyclerView mMyFootprintActivity_recyclerView;

    @Override
    protected int contentView() {
        return R.layout.activity_frag_main_footprint;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mineFootRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL));


    }

    @Override
    protected void initData() {


        mMyFootprintActivity_recyclerView = new MyFootprintActivity_RecyclerView(this);
        mineFootRecyclerview.setAdapter(mMyFootprintActivity_recyclerView);

        request(page);

        mineFootRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                request(page);
                mMyFootprintActivity_recyclerView.clear();
                mineFootRecyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                request(page);
                mMyFootprintActivity_recyclerView.notifyDataSetChanged();
                mineFootRecyclerview.loadMoreComplete();

            }
        });

    }

    private void request(int page) {
        UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        List<UserDao> userDaos = userDaoDao.loadAll();
        UserDao userDao = userDaos.get(0);
        int userId = userDao.getUserId();
        String sessionId = userDao.getSessionId();
        mMineFootPrintPresenter = new MineFootPrintPresenter(new FootPrientCall());
        mMineFootPrintPresenter.reqeust(userId,sessionId,10, page);
    }


    private class FootPrientCall implements DataCall<Result<List<MyFootPrint_Bean>>> {
        @Override
        public void success(Result<List<MyFootPrint_Bean>> data) {
            List<MyFootPrint_Bean> result = data.getResult();
            mMyFootprintActivity_recyclerView.reset(result);
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
