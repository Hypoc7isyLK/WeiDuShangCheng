package com.bwie.likuo.persenter;

import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.http.IRetrofitRx;
import com.bwie.likuo.core.http.NetworkManager;

import io.reactivex.Observable;

/**
 * date:2019/1/4
 * author:李阔(淡意衬优柔)
 * function:
 */
public class HomeFragmentSearchPresenter extends BasePresenter {
    public HomeFragmentSearchPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRetrofitRx iRetrofitRx = NetworkManager.instance().create(IRetrofitRx.class);
        return iRetrofitRx.ShowSearch((String) args[0],(int) args[1],(int) args[2]);
    }
}
