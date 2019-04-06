package com.example.chen.translator.ui.fragment.collect;

import com.example.chen.translator.data.Repository;
import com.example.chen.translator.data.dao.Translation;

import java.util.List;

import androidx.lifecycle.ViewModel;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/6 9:45
 */
public class CollectViewModel extends ViewModel {
    private Repository mRepository;

    public CollectViewModel(Repository repository) {
        mRepository = repository;
    }

    public List<Translation> getCollectedTranslation() {
        return mRepository.getCollectedTranslation();
    }
}
