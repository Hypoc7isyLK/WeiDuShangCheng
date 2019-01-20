package com.bwie.likuo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * date:2018/12/8
 * author:李阔(淡意衬优柔)
 * function:
 */
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollViewListener !=null){
            mScrollViewListener.onScrollChange(this,l,t,oldl,oldt);
        }
    }


    public interface ScrollViewListener{
        void onScrollChange(MyScrollView scrollView, int l, int t, int oldl, int oldt);
    }

    private ScrollViewListener  mScrollViewListener;

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        mScrollViewListener = scrollViewListener;
    }
}
