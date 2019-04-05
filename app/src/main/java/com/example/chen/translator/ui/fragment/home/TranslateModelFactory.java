package com.example.chen.translator.ui.fragment.home;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.chen.translator.data.Repository;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/2 22:58
 */
public class TranslateModelFactory implements ViewModelProvider.Factory {
    private Repository mRepository;

    public TranslateModelFactory(Repository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TranslateViewModel(mRepository);
    }
}
