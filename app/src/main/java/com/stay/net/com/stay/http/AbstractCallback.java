package com.stay.net.com.stay.http;

import org.apache.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * 具体的解析拿数据
 * 不管是JsonCallback 还是 XMLCallback都有相同的部分给抽取出来
 */
public abstract class AbstractCallback<T> implements ICallback<T> {
    private String path;

    @Override
    public T parse(HttpURLConnection connection) throws Exception {
        int status = connection.getResponseCode();
        if (status == HttpStatus.SC_OK) {
            if(path == null){
                ByteArrayOutputStream out = new ByteArrayOutputStream();// 将服务器返回的 ByteArrayOutputStream转换成String
                InputStream is = connection.getInputStream();
                byte[] buffer = new byte[2048];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                is.close();
                out.flush();
                out.close();
                String resule = new String(out.toByteArray());// 转换成String，在返回给我们的上层，如果这个String是json个事或者是xml格式，我们在对它进行转换
                return bindData(resule); // 我们让子类来完成这个事情.因为上面这些代码JsonCallback和XMLCallback都是相同的，唯一不同的就是这块
            }else{
                // 通过这样我们就能写入sd卡了
                FileOutputStream out = new FileOutputStream(path);// 将服务器返回的 ByteArrayOutputStream转换成String
                InputStream is = connection.getInputStream();

                int totalLen=connection.getContentLength();// 总的进度 ， 文件下载
                int curLen=0;// 当前下载到多少了
                byte[] buffer = new byte[2048];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                    curLen+=totalLen;
                }
                is.close();
                out.flush();
                out.close();
                return bindData(path); // 我们让子类来完成这个事情.因为上面这些代码JsonCallback和XMLCallback都是相同的，唯一不同的就是这块
            }

        }
        return null;
    }
    // 定义这个方法让子类来实现，子类需要集成本类方法并重写该方法
    protected abstract T bindData(String resule) throws Exception;

    public ICallback setCachePath(String path) {
        this.path = path;
        return this;
    }
}
