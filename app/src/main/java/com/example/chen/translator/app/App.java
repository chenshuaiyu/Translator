package com.example.chen.translator.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.chen.translator.data.dao.DaoMaster;
import com.example.chen.translator.data.dao.DaoSession;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/5 10:26
 */
public class App extends Application {
    private static App sApp;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(sApp, Constants.DB_NAME);
        SQLiteDatabase database = helper.getWritableDatabase();
        mDaoSession = new DaoMaster(database).newSession();
    }

    public static App getInstance() {
        return sApp;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
