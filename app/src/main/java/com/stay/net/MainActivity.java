package com.stay.net;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.stay.net.com.stay.http.FileCallback;
import com.stay.net.com.stay.http.JsonCallback;
import com.stay.net.com.stay.http.RequestTask;
import com.stay.net.com.stay.http.StringCallback;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  testHttpPostOnSubThread();
                //  testHttpGet();
                 testHttpPostOnSubThreadForGeneric();
               // testHttpPostOnSubThreadFordownload();
            }
        });
    }
    public void testHttpGet(){
        String url = "http://stage-api.yxhaoche.com/api/app/AppVersion/AndroidLatestVersion?appKey=ea543510-c4c7-4b3f-b698-b9494bd7440c";
      //  Request request = new Request(url);    // 给这个Request定义到底是get方法还是post方法，默认是get方法
        Request request = new Request(url);
        request.setCallBack(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag", "testHttpGet return:" + result.toString());
            }
            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
        RequestTask task=new RequestTask(request);
        task.execute();
    }

    public void testHttpPostOnSubThread() {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, Request.RequestMethod.POST);// 把url扔给request，request封装了所有http请求参数
        request.setCallBack(new JsonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("result:"+result);
            }
            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
        request.content = content;
        RequestTask task=new RequestTask(request);// 移动到子线程取执行访问网络
        task.execute();
    }
    public void testHttpPostOnSubThreadForGeneric() {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, Request.RequestMethod.POST);// 把url扔给request，request封装了所有http请求参数
        request.setCallBack(new JsonCallback<User>() {  // 如果返回的是XMLCallback 就改成XMLCallback
            @Override
            public void onSuccess(User result) {
                System.out.println("result:"+result);
            }
            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
        request.content = content;
        RequestTask task=new RequestTask(request);// 然后扔给RequestTask去执行，移动到子线程取执行访问网络
        task.execute();
    }
    // 服务器返回，保存到SD卡了
    public void testHttpPostOnSubThreadFordownload() {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, Request.RequestMethod.POST);// 把url扔给request，request封装了所有http请求参数
        String path= Environment.getExternalStorageDirectory()+ File.separator+"demo5";
        request.setCallBack(new FileCallback() {
            @Override
            public void onSuccess(String path) {
                Log.i("tag",path);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }.setCachePath(path));// 设置路径它要保存到那里去,把服务器返回的下载到SD卡
        request.content = content;
        RequestTask task=new RequestTask(request);// 然后扔给RequestTask去执行，移动到子线程取执行访问网络
        task.execute();
    }


}
