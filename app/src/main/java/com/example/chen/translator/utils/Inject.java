package com.example.chen.translator.utils;

import com.example.chen.translator.data.Repository;
import com.example.chen.translator.data.db.Database;
import com.example.chen.translator.data.network.Network;
import com.example.chen.translator.ui.main.MainModelFactory;
import com.example.chen.translator.ui.fragment.home.TranslateModelFactory;
import com.example.chen.translator.ui.splash.SplashModelFactory;

import androidx.lifecycle.ViewModelProvider;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/5 17:29
 */
public class Inject {
    private static TranslateModelFactory sTranslateModelFactory;
    private static MainModelFactory sMainModelFactory;
    private static SplashModelFactory sSplashModelFactory;

    public static TranslateModelFactory getTranslateModelFactory(){
        if (sTranslateModelFactory == null)
            sTranslateModelFactory = new TranslateModelFactory(Repository.getInstance(Network.getInstance(), Database.getInstance()));
        return sTranslateModelFactory;
    }

    public static MainModelFactory getMainModelFactory(){
        if (sMainModelFactory == null)
            sMainModelFactory = new MainModelFactory(Repository.getInstance(Network.getInstance(), Database.getInstance()));
        return sMainModelFactory;
    }

    public static ViewModelProvider.Factory getSplashModelFactory() {
        if (sSplashModelFactory == null)
            sSplashModelFactory = new SplashModelFactory(Repository.getInstance(Network.getInstance(), Database.getInstance()));
        return sSplashModelFactory;
    }
}
