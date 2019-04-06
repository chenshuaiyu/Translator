package com.example.chen.translator.ui;

import com.example.chen.translator.data.Repository;
import com.example.chen.translator.ui.fragment.collect.CollectViewModel;
import com.example.chen.translator.ui.fragment.home.TranslateViewModel;
import com.example.chen.translator.ui.main.MainViewModel;
import com.example.chen.translator.ui.splash.SplashViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/6 9:34
 */
public class ModelFactory implements ViewModelProvider.Factory {
    private Repository mRepository;

    public ModelFactory(Repository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        T t = null;
        if (modelClass == SplashViewModel.class) {
            t = (T) new SplashViewModel(mRepository);
        } else if (modelClass == MainViewModel.class) {
            t = (T) new MainViewModel(mRepository);
        } else if (modelClass == TranslateViewModel.class) {
            t = (T) new TranslateViewModel(mRepository);
        } else if (modelClass == CollectViewModel.class) {
            t = (T) new CollectViewModel(mRepository);
        }
        return t;
    }
}
