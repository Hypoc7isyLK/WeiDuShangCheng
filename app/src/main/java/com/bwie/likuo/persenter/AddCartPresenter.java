package com.bwie.likuo.persenter;

import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.http.IRetrofitRx;
import com.bwie.likuo.core.http.NetworkManager;

import io.reactivex.Observable;

/**
 * date:2019/1/15
 * author:李阔(淡意衬优柔)
 * function:
 */
public class AddCartPresenter extends BasePresenter {
    public AddCartPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRetrofitRx iRetrofitRx = NetworkManager.instance().create(IRetrofitRx.class);
        return iRetrofitRx.ShowInsertCart((int) args[0],(String) args[1],(String)args[2]);
    }
}
