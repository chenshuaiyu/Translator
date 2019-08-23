package com.example.chen.translator.data.db;

import android.util.Log;

import com.example.chen.translator.app.App;
import com.example.chen.translator.data.dao.DaoSession;
import com.example.chen.translator.data.dao.Translation;
import com.example.chen.translator.data.dao.TranslationDao;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/5 10:21
 */
public class Database {
    private static Database sDatabase;
    private DaoSession mDaoSession;

    private Database() {
        mDaoSession = App.getInstance().getDaoSession();
    }

    public static Database getInstance() {
        if (sDatabase == null) {
            synchronized (Database.class) {
                if (sDatabase == null) {
                    sDatabase = new Database();
                }
            }
        }
        return sDatabase;
    }

    public Translation getTranslation(String query) {
        List<Translation> list = mDaoSession.queryBuilder(Translation.class).where(TranslationDao.Properties.Input.eq(query)).list();
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void addTranslation(Translation translation) {
        mDaoSession.insert(translation);
    }

    public List<Translation> getAllTranslation() {
        return mDaoSession.loadAll(Translation.class);
    }

    public List<Translation> getCollectedTranslation() {
        List<Translation> list = mDaoSession.queryBuilder(Translation.class).where(TranslationDao.Properties.IsCollected.eq(true)).list();
        return list;
    }

    public void deleteTranslation(Translation translation) {
        mDaoSession.delete(translation);
    }

    public void setCollected(Translation translation) {
        Translation t = getTranslation(translation.getInput());
        t.setIsCollected(translation.getIsCollected());
        mDaoSession.update(t);
    }
}
