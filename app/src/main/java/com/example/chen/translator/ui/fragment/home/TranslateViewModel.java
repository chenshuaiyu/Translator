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

    public MutableLiveData<Translation> getTranslationFromNet(String query){
        return mRepository.getTranslationFromNet(query);
    }

    public void addTranslation(Translation translation) {
        mRepository.addTranslation(translation);
    }

    public void deleteTranslation(Translation translation){
        mRepository.deleteTranslation(translation);
    }

    public List<Translation> getAllTranslation() {
        return mRepository.getAllTranslation();
    }

    public void setCollected(Translation translation) {
        mRepository.setCollected(translation);
    }
}
