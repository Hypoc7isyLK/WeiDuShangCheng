package com.bwie.likuo.fragment;

;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * date:2018/12/4
 * author:李阔(淡意衬优柔)
 * function:
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getContent(), container, false);
        initView(view);
        initData(view);
        return view;
    }

    public abstract void initView(View view);
    public abstract void initData(View view);
    public abstract int getContent();

}
