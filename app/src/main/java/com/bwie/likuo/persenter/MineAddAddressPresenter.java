package com.bwie.likuo.persenter;

import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.http.IRetrofitRx;
import com.bwie.likuo.core.http.NetworkManager;

import io.reactivex.Observable;

/**
 * date:2019/1/8
 * author:李阔(淡意衬优柔)
 * function:
 */
public class MineAddAddressPresenter extends BasePresenter {
    public MineAddAddressPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRetrofitRx iRetrofitRx = NetworkManager.instance().create(IRetrofitRx.class);
        return iRetrofitRx.AddAddress((int) args[0],(String) args[1],(String) args[2],(String) args[3],(String) args[4],(String) args[5]);
    }
}
