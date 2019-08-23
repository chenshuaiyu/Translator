package com.example.chen.translator.data.network;


import com.example.chen.translator.data.model.Bing;
import com.example.chen.translator.data.model.DailyEnglish;
import com.example.chen.translator.data.model.Result;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/2 22:57
 */
public interface Api {
    /**
     * 获取翻译结果
     *
     * @param q
     * @param from
     * @param to
     * @param signType
     * @param curtime
     * @param appKey
     * @param salt
     * @param sign
     * @return
     */
    @POST("api")
    @FormUrlEncoded
    Observable<Result> getTranslation(@Field("q") String q, @Field("from") String from, @Field("to") String to,
                                      @Field("signType") String signType, @Field("curtime") String curtime,
                                      @Field("appKey") String appKey, @Field("salt") String salt, @Field("sign") String sign);

    /**
     * 获取每日一句
     *
     * @param url
     * @return
     */
    @GET()
    Observable<DailyEnglish> getDailyEnglish(@Url String url);

    /**
     * 获取每日一图
     *
     * @param url
     * @return
     */
    @GET()
    Observable<Bing> getBingPicture(@Url String url);

}
