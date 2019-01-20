package com.bwie.likuo.fragment;



import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bwie.likuo.R;
import com.bwie.likuo.activity.DetailsActivity;
import com.bwie.likuo.activity.HomeSouSuoActivity;
import com.bwie.likuo.adapter.HomeFragment_RecyclerView_Adapter_Fashion;
import com.bwie.likuo.adapter.HomeFragment_RecyclerView_Adapter_Hot;
import com.bwie.likuo.adapter.HomeFragment_RecyclerView_Adapter_Life;

import com.bwie.likuo.bean.Banner;
import com.bwie.likuo.bean.HomeFragment_BannerBean;
import com.bwie.likuo.bean.HomeFragment_HotBean;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.persenter.BannerPresenter;
import com.bwie.likuo.persenter.HomeFragmentPresenter;
import com.bwie.likuo.utils.SpaceItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private MZBannerView homefragment_viewpager;
    private ImageView homefragment_img_menu;
    private ImageView homefragment_img_search;
    private RecyclerView homefragment_recyclerview_hot;
    private RecyclerView homefragment_recyclerview_line;
    private RecyclerView homefragment_recyclerview_grid;
    private LinearLayout llllllll;


    HomeFragment_RecyclerView_Adapter_Hot mHomeFragment_recyclerView_adapter_hot;
    private HomeFragment_RecyclerView_Adapter_Fashion mHomeFragment_recyclerView_adapter_fashion;
    private HomeFragment_RecyclerView_Adapter_Life mHomeFragment_recyclerView_adapter_life;
    private BannerPresenter bannerPresenter;
    private HomeFragmentPresenter rxxpPresenter;
    private HomeFragmentPresenter mHomeFragmentPresenter;
    private HomeFragmentPresenter homeFragmentPresenter;




    @Override
    public void initView(View view) {
        homefragment_img_menu = view.findViewById(R.id.homefragment_img_menu);
        homefragment_img_search = view.findViewById(R.id.homefragment_img_search);

        homefragment_viewpager = view.findViewById(R.id.homefragment_viewpager);
        llllllll = view.findViewById(R.id.llllllll);

        homefragment_recyclerview_hot = view.findViewById(R.id.homefragment_recyclerview_hot);
        homefragment_recyclerview_hot.setLayoutManager(new GridLayoutManager(getActivity(),3));


        homefragment_recyclerview_line = view.findViewById(R.id.homefragment_recyclerview_line);
        homefragment_recyclerview_line.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false));

        homefragment_recyclerview_grid = view.findViewById(R.id.homefragment_recyclerview_grid);
        homefragment_recyclerview_grid.setLayoutManager(new GridLayoutManager(getActivity(),2));

    }

    @Override
    public void initData(View view) {
        bannerImage();//轮播图
        hotProducts();//热销新品
        magicfashion();//魔丽时尚
        goodLife();//品质生活
        onClickListener();
        search();
    }

    private void onClickListener() {
        //   menu的点击事件，弹出popupwind
        homefragment_img_menu.setOnClickListener(new View.OnClickListener() {
            private RadioGroup popupwindow_item_class;
            private RadioGroup popupwindow_item_title;
            @Override
            public void onClick(View view) {
                //获取布局文件中控件
                final View popup_item = View.inflate(getActivity(), R.layout.popupwindow_item, null);
                PopupWindow window = new PopupWindow(popup_item, 1080, 300, true);
                window.setBackgroundDrawable(new ColorDrawable());//设置背景
                window.setOutsideTouchable(true);//
                window.setTouchable(true);//
                window.showAsDropDown(homefragment_img_menu);//弹出的位置在menu的下面

                popupwindow_item_class = popup_item.findViewById(R.id.popupwindow_item_class);
                popupwindow_item_title = popup_item.findViewById(R.id.popupwindow_item_title);
                //当title点击的时候
                popupwindow_item_class.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton child = popup_item.findViewById(popupwindow_item_class.getCheckedRadioButtonId());
                        Toast.makeText(getActivity(), "" + child.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), HomeSouSuoActivity.class);
                        intent.putExtra("name", child.getText());
                        startActivity(intent);
                    }
                });
                //点击跳转详细的商品信息界面
                popupwindow_item_title.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton child = popup_item.findViewById(popupwindow_item_title.getCheckedRadioButtonId());
                        Toast.makeText(getActivity(), "" + child.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), HomeSouSuoActivity.class);
                        intent.putExtra("name", child.getText());
                        startActivity(intent);
                    }
                });

            }
        });
    }

    private void search() {
        homefragment_img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeSouSuoActivity.class));
            }
        });
    }

    private void goodLife() {
        mHomeFragment_recyclerView_adapter_life = new HomeFragment_RecyclerView_Adapter_Life(getActivity());
        homefragment_recyclerview_grid.addItemDecoration(new SpaceItemDecoration(10));
        homefragment_recyclerview_grid.setAdapter(mHomeFragment_recyclerView_adapter_life);
        homeFragmentPresenter = new HomeFragmentPresenter(new PzshCall());
        homeFragmentPresenter.reqeust();
    }

    private void magicfashion() {
        mHomeFragment_recyclerView_adapter_fashion = new HomeFragment_RecyclerView_Adapter_Fashion(getActivity());
        homefragment_recyclerview_line.addItemDecoration(new SpaceItemDecoration(10));
        homefragment_recyclerview_line.setAdapter(mHomeFragment_recyclerView_adapter_fashion);
        mHomeFragmentPresenter = new HomeFragmentPresenter(new MossCall());
        mHomeFragmentPresenter.reqeust();


    }


    private void bannerImage() {

        bannerPresenter = new BannerPresenter(new BannerCall());
        bannerPresenter.reqeust();

    }

    class BannerViewHolder implements MZViewHolder<HomeFragment_BannerBean> {
        private SimpleDraweeView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, HomeFragment_BannerBean homeFragment_bannerBean) {
            mImageView.setImageURI(Uri.parse(homeFragment_bannerBean.getImageUrl()));
        }


    }

    private void hotProducts() {
        mHomeFragment_recyclerView_adapter_hot = new HomeFragment_RecyclerView_Adapter_Hot(getActivity());
        homefragment_recyclerview_hot.addItemDecoration(new SpaceItemDecoration(10));
        homefragment_recyclerview_hot.setAdapter(mHomeFragment_recyclerView_adapter_hot);

        rxxpPresenter = new HomeFragmentPresenter(new RxxpCall());
        rxxpPresenter.reqeust();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        homefragment_viewpager.pause();
    }



    private class BannerCall implements DataCall<Result<List<HomeFragment_BannerBean>>> {
        @Override
        public void success(Result<List<HomeFragment_BannerBean>> data) {
            if (data.getStatus().equals("0000")){
                homefragment_viewpager.setIndicatorVisible(false);
                homefragment_viewpager.setPages(data.getResult(), new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                });
                homefragment_viewpager.start();
            }



        }

        @Override
        public void fail(ApiException e) {

        }
    }


    @Override
    public int getContent() {
        return R.layout.fragment_home;
    }

    private class RxxpCall implements DataCall<Result<HomeFragment_HotBean>> {

        private HomeFragment_HotBean mResult;
        private List<HomeFragment_HotBean.RxxpBean> mRxxp;
        private List<HomeFragment_HotBean.RxxpBean.CommodityListBean> commodityList;

        @Override
        public void success(Result<HomeFragment_HotBean> data) {
            mResult = data.getResult();
            mRxxp = mResult.getRxxp();
            commodityList = mRxxp.get(0).getCommodityList();

            mHomeFragment_recyclerView_adapter_hot.reset(commodityList);
            mHomeFragment_recyclerView_adapter_hot.setOnIdClicklistener(new HomeFragment_RecyclerView_Adapter_Hot.onIdClicklistener() {
                @Override
                public void success(String id) {
                    Intent intent = new Intent(getActivity(),DetailsActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }

        @Override
        public void fail(ApiException e) {

        }
    }


    private class MossCall implements DataCall<Result<HomeFragment_HotBean>> {

        private List<HomeFragment_HotBean.MlssBean> mMlss;
        private List<HomeFragment_HotBean.MlssBean.CommodityListBeanXX> mCommodityList;

        @Override
        public void success(Result<HomeFragment_HotBean> data) {
            mMlss = data.getResult().getMlss();
            mCommodityList = mMlss.get(0).getCommodityList();
            mHomeFragment_recyclerView_adapter_fashion.reset(mCommodityList);
            mHomeFragment_recyclerView_adapter_fashion.setOnIdClicklistener(new HomeFragment_RecyclerView_Adapter_Fashion.onIdClicklistener() {
                @Override
                public void success(String id) {
                    Intent intent = new Intent(getActivity(),DetailsActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class PzshCall implements DataCall<Result<HomeFragment_HotBean>> {

        private List<HomeFragment_HotBean.PzshBean.CommodityListBeanX> mCommodityList;

        @Override
        public void success(Result<HomeFragment_HotBean> data) {
            mCommodityList = data.getResult().getPzsh().get(0).getCommodityList();
            mHomeFragment_recyclerView_adapter_life.reset(mCommodityList);
            mHomeFragment_recyclerView_adapter_life.setOnIdClicklistener(new HomeFragment_RecyclerView_Adapter_Life.onIdClicklistener() {
                @Override
                public void success(String id) {
                    Intent intent = new Intent(getActivity(),DetailsActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bannerPresenter = null;
        rxxpPresenter = null;
        mHomeFragmentPresenter = null;
        homeFragmentPresenter = null;

    }
}
