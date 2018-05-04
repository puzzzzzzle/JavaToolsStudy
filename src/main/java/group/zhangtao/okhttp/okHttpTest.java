package group.zhangtao.okhttp;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

public class okHttpTest {
    @Test
    public void test(){
        try {
            OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
            Request request = new Request.Builder()
                    .url("http://www.baidu.com")//请求接口。如果需要传参拼接到接口后面。
                    .build();//创建Request 对象
            Response response = null;
            response = client.newCall(request).execute();//得到Response 对象
            if (response.isSuccessful()) {
                System.out.println(response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testAsync(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){//回调的方法执行在子线程。
                    System.out.println(response.body().string());
                }
            }
        });
        System.out.println("running");
    }

    public static void main(String[] args) {
        new okHttpTest().testAsync();
    }
}
