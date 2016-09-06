package com.ldp.cornucopia.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.base.BaseActivity;
import com.ldp.cornucopia.ui.fragment.ContentFragment;
import com.ldp.cornucopia.ui.view.CoordinatorLayoutView.MDTab;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Material Design风格的tabBar
 * Created by ldp on 16/9/2.
 */
public class CoordinatorLayoutActivity extends BaseActivity {

    @BindView(R.id.coordinator_layout_vp)
    ViewPager mViewPager;
    @BindView(R.id.coordinator_layout_tab)
    MDTab mTab;

    private String[] mMenus = new String[]{"首页", "微聊", "我的"};
    private ArrayList<ContentFragment> mContentFragment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        ButterKnife.bind(this);

        initFragments();
        initViewPager();
        initTab();
    }

    private void initFragments() {
        ContentFragment fragment;
        Bundle bundle;
        for (int i = 0; i < mMenus.length; i++) {
            fragment = new ContentFragment();
            bundle = new Bundle();
            bundle.putString("content", mMenus[i]);
            fragment.setArguments(bundle);
            mContentFragment.add(fragment);
        }
    }

    private void initViewPager() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mContentFragment.get(position);
            }

            @Override
            public int getCount() {
                return mContentFragment.size();
            }
        });
    }

    private void initTab() {
        mTab.setAdapter(new ViewPagerAdapter());
        mTab.itemChecked(0);
        mTab.setOnItemCheckedListener(new MDTab.OnItemCheckedListener() {
            @Override
            public void onItemChecked(int position, View view) {
                mViewPager.setCurrentItem(position);
            }
        });

        mTab.setupWithViewPager(mViewPager);
    }

    class ViewPagerAdapter extends MDTab.TabAdapter {

        @Override
        public int getItemCount() {
            return mMenus.length;
        }

        // tab图标
        @Override
        public Drawable getDrawable(int position) {
            int res = getResources().getIdentifier("icon_" + position, "mipmap", getPackageName());
            return getResources().getDrawable(res);
        }

        // tab文本
        @Override
        public CharSequence getText(int position) {
            return mMenus[position];
        }
    }
}
