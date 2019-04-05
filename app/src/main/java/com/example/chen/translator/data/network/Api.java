package com.example.chen.translator.data.network;


import com.example.chen.translator.data.model.Bing;
import com.example.chen.translator.data.model.DailyEnglish;
import com.example.chen.translator.data.model.Result;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/2 22:57
 */
public interface Api {
    @POST("api")
    @FormUrlEncoded
    Observable<Result> getTranslation(@Field("q") String q, @Field("from") String from, @Field("to") String to,
                                      @Field("signType") String signType, @Field("curtime") String curtime,
                                      @Field("appKey") String appKey, @Field("salt") String salt, @Field("sign") String sign);

    @GET("dsapi")
    Observable<DailyEnglish> getDailtEnglish();

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    Observable<Bing> getBingPicture();

}
