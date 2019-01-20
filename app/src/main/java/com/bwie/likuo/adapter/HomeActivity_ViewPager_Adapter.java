package com.bwie.likuo.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/4
 * author:李阔(淡意衬优柔)
 * function:
 */
public class HomeActivity_ViewPager_Adapter extends FragmentPagerAdapter {
    /**
     * @param fm
     * @deprecated
     */
    List<Fragment> list;
    public HomeActivity_ViewPager_Adapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
    }

    public void setData(List<Fragment> list){
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }
    /**
     * @param i
     * @deprecated
     */
    @Override
    public Fragment getItem(int i) {
        Fragment fragment = list.get(i);

        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
