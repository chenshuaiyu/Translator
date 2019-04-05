package com.example.chen.translator.data.db;

import com.example.chen.translator.app.App;
import com.example.chen.translator.data.dao.DaoSession;
import com.example.chen.translator.data.dao.Translation;
import com.example.chen.translator.data.dao.TranslationDao;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/5 10:21
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
                if (sDatabase == null)
                    sDatabase = new Database();
            }
        }
        return sDatabase;
    }

    public Translation getTranslation(String query) {
        List<Translation> list = mDaoSession.queryBuilder(Translation.class).where(TranslationDao.Properties.Input.eq(query)).list();
        if (list.size() == 0)
            return null;
        else
            return list.get(0);
    }

    public void addTranslation(Translation translation) {
        mDaoSession.insert(translation);
    }

    public List<Translation> getAllTranslation() {
        return mDaoSession.loadAll(Translation.class);
    }
}
