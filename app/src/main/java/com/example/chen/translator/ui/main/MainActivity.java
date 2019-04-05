package com.example.chen.translator.ui.main;

import com.bumptech.glide.Glide;
import com.example.chen.translator.app.Constants;
import com.example.chen.translator.utils.Inject;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.chen.translator.R;
import com.example.chen.translator.ui.fragment.home.TranslateFragment;

import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private ImageView mImageView;

    private TranslateFragment mTranslateFragment;
    private MainViewModel mViewModel;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this, Inject.getMainModelFactory()).get(MainViewModel.class);
        mUnbinder = ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mTranslateFragment = new TranslateFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mTranslateFragment)
                .commit();

        mImageView = mNavigationView.getHeaderView(0).findViewById(R.id.image_view);
        mViewModel.getBingPicture()
                .observe(this, bing ->
                        Glide.with(this).load(Constants.BING_BASE_URL + bing.getImages().get(0).getUrl()).into(mImageView)
                );

        mNavigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.menu_home:
                            break;
                        case R.id.menu_collection:
                            break;
                        case R.id.menu_settings:
                            break;
                        default:
                            break;
                    }
                    mDrawerLayout.closeDrawer(Gravity.START);
                    return true;
                });

    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                break;
        }
        return true;
    }
}
