package com.stay.net.com.stay.http;

/**
 * Created by apple on 15/10/7.
 * 具体的解析拿数据
 * <T>也是User
 */
public abstract class FileCallback extends AbstractCallback<String> {

    @Override
    protected String bindData(String path) throws Exception{
        return path;
    }
}
