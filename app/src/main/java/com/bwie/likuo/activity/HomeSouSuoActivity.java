package com.bwie.likuo.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.adapter.HomeFragment_SearchXRecyclearView_Adapter;
import com.bwie.likuo.bean.HomeFragment_SearchBean;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.persenter.HomeFragmentSearchPresenter;
import com.bwie.likuo.utils.SpaceItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSouSuoActivity extends BaseActivity {

    @BindView(R.id.search_back)
    ImageView searchBack;
    @BindView(R.id.search_editText)
    EditText searchEditText;
    @BindView(R.id.search_btn)
    TextView searchBtn;
    @BindView(R.id.search_recyclerview)
    XRecyclerView searchRecyclerview;
    @BindView(R.id.linner)
    LinearLayout linner;

    int page = 1;

    private HomeFragment_SearchXRecyclearView_Adapter mHomeFragment_searchXRecyclearView_adapter;
    private List<HomeFragment_SearchBean> result;

    @Override
    protected int contentView() {

        return R.layout.activity_home_sou_suo;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.search_back, R.id.search_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_btn:
                final String shop = searchEditText.getText().toString().trim();
                searchRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
                searchRecyclerview.addItemDecoration(new SpaceItemDecoration(5));
                mHomeFragment_searchXRecyclearView_adapter = new HomeFragment_SearchXRecyclearView_Adapter(this);
                searchRecyclerview.setAdapter(mHomeFragment_searchXRecyclearView_adapter);
                sousuo(shop, page);
                searchRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        sousuo(shop, page);
                        mHomeFragment_searchXRecyclearView_adapter.clear();
                        searchRecyclerview.refreshComplete();

                    }

                    @Override
                    public void onLoadMore() {
                        page++;
                        sousuo(shop, page);
                        mHomeFragment_searchXRecyclearView_adapter.notifyDataSetChanged();
                        searchRecyclerview.loadMoreComplete();
                    }
                });
                break;
        }
    }

    private void sousuo(String shop, int page) {

        HomeFragmentSearchPresenter homeFragmentSearchPresenter = new HomeFragmentSearchPresenter(new SearchCall());
        homeFragmentSearchPresenter.reqeust(shop, 6, page);

    }

    private class SearchCall implements DataCall<Result<List<HomeFragment_SearchBean>>> {
        @Override
        public void success(Result<List<HomeFragment_SearchBean>> data) {
                result = data.getResult();
                mHomeFragment_searchXRecyclearView_adapter.reset(result);
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
