package com.ldp.cornucopia.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cornucopia.ldp.cornucopia.R.id.fab;

public class ScrollingActivity extends BaseActivity {

    private static final String COLLAPSING_TOOL_BAR_LAYOUT_STATE_EXPAND = "expand";// 展开
    private static final String COLLAPSING_TOOL_BAR_LAYOUT_STATE_COLLAPSED = "collapsed";// 折叠
    private static final String COLLAPSING_TOOL_BAR_LAYOUT_STATE_INTERMEDIATE = "intermediate";// 中间

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(fab)
    FloatingActionButton mFab;
    @BindView(R.id.seeImageButton)
    ButtonBarLayout mSeeImageButton;

    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initAppBar();
        initFab();
    }

    @OnClick(R.id.seeImageButton)
    public void onClickSeeImageButton() {
        mAppBar.setExpanded(true);
    }


    private void initAppBar() {
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (!COLLAPSING_TOOL_BAR_LAYOUT_STATE_EXPAND.equals(state)) {
                        state = COLLAPSING_TOOL_BAR_LAYOUT_STATE_EXPAND;
                        mToolbarLayout.setTitle("expand");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (!COLLAPSING_TOOL_BAR_LAYOUT_STATE_COLLAPSED.equals(state)) {
                        state = COLLAPSING_TOOL_BAR_LAYOUT_STATE_COLLAPSED;
                        mToolbarLayout.setTitle("");
                        mSeeImageButton.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (!COLLAPSING_TOOL_BAR_LAYOUT_STATE_INTERMEDIATE.equals(state)) {
                        if (COLLAPSING_TOOL_BAR_LAYOUT_STATE_COLLAPSED.equals(state)) {
                            mSeeImageButton.setVisibility(View.GONE);
                        }
                        state = COLLAPSING_TOOL_BAR_LAYOUT_STATE_INTERMEDIATE;
                        mToolbarLayout.setTitle("intermediate");
                    }
                }
            }
        });
    }

    private void initFab() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
