package com.example.chen.translator.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.example.chen.translator.R;
import com.example.chen.translator.ui.main.MainActivity;
import com.example.chen.translator.utils.Inject;

public class SplashActivity extends AppCompatActivity {

    private TextView content;
    private TextView note;

    private SplashViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mViewModel = ViewModelProviders.of(this, Inject.getSplashModelFactory()).get(SplashViewModel.class);

        content = findViewById(R.id.content);
        note = findViewById(R.id.note);

        mViewModel.getDailyEnglish()
                .observe(this, dailyEnglish -> {
                    content.setText(dailyEnglish.getContent());
                    note.setText(dailyEnglish.getNote());
                });

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }, 3000);
    }
}
