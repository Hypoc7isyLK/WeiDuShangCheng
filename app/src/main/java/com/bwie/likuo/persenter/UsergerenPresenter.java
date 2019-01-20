package com.bwie.likuo.persenter;

import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.http.IRetrofitRx;
import com.bwie.likuo.core.http.NetworkManager;

import io.reactivex.Observable;

/**
 */
public class UsergerenPresenter extends BasePresenter {

    public UsergerenPresenter(DataCall dataCall) {
        super(dataCall);
    }


    @Override
    protected Observable observable(Object... args) {
        IRetrofitRx iRetrofitRx = NetworkManager.instance().create(IRetrofitRx.class);
        return iRetrofitRx.getUser((int) args[0],(String)args[1]);
    }


}
