package com.bwie.likuo.persenter;

import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.http.IRetrofitRx;
import com.bwie.likuo.core.http.NetworkManager;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 */
public class TouPresenter extends BasePresenter {

    public TouPresenter(DataCall dataCall) {
        super(dataCall);
    }


    @Override
    protected Observable observable(Object... args) {
        IRetrofitRx iRetrofitRx = NetworkManager.instance().create(IRetrofitRx.class);
        File file = new File((String)args[2]);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("image", file.getName(),
                RequestBody.create(MediaType.parse("multipart/octet-stream"),file));
        return iRetrofitRx.showUpdateHead((int) args[0],
                (String)args[1],builder.build());
    }


}
