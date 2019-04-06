package com.example.chen.translator.utils;

import com.example.chen.translator.data.Repository;
import com.example.chen.translator.data.db.Database;
import com.example.chen.translator.data.network.Network;
import com.example.chen.translator.ui.ModelFactory;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/5 17:29
 */
public class Inject {
    private static ModelFactory sModelFactory;

    public static ModelFactory getModelFactory(){
        if (sModelFactory == null)
            sModelFactory = new ModelFactory(Repository.getInstance(Network.getInstance(), Database.getInstance()));
        return sModelFactory;
    }
}
