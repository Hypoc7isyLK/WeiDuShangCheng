package com.bwie.likuo.persenter;

import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.http.IRetrofitRx;
import com.bwie.likuo.core.http.NetworkManager;

import io.reactivex.Observable;

/**
 * date:2019/1/3
 * author:李阔(淡意衬优柔)
 * function:
 */
public class HomeFragmentPresenter extends BasePresenter {
    public HomeFragmentPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRetrofitRx iRetrofitRx = NetworkManager.instance().create(IRetrofitRx.class);
        return iRetrofitRx.showhome();
    }
}
