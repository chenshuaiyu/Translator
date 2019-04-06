package com.example.chen.translator.ui.fragment.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chen.translator.R;
import com.example.chen.translator.adapter.TranslationAdapter;
import com.example.chen.translator.data.dao.Translation;
import com.example.chen.translator.utils.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/4 10:51
 */
public class TranslateFragment extends Fragment implements View.OnClickListener {
    //    @BindView(R.id.translate)
    Button translate;
    //    @BindView(R.id.input_content)
    EditText inputContent;
    //    @BindView(R.id.output_content)
    TextView outputContent;
    //    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    //    @BindView(R.id.collect)
    ImageView collect;
    //    @BindView(R.id.copy)
    ImageView copy;
    //    @BindView(R.id.share)
    ImageView share;

    private Translation curTranslation = null;

    private TranslateViewModel mViewModel;
    private List<Translation> mTranslationList;
    private TranslationAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(TranslateViewModel.class);

        translate = view.findViewById(R.id.translate);
        inputContent = view.findViewById(R.id.input_content);
        outputContent = view.findViewById(R.id.output_content);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        collect = view.findViewById(R.id.collect);
        copy = view.findViewById(R.id.copy);
        share = view.findViewById(R.id.share);

        mTranslationList = new ArrayList<>();
        mTranslationList = mViewModel.getAllTranslation();
        Collections.reverse(mTranslationList);

        mAdapter = new TranslationAdapter(R.layout.item_translation, mTranslationList);
        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            curTranslation = mTranslationList.get(position);
            mTranslationList.remove(position);
            mTranslationList.add(0, curTranslation);
            mAdapter.notifyDataSetChanged();

            mViewModel.deleteTranslation(curTranslation);
            curTranslation.setId(null);
            mViewModel.addTranslation(curTranslation);

            inputContent.setText(curTranslation.getInput());
            inputContent.setSelection(curTranslation.getInput().length());
            outputContent.setText(curTranslation.getOutput());
        });
        mAdapter.setOnItemChildClickListener((adapter, view12, position) -> {
            Translation t = mTranslationList.get(position);
            t.setIsCollected(!t.getIsCollected());
            mAdapter.notifyDataSetChanged();
            mViewModel.setCollected(t);
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        translate.setOnClickListener(
                v -> {
                    String q = inputContent.getText().toString();
                    if (!TextUtils.isEmpty(q)) {
                        Translation t = null;
                        for (int i = 0; i < mTranslationList.size(); i++) {
                            if (mTranslationList.get(i).getInput().equals(q))
                                t = mTranslationList.get(i);
                        }
                        if (t != null) {
                            mTranslationList.remove(t);
                            mTranslationList.add(0, t);
                            mViewModel.addTranslation(t);
                        } else {
                            MutableLiveData<Translation> liveData = mViewModel.getTranslationFromNet(q);
                            liveData.observe(this, translation -> {
                                        curTranslation = translation;
                                        mViewModel.addTranslation(curTranslation);

                                        mTranslationList.add(0, curTranslation);
                                        mAdapter.notifyDataSetChanged();
                                        outputContent.setText(translation.getOutput());
                                    }
                            );
                        }
                    }
                }
        );

        collect.setOnClickListener(this);
        copy.setOnClickListener(this);
        share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collect:
                //将标记位设为true
//                if (curTranslation != null) {
//                    collect.setImageResource(R.drawable.ic_collect);
//                }
                break;
            case R.id.copy:
                break;
            case R.id.share:
                break;
            default:
                break;
        }
    }
}
