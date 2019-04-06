package com.example.chen.translator.ui.fragment.collect;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chen.translator.R;
import com.example.chen.translator.adapter.TranslationAdapter;
import com.example.chen.translator.data.dao.Translation;
import com.example.chen.translator.utils.Inject;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/5 22:57
 */
public class CollectFragment extends Fragment {
    private RecyclerView mRecyclerView;

    private List<Translation> mTranslationList;
    private TranslationAdapter mAdapter;
    private CollectViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect, container, false);
        if (mAdapter != null) {
            mTranslationList.clear();
            mTranslationList = mViewModel.getCollectedTranslation();
            mAdapter.notifyDataSetChanged();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(CollectViewModel.class);

        mTranslationList = new ArrayList<>();
        mTranslationList = mViewModel.getCollectedTranslation();
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new TranslationAdapter(R.layout.item_translation, mTranslationList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view12, position) -> {

        });
        mAdapter.setOnItemChildClickListener((adapter, view1, position) -> {

        });
    }
}
