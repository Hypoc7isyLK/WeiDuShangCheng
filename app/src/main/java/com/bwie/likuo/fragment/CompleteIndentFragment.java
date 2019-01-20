package com.bwie.likuo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.likuo.R;
import com.bwie.likuo.adapter.CompleteIndent_RecyclerView_Adapter;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.Indent_All_Order_Bean;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.persenter.AllOrderPresenter;
import com.greendao.gen.UserDaoDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompleteIndentFragment extends BaseFragment {


    @BindView(R.id.completeindent_recyclerview)
    RecyclerView completeindentRecyclerview;
    Unbinder unbinder;
    private CompleteIndent_RecyclerView_Adapter mCompleteIndent_recyclerView_adapter;
    private AllOrderPresenter mAllOrderPresenter;

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        completeindentRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false));
        mCompleteIndent_recyclerView_adapter = new CompleteIndent_RecyclerView_Adapter(getActivity());
        completeindentRecyclerview.setAdapter(mCompleteIndent_recyclerView_adapter);
    }

    @Override
    public void initData(View view) {
        UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        List<UserDao> userDaos = userDaoDao.loadAll();
        UserDao userDao = userDaos.get(0);
        int userId = userDao.getUserId();
        String sessionId = userDao.getSessionId();
        mAllOrderPresenter = new AllOrderPresenter(new AllOrderCall());
        mAllOrderPresenter.reqeust(userId,sessionId,0,20,1);
    }

    @Override
    public int getContent() {
        return R.layout.fragment_complete_indent;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class AllOrderCall implements DataCall<Result<List<Indent_All_Order_Bean>>> {
        @Override
        public void success(Result<List<Indent_All_Order_Bean>> data) {
            List<Indent_All_Order_Bean> orderList = data.getOrderList();
            mCompleteIndent_recyclerView_adapter.reset(orderList);
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
