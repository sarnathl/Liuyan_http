package com.stay.net.com.stay.http;

import java.net.HttpURLConnection;

/**
 * Created by apple on 15/10/6.
 * <T> 服务器返回String 就是String,返回Json就是json
 */
public interface ICallback<T>{
    void onSuccess(T result);
    void onFailure(Exception e);

    T parse(HttpURLConnection connection) throws Exception;
}
