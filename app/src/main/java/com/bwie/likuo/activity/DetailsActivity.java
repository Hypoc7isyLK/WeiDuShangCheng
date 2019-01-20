package com.bwie.likuo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.likuo.R;
import com.bwie.likuo.adapter.RecycleView_Adapter_DetailsComments;
import com.bwie.likuo.adapter.ShoppingFragment_Adapter;
import com.bwie.likuo.adapter.ViewPager_Adapter_DetailsBanner;
import com.bwie.likuo.app.MyApplication;
import com.bwie.likuo.bean.DetailsBean;
import com.bwie.likuo.bean.DetailsCommentsBeans;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.dao.UserDao;
import com.bwie.likuo.core.exception.ApiException;
import com.bwie.likuo.persenter.AddCartPresenter;
import com.bwie.likuo.persenter.DetailsCommentsPresenter;
import com.bwie.likuo.persenter.DetailsPresent;
import com.bwie.likuo.view.MyScrollView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.greendao.gen.UserDaoDao;

import java.util.List;

public class DetailsActivity extends BaseActivity {


    private DetailsPresent mDetailsPresent;
    private UserDaoDao mUserDaoDao;
    private List<UserDao> mUserDaos;
    private ViewPager details_viewpager_show;
    private TextView details_textview_shownum;
    private TextView details_textview_price;
    private TextView details_textview_sold;
    private TextView details_textview_title;
    private TextView details_textview_Weight;
    private SimpleDraweeView details_Image_details;
    private TextView details_textview_describe;
    private SimpleDraweeView details_Image_describe;
    private RecyclerView details_recview_comments;
    private TextView details_textview_comments;
    private MyScrollView details_scroll_changecolor;
    private ImageView details_image_return;
    private TextView details_text_goodsT;
    private TextView details_text_detailsT;
    private TextView details_text_commentsT;
    private TextView details_text_goods;
    private TextView details_text_details;
    private TextView details_text_comments;
    private RelativeLayout details_relative_changer;
    private RelativeLayout details_relat_changecolor;
    private RelativeLayout details_relative_addshoppingcar;
    private RelativeLayout details_relative_pay;
    private int count;
    private String id;
    private DetailsCommentsPresenter mDetailsCommentsPresenter;
    int page = 1;
    private AddCartPresenter mAddCartPresenter;


    @Override
    protected int contentView() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.e("lk","id"+id);
        details_image_return = findViewById(R.id.details_image_return);
        details_viewpager_show = findViewById(R.id.details_viewpager_show);
        details_textview_shownum = findViewById(R.id.details_textview_shownum);
        details_textview_price = findViewById(R.id.details_textview_sprice);
        details_textview_sold = findViewById(R.id.details_textview_sold);
        details_textview_title = findViewById(R.id.details_textview_title);
        details_textview_Weight = findViewById(R.id.details_textview_Weight);
        details_Image_details = findViewById(R.id.details_Image_details);
        details_textview_describe = findViewById(R.id.details_textview_describe);
        details_Image_describe = findViewById(R.id.details_Image_describe);
        details_recview_comments = findViewById(R.id.details_recview_comments);
        details_textview_comments = findViewById(R.id.details_textview_comments);
        details_scroll_changecolor = findViewById(R.id.details_scroll_changecolor);
        details_relat_changecolor = findViewById(R.id.details_relat_changecolor);
        details_relative_addshoppingcar = findViewById(R.id.details_relative_addshoppingcar);
        details_relative_pay = findViewById(R.id.details_relative_pay);
        details_text_goodsT = findViewById(R.id.details_text_goodsT);
        details_text_goods = findViewById(R.id.details_text_goods);
        details_text_detailsT = findViewById(R.id.details_text_detailsT);
        details_text_details = findViewById(R.id.details_text_details);
        details_text_commentsT = findViewById(R.id.details_text_commentsT);
        details_text_comments = findViewById(R.id.details_text_comments);
        details_relative_changer = findViewById(R.id.details_relative_changer);


        //点击返回按钮销毁当前页面
        details_image_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        //加入购物车
        details_relative_addshoppingcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText num = new EditText(DetailsActivity.this);
                new AlertDialog.Builder(DetailsActivity.this).setTitle("请输入数量")
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(num)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            private String mNum;

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                                mNum = num.getText().toString().trim();
                                requestData(id,mNum);


                            }
                        }).setNegativeButton("取消",null).show();



            }

            private void requestData(String id, String num) {
                Log.e("Goodsid",""+id);
                UserDaoDao userDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
                List<UserDao> userDaos = userDaoDao.loadAll();

                mAddCartPresenter = new AddCartPresenter(new AddCartCall());
                mAddCartPresenter.reqeust(userDaos.get(0).getUserId(),userDaos.get(0).getSessionId(),"[{\"commodityId\":"+id+",\"count\":"+num+"}]");
            }
        });
        //直接支付
        details_relative_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });








        //商品banner的下标展示
        details_viewpager_show.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                details_textview_shownum.setText((position + 1) + "/" + count);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //滑动变色
        details_scroll_changecolor.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChange(MyScrollView scrollView, int l, int t, int oldl, int oldt) {
                if (t <= 0) {
                    //标题显示
                    details_relative_changer.setVisibility(View.GONE);
                    //设置标题所在的背景颜色为透明
                    details_relat_changecolor.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else if (t > 0 && t < 200) {
                    details_relative_changer.setVisibility(View.VISIBLE);
                    //获取ScrollView想下滑动,图片消失部分的比例
                    float scale = (float) t / 200;
                    //根据比例,让标题的颜色由浅入深
                    float alpha = 255 * scale;
                    //设置标题布局的颜色
                    details_relat_changecolor.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));

                }
                if (0 < t && t < 700) {
                    details_text_goods.setBackgroundColor(Color.RED);
                    details_text_details.setBackgroundColor(Color.WHITE);
                    details_text_comments.setBackgroundColor(Color.WHITE);
                } else if (701 < t && t < 1500) {
                    details_text_goods.setBackgroundColor(Color.WHITE);
                    details_text_details.setBackgroundColor(Color.RED);
                    details_text_comments.setBackgroundColor(Color.WHITE);
                } else if (t > 1500) {
                    details_text_goods.setBackgroundColor(Color.WHITE);
                    details_text_details.setBackgroundColor(Color.WHITE);
                    details_text_comments.setBackgroundColor(Color.RED);
                }
            }
        });

        details_text_goodsT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_scroll_changecolor.setScrollY(0);
            }
        });
        details_text_detailsT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_scroll_changecolor.setScrollY(702);
            }
        });
        details_text_commentsT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_scroll_changecolor.setScrollY(1501);
            }
        });

    }

    @Override
    protected void initData() {
        mUserDaoDao = MyApplication.getInstances().getDaoSession().getUserDaoDao();
        mUserDaos = mUserDaoDao.loadAll();
        mDetailsPresent = new DetailsPresent(new DetailsCall());
        mDetailsPresent.reqeust(mUserDaos.get(0).getUserId(), mUserDaos.get(0).getSessionId(),id);

        mDetailsCommentsPresenter = new DetailsCommentsPresenter(new DetailsCommentsCall());
        mDetailsCommentsPresenter.reqeust(id,50,page);
    }

    private class DetailsCall implements DataCall<Result<DetailsBean>> {
        @Override
        public void success(Result<DetailsBean> data) {
            DetailsBean detailsBeans = data.getResult();
            LoadData(detailsBeans);
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private void LoadData(DetailsBean detailsBeans) {
        details_textview_price.setText("￥" + detailsBeans.getPrice());
        details_textview_sold.setText("已售" + detailsBeans.getSaleNum() + "件");
        details_textview_title.setText(detailsBeans.getCommodityName());
        details_textview_Weight.setText(detailsBeans.getWeight() + "kg");
        details_textview_describe.setText(detailsBeans.getDescribe());

        String Pictures = detailsBeans.getPicture().trim();
        String[] split = Pictures.split(",");

        details_Image_details.setImageURI(split[0]);
        details_Image_describe.setImageURI(split[1]);

        ViewPager_Adapter_DetailsBanner adapter = new ViewPager_Adapter_DetailsBanner(this, split);
        count = adapter.getCount();
        details_viewpager_show.setAdapter(adapter);
    }

    private class DetailsCommentsCall implements DataCall<Result<List<DetailsCommentsBeans>>> {
        @Override
        public void success(Result<List<DetailsCommentsBeans>> data) {
            if (data.getResult().size() != 0) {
                details_recview_comments.setVisibility(View.VISIBLE);
                details_textview_comments.setVisibility(View.GONE);

                details_recview_comments.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
                RecycleView_Adapter_DetailsComments commentsAdapter = new RecycleView_Adapter_DetailsComments(DetailsActivity.this,data.getResult());
                details_recview_comments.setAdapter(commentsAdapter);
            } else {
                details_recview_comments.setVisibility(View.GONE);
                details_textview_comments.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class AddCartCall implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")){
                Toast.makeText(DetailsActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
