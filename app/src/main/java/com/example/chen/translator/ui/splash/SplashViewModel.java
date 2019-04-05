package com.example.chen.translator.ui.splash;

import com.example.chen.translator.data.Repository;
import com.example.chen.translator.data.model.DailyEnglish;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/5 23:19
 */
public class SplashViewModel extends ViewModel {
    private Repository mRepository;

    public SplashViewModel(Repository repository) {
        mRepository = repository;
    }

    public MutableLiveData<DailyEnglish> getDailyEnglish() {
        return mRepository.getDailyEnglish();
    }
}
