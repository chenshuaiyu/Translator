package com.example.chen.translator.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chen.translator.R;
import com.example.chen.translator.data.dao.Translation;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/5 21:05
 */
public class TranslationAdapter extends BaseQuickAdapter<Translation, BaseViewHolder> {
    public TranslationAdapter(int layoutResId, @Nullable List<Translation> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Translation item) {
        helper.setText(R.id.input, item.getInput())
                .setText(R.id.output, item.getOutput());
        if (item.getIsCollected()) {
            helper.setImageResource(R.id.collect, R.drawable.ic_collect);
        } else {
            helper.setImageResource(R.id.collect, R.drawable.ic_uncollect_grey);
        }
        helper.addOnClickListener(R.id.collect);
    }
}
