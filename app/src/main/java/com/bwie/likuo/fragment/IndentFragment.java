package com.bwie.likuo.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bwie.likuo.R;

/**
 * A simple {@link Fragment} subclass.
 * @author 淡意衬优柔
 */
public class IndentFragment extends BaseFragment {

    private TabLayout indentfragment_tab;
    private ViewPager indentfragment_viewpager;
    private TabLayout.Tab tab_one;
    private TabLayout.Tab tab_two;
    private TabLayout.Tab tab_three;
    private TabLayout.Tab tab_four;
    private TabLayout.Tab tab_five;

    @Override
    public void initView(View view) {
        indentfragment_tab = view.findViewById(R.id.indentfragment_tab);
        indentfragment_viewpager = view.findViewById(R.id.indentfragment_viewpager);

    }

    @Override
    public void initData(View view) {
        initViews1();
        initEvents1();

    }



    private void initViews1() {
        indentfragment_viewpager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            private String[] mTitles = new String[]{"全部订单", "待付款", "待收货", "待评价","已完成"};

            @Override
            public Fragment getItem(int position) {//为fragment pager adapter 分配 fragment
                if (position == 0) {
                    return new CompleteIndentFragment();
                }else if (position == 1) {
                    return new PaymentFragment();
                }else if (position == 2) {
                    return new ReceivingFragment();
                }else if(position == 3){
                    return new EvaluateFragment();
                }else{
                    return new FinishFragment();
                }
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {//显示title
                return mTitles[position];
            }
        });
        indentfragment_tab.setupWithViewPager(indentfragment_viewpager);//将tablayout和viewpager绑定
        tab_one = indentfragment_tab.getTabAt(0);
        tab_two = indentfragment_tab.getTabAt(1);
        tab_three = indentfragment_tab.getTabAt(2);
        tab_four = indentfragment_tab.getTabAt(3);
        tab_five = indentfragment_tab.getTabAt(4);
        tab_one.setIcon(getResources().getDrawable(R.mipmap.common_icon_alllist_n_hdpi,null));
        tab_two.setIcon(getResources().getDrawable(R.mipmap.common_icon_pay_n_hdpi,null));
        tab_three.setIcon(getResources().getDrawable(R.mipmap.common_icon_receive_n_hdpi,null));
        tab_four.setIcon(getResources().getDrawable(R.mipmap.commom_icon_comment_n_hdpi,null));
        tab_five.setIcon(getResources().getDrawable(R.mipmap.common_icon_finish_n_hdpi,null));
    }

    private void initEvents1() {
        indentfragment_tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {//viewpager跟着tab
                if(tab==indentfragment_tab.getTabAt(0)){
                    indentfragment_viewpager.setCurrentItem(0);
                }
                if(tab==indentfragment_tab.getTabAt(1)){
                    indentfragment_viewpager.setCurrentItem(1);
                }
                if(tab==indentfragment_tab.getTabAt(2)){
                    indentfragment_viewpager.setCurrentItem(2);
                }
                if(tab==indentfragment_tab.getTabAt(3)){
                    indentfragment_viewpager.setCurrentItem(3);
                }
                if(tab==indentfragment_tab.getTabAt(4)){
                    indentfragment_viewpager.setCurrentItem(4);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public int getContent() {
        return R.layout.fragment_indent;
    }
}
