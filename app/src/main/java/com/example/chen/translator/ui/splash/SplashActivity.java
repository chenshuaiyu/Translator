package com.example.chen.translator.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chen.translator.R;
import com.example.chen.translator.app.Constants;
import com.example.chen.translator.data.model.Bing;
import com.example.chen.translator.ui.main.MainActivity;
import com.example.chen.translator.utils.Inject;

public class SplashActivity extends AppCompatActivity {

    private TextView content;
    private TextView note;
    private ImageView image;

    private SplashViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(SplashViewModel.class);

        content = findViewById(R.id.content);
        note = findViewById(R.id.note);
        image = findViewById(R.id.image_view);

        mViewModel.getBingPicture()
                .observe(this, bing -> {
                    Glide.with(this).load(Constants.BING_BASE_URL + bing.getImages().get(0).getUrl()).into(image);
                });


        mViewModel.getDailyEnglish()
                .observe(this, dailyEnglish -> {
                    content.setText(dailyEnglish.getContent());
                    note.setText(dailyEnglish.getNote());
                });

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 3000);
    }
}
