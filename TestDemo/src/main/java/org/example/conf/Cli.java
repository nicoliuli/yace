package org.example.conf;

import okhttp3.*;

public class Cli {

    public Cli() {
    }

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String url = "http://localhost:18000/api/v1/image/check?debug=abc";
    private String data = "{\"type\":\"1\",\"appId\":\"91000001\",\"cache\":\"1\",\"image\":\"https://scpic.chinaz.net/files/pic/pic9/202204/apic40322.jpg\",\"debug\":\"abc\"}";
    RequestBody requestBody = RequestBody.create(JSON, data);
    Request request = new Request.Builder().url(url).post(requestBody).addHeader("X-AppId","91000001").build();


    private OkHttpClient okHttpClient = new Config().okHttpClient();


    public String doPostJson() {
        return exectePost();
    }

    private String exectePost() {
        return execute(request);
    }

    private String execute(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }
}
