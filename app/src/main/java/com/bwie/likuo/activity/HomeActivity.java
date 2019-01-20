package com.bwie.likuo.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.likuo.R;
import com.bwie.likuo.fragment.CircleFragment;
import com.bwie.likuo.fragment.HomeFragment;
import com.bwie.likuo.fragment.IndentFragment;
import com.bwie.likuo.fragment.MineFragment;
import com.bwie.likuo.fragment.ShoppingFragment;

import butterknife.ButterKnife;

/**
 * date:2018/12/4
 * author:李阔(淡意衬优柔)
 * function:
 */
public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    HomeFragment mHomeFragment;
    CircleFragment mCircleFragment;
    ShoppingFragment mShoppingFragment;
    IndentFragment mIndentFragment;
    MineFragment mMineFragment;
    private FrameLayout container;
    private RadioGroup bottomMenu;

    @Override
    public void initData() {

    }

    @Override
    protected int contentView() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        container = findViewById(R.id.container);
        bottomMenu = findViewById(R.id.bottom_menu);
        bottomMenu.setOnCheckedChangeListener(this);

        mHomeFragment = new HomeFragment();
        mCircleFragment = new CircleFragment();
        mShoppingFragment = new ShoppingFragment();
        mIndentFragment = new IndentFragment();
        mMineFragment = new MineFragment();

        currentFragment = mHomeFragment;
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.add(R.id.container, mHomeFragment)
                .show(mHomeFragment).commit();



    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.home_btn) {
            showFragment(mHomeFragment);
        } else if (checkedId == R.id.circle_btn) {
            showFragment(mCircleFragment);
        } else if (checkedId == R.id.cart_btn){
            showFragment(mShoppingFragment);
        } else if (checkedId == R.id.list_btn){
            showFragment(mIndentFragment);
        } else if (checkedId == R.id.me_btn) {
            showFragment(mMineFragment);
        }


    }
    Fragment currentFragment;

    /**
     * 展示Fragment
     */
    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.container, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }
}
