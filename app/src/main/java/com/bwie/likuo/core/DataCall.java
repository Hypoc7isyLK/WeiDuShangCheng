package com.bwie.likuo.core;


import com.bwie.likuo.core.exception.ApiException;


/**
 * @author dingtao
 * @date 2018/12/30 10:30
 * qq:1940870847
 */
public interface DataCall<T> {

    void success(T data);
    void fail(ApiException e);

}
