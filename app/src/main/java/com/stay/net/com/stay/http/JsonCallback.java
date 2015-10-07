package com.stay.net.com.stay.http;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by apple on 15/10/7.
 * 具体的解析拿数据
 * <T>也是User
 */
public abstract class JsonCallback<T> extends AbstractCallback<T> {

    @Override
    protected T bindData(String result) throws Exception{
        JSONObject json=new JSONObject(result);
        JSONObject data=json.optJSONObject("data");
        Gson gson=new Gson();
        Type type=((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return gson.fromJson(data.toString(),type);// clz是User.class  type反序列化
    }
}
