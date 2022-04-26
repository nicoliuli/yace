package org.example.conf;

import okhttp3.*;

public class Cli {

    public Cli() {
    }

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private OkHttpClient okHttpClient = new Config().okHttpClient();


    public String doPostJson(String url, String json) {
        return exectePost(url, json, JSON);
    }

    private String exectePost(String url, String data, MediaType contentType) {
        RequestBody requestBody = RequestBody.create(contentType, data);
        Request request = new Request.Builder().url(url).post(requestBody).addHeader("X-AppId","91000001").build();

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
