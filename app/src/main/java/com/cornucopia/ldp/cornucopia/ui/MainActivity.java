package com.cornucopia.ldp.cornucopia.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.cornucopia.ldp.cornucopia.R;
import com.cornucopia.ldp.cornucopia.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.customize_recycleView)
    TextView mCustomizeRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.customize_recycleView)
    void onClickCustomizeRecycleView() {
        showToast("onClickCustomizeRecycleView");
    }
}
