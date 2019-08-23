package com.example.chen.translator.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.chen.translator.R;
import com.example.chen.translator.ui.main.MainActivity;
import com.example.chen.translator.utils.Inject;

/**
 * @author chenshuaiyu
 */
public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.content) TextView content;
    @BindView(R.id.note) TextView note;
    @BindView(R.id.image_view) ImageView image;

    private SplashViewModel mViewModel;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mUnbinder = ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(SplashViewModel.class);

        mViewModel.getDailyEnglish()
                .observe(this, dailyEnglish -> {
                    content.setText(dailyEnglish.getContent());
                    note.setText(dailyEnglish.getNote());
                    Glide.with(this).load(dailyEnglish.getPicture2()).into(image);
                });

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
