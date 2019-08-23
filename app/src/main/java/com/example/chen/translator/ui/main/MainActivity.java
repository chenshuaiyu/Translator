package com.example.chen.translator.ui.main;

import com.bumptech.glide.Glide;
import com.example.chen.translator.app.Constants;
import com.example.chen.translator.ui.fragment.collect.CollectFragment;
import com.example.chen.translator.ui.fragment.settings.SettingsFragment;
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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author chenshuaiyu
 */
public class MainActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private ActionBar mActionBar;

    private ImageView mImageView;

    private Fragment curFragment;
    private TranslateFragment mTranslateFragment;
    private CollectFragment mCollectFragment;
    private SettingsFragment mSettingsFragment;
    private FragmentManager mFragmentManager;

    private MainViewModel mViewModel;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(MainViewModel.class);
        mUnbinder = ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(R.string.app_name);

        mTranslateFragment = new TranslateFragment();
        mCollectFragment = new CollectFragment();
        mSettingsFragment = new SettingsFragment();
        curFragment = mTranslateFragment;
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container, mTranslateFragment)
                .add(R.id.fragment_container, mCollectFragment)
                .add(R.id.fragment_container, mSettingsFragment)
                .hide(mCollectFragment)
                .hide(mSettingsFragment)
                .commit();

        mImageView = mNavigationView.getHeaderView(0).findViewById(R.id.image_view);
        mViewModel.getBingPicture()
                .observe(this, bing ->
                        Glide.with(this).load(Constants.BING_BASE_URL + bing.getImages().get(0).getUrl()).into(mImageView)
                );

        mNavigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    FragmentTransaction transaction = mFragmentManager.beginTransaction().hide(curFragment);
                    switch (menuItem.getItemId()) {
                        case R.id.menu_home:
                            if (curFragment != mTranslateFragment) {
                                curFragment = mTranslateFragment;
                            }
                            mActionBar.setTitle(R.string.app_name);
                            break;
                        case R.id.menu_collection:
                            if (curFragment != mCollectFragment) {
                                curFragment = mCollectFragment;
                            }
                            mActionBar.setTitle(R.string.collection);
                            break;
                        case R.id.menu_settings:
                            if (curFragment != mSettingsFragment) {
                                curFragment = mSettingsFragment;
                            }
                            mActionBar.setTitle(R.string.settings);
                            break;
                        default:
                            break;
                    }
                    transaction.show(curFragment).commit();
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
            default:
                break;
        }
        return true;
    }
}
