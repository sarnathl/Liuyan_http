package com.stay.net.com.stay.http;

import com.stay.net.Request;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by apple on 15/10/4.
 * 这个类负责帮我们请求服务器
 */
public class HttpURLConnectionUtil {
    private static final int TIMEOUT=15*3000;
    /**
     * 思考我们这里有4个方法，那么我们可不可以提供一个方法提供给外部
     *
     */
    public static HttpURLConnection execute(Request request) throws Exception {
        switch (request.method){
            case GET:
            case DELETE:
                return get(request);
            case POST:
            case PUT:
                return post(request);
        }
        return null;
    }
    private static HttpURLConnection get(Request request) throws Exception {   // 这里把方法私有化，上面暴露出一个供外部调用的方法，这里就可以用私有
        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.method.name());//GET
        connection.setConnectTimeout(TIMEOUT);
        connection.setReadTimeout(TIMEOUT);

        addHeader(connection, request.headers);

        return connection;
    }

    //String url, String content,Map<String,String> headers 把这几个参数封装成javabean
    public static HttpURLConnection post(Request request) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.method.name());
        connection.setConnectTimeout(TIMEOUT);
        connection.setReadTimeout(TIMEOUT);
        connection.setDoOutput(true);

        addHeader(connection, request.headers);
      //  connection.addRequestProperty("content-type", "application/json");

        OutputStream os = connection.getOutputStream();
        os.write(request.content.getBytes());

        return  connection;

    }

    private static void addHeader(HttpURLConnection connection, Map<String, String> headers) {
        if (headers == null || headers.size() == 0) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }

}
