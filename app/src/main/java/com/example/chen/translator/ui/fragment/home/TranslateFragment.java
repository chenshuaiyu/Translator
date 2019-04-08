package com.example.chen.translator.ui.fragment.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.translate)
    Button translate;
    @BindView(R.id.input_content)
    EditText inputContent;
    @BindView(R.id.output_content)
    TextView outputContent;
    @BindView(R.id.output)
    RelativeLayout output;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.collect)
    ImageView collect;
    @BindView(R.id.copy)
    ImageView copy;
    @BindView(R.id.share)
    ImageView share;

    private Translation curTranslation = null;

    private TranslateViewModel mViewModel;
    private List<Translation> mTranslationList;
    private TranslationAdapter mAdapter;

    private Unbinder mUnbinder;

    private boolean translateFirstClicked = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        // 不能传入getActivity()，加上第二个参数view
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(TranslateViewModel.class);

        initRecyclerView();

        output.setVisibility(View.GONE);
        inputContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!translateFirstClicked) {
                    output.setVisibility(View.GONE);
                } else {
                    output.setVisibility(View.VISIBLE);
                }
            }
        });

        translate.setOnClickListener(
                v -> {
                    translateFirstClicked = true;
                    String q = inputContent.getText().toString();
                    if (!TextUtils.isEmpty(q)) {
                        Translation t = null;
                        for (int i = 0; i < mTranslationList.size(); i++) {
                            if (mTranslationList.get(i).getInput().equals(q))
                                t = mTranslationList.get(i);
                        }
                        if (t != null) {
                            curTranslation = t;
                            mTranslationList.remove(curTranslation);
                            mTranslationList.add(0, curTranslation);
                            mAdapter.notifyDataSetChanged();
                            mViewModel.deleteTranslation(curTranslation);
                            curTranslation.setId(null);
                            mViewModel.addTranslation(curTranslation);

                            inputContent.setText(curTranslation.getInput());
                            inputContent.setSelection(curTranslation.getInput().length());
                            outputContent.setText(curTranslation.getOutput());
                        } else {
                            MutableLiveData<Translation> liveData = mViewModel.getTranslationFromNet(q);
                            liveData.observe(this, translation -> {
                                        curTranslation = translation;
                                        setCollectImageView(curTranslation);
                                        mViewModel.addTranslation(curTranslation);

                                        mTranslationList.add(0, curTranslation);
                                        mAdapter.notifyDataSetChanged();
                                        outputContent.setText(translation.getOutput());
                                    }
                            );
                        }
                        output.setVisibility(View.VISIBLE);
                    } else {
                        output.setVisibility(View.GONE);
                    }
                }
        );

        collect.setOnClickListener(this);
        copy.setOnClickListener(this);
        share.setOnClickListener(this);
    }

    private void initRecyclerView() {
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
            setCollectImageView(curTranslation);

            output.setVisibility(View.VISIBLE);
            //跳转到 index = 0 的位置
            mRecyclerView.scrollToPosition(0);
        });
        mAdapter.setOnItemChildClickListener((adapter, view12, position) -> {
            Translation t = mTranslationList.get(position);
            t.setIsCollected(!t.getIsCollected());
            setCollectImageView(t);
            mAdapter.notifyDataSetChanged();
            mViewModel.setCollected(t);
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collect:
                //将标记位设为true
                if (curTranslation != null) {
                    curTranslation.setIsCollected(!curTranslation.getIsCollected());
                    setCollectImageView(curTranslation);
                    mAdapter.notifyDataSetChanged();
                    mViewModel.setCollected(curTranslation);
                }
                break;
            case R.id.copy:
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText(curTranslation.getOutput(), curTranslation.getOutput());
                cm.setPrimaryClip(mClipData);
                Toast.makeText(getActivity(), R.string.copy, Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                share();
                break;
            default:
                break;
        }
    }

    private void setCollectImageView(Translation t) {
        collect.setImageResource(t.getIsCollected() ? R.drawable.ic_collect : R.drawable.ic_uncollect);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, curTranslation.getOutput());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.share)));
    }
}
