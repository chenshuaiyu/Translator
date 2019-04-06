package com.example.chen.translator.ui.main;

import com.example.chen.translator.data.Repository;
import com.example.chen.translator.data.model.Bing;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/5 21:42
 */
public class MainViewModel extends ViewModel {
    private Repository mRepository;

    public MainViewModel(Repository repository) {
        mRepository = repository;
    }

    public MutableLiveData<Bing> getBingPicture() {
        return mRepository.getBingPicture();
    }
}
