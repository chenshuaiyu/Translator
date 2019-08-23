package com.example.chen.translator.data.network;

import com.example.chen.translator.app.Constants;
import com.example.chen.translator.data.model.Bing;
import com.example.chen.translator.data.model.DailyEnglish;
import com.example.chen.translator.data.model.Result;
import com.example.chen.translator.utils.MD5Helper;

import io.reactivex.Observable;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/5 10:21
 */
public class Network {
    private static Network sNetwork;
    private Api mApi;

    private Network() {
        mApi = ApiService.createService();
    }

    public static Network getInstance() {
        if (sNetwork == null) {
            synchronized (Network.class) {
                if (sNetwork == null) {
                    sNetwork = new Network();
                }
            }
        }
        return sNetwork;
    }

    public Observable<Result> getTranslation(String query) {
        String salt = String.valueOf(System.currentTimeMillis());
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        return mApi.getTranslation(query, "EN", "zh-CHS", "v3", curtime,
                Constants.APP_ID, salt,
                MD5Helper.md5(Constants.APP_ID + MD5Helper.truncate(query) + salt + curtime + Constants.APP_KEY));
    }

    public Observable<Bing> getBingPicture() {
        return mApi.getBingPicture(Constants.DAILY_PICTURE);
    }

    public Observable<DailyEnglish> getDailyEnglish() {
        return mApi.getDailyEnglish(Constants.DAILY_ENGLISH);
    }
}
