package com.example.chen.translator.data.network;

import com.example.chen.translator.app.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/4 8:50
 */
public class ApiService {
    private static Api sApi;

    public static Api createService(){
        Retrofit retrofit = null;
        if (sApi == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.YOUDAO_BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit.create(Api.class);
    }
}
