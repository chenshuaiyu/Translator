package com.example.chen.translator.data;

import com.example.chen.translator.data.dao.Translation;
import com.example.chen.translator.data.db.Database;
import com.example.chen.translator.data.model.Bing;
import com.example.chen.translator.data.model.DailyEnglish;
import com.example.chen.translator.data.model.Result;
import com.example.chen.translator.data.network.Network;
import java.util.List;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/5 10:20
 */
public class Repository {
    private static Repository sRepository;
    private Network mNetwork;
    private Database mDatabase;

    private Repository(Network network, Database database) {
        mNetwork = network;
        mDatabase = database;
    }

    public static Repository getInstance(Network network, Database database) {
        if (sRepository == null) {
            synchronized (Repository.class) {
                if (sRepository == null) {
                    sRepository = new Repository(network, database);
                }
            }
        }
        return sRepository;
    }

    public MutableLiveData<Translation> getTranslationFromNet(String query) {
        MutableLiveData<Translation> liveData = new MutableLiveData<>();
        Translation translation = mDatabase.getTranslation(query);
        if (translation == null) {
            mNetwork.getTranslation(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Result result) {
                            Translation t = new Translation();
                            t.setInput(result.getQuery());
                            t.setOutput(result.getTranslation().get(0));
                            t.setIsCollected(false);
                            liveData.setValue(t);
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        } else {
            liveData.postValue(translation);
        }
        return liveData;
    }

    public void addTranslation(Translation translation) {
        mDatabase.addTranslation(translation);
    }

    public List<Translation> getAllTranslation() {
        return mDatabase.getAllTranslation();
    }

    public MutableLiveData<Bing> getBingPicture() {
        MutableLiveData<Bing> liveData = new MutableLiveData<>();
        mNetwork.getBingPicture()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bing>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Bing bing) {
                        liveData.setValue(bing);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return liveData;
    }

    public MutableLiveData<DailyEnglish> getDailyEnglish() {
        MutableLiveData<DailyEnglish> liveData = new MutableLiveData<>();
        mNetwork.getDailyEnglish()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyEnglish>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DailyEnglish dailyEnglish) {
                        liveData.setValue(dailyEnglish);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return liveData;
    }

    public List<Translation> getCollectedTranslation() {
        return mDatabase.getCollectedTranslation();
    }

    public void deleteTranslation(Translation translation) {
        mDatabase.deleteTranslation(translation);
    }

    public void setCollected(Translation translation) {
        mDatabase.setCollected(translation);
    }
}
