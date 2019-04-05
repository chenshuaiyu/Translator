package com.example.chen.translator.ui.splash;

import com.example.chen.translator.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/5 23:18
 */
public class SplashModelFactory implements ViewModelProvider.Factory {
    private Repository mRepository;

    public SplashModelFactory(Repository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SplashViewModel(mRepository);
    }
}
