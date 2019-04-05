package com.example.chen.translator.ui.fragment.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chen.translator.data.Repository;
import com.example.chen.translator.data.dao.Translation;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/2 22:58
 */
public class TranslateViewModel extends ViewModel {
    private Repository mRepository;

    public TranslateViewModel(Repository repository) {
        mRepository = repository;
    }

    public MutableLiveData<Translation> getTranslation(String query){
        return mRepository.getTranslation(query);
    }

    public void addTranslation(Translation translation) {
        mRepository.addTranslation(translation);
    }

    public List<Translation> getAllTranslation() {
        return mRepository.getAllTranslation();
    }
}
