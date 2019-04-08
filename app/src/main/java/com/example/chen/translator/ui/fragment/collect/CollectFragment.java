package com.example.chen.translator.ui.fragment.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/5 22:57
 */
public class CollectFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.no_collection)
    TextView mNoCollection;

    private List<Translation> mTranslationList;
    private TranslationAdapter mAdapter;
    private CollectViewModel mViewModel;

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(CollectViewModel.class);

        mTranslationList = new ArrayList<>();
        mTranslationList = mViewModel.getCollectedTranslation();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new TranslationAdapter(R.layout.item_translation, mTranslationList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view12, position) -> {

        });
        mAdapter.setOnItemChildClickListener((adapter, view12, position) -> {
//            Translation t = mTranslationList.get(position);
//            t.setIsCollected(!t.getIsCollected());
//            mAdapter.notifyDataSetChanged();
//            mViewModel.setCollected(t);
        });
        setNoCollection();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //解决该 Fragment hide -> show 之后刷新数据
        if (!hidden) {
            mTranslationList.clear();
            mTranslationList.addAll(mViewModel.getCollectedTranslation());
            mAdapter.notifyDataSetChanged();
            setNoCollection();
        }
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    private void setNoCollection() {
        if (mTranslationList.size() == 0)
            mNoCollection.setVisibility(View.VISIBLE);
        else
            mNoCollection.setVisibility(View.GONE);
    }
}
