package com.stay.net.com.stay.http;

import android.os.AsyncTask;

import com.stay.net.Request;

import java.net.HttpURLConnection;

/**
 * Created by apple on 15/10/6.
 * 请求访问网络接口可以很多请求所以放到子线程中访问网络
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {
    private Request request;

    public RequestTask(Request request) {
        this.request = request;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... params) {
        try {
            HttpURLConnection connection = HttpURLConnectionUtil.execute(request);// 数据拿到了return
            return  request.iCallback.parse(connection); // return回去的是
        } catch (Exception e) {
            return e;
        }
    }
// 这里执行的都是在主线程onPostExecute
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof Exception) {
            request.iCallback.onFailure((Exception) o);
        } else {
            request.iCallback.onSuccess(o);
        }
    }
}
