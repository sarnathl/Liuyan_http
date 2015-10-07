package com.stay.net;

import com.stay.net.com.stay.http.ICallback;

import java.util.Map;

/**
 * Created by apple on 15/9/25.
 */
public class Request {
    public ICallback iCallback;

    public void setCallBack(ICallback iCallback) {
        this.iCallback=iCallback;
    }
    // 在这里定义请求我的方法到底用哪一个

    public enum RequestMethod {GET, POST, PUT, DELETE}

    public String url;
    public String content;// post拼接用
    public Map<String, String> headers;// 请求头

    public RequestMethod method;

    public Request(String url,RequestMethod method){
        this.url=url;
        this.method=method;
    }
    // 默认get方式
    public Request(String url){
        this.url=url;
        this.method=RequestMethod.GET;
    }
}
