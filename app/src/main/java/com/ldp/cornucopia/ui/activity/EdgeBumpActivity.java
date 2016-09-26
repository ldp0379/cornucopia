package com.ldp.cornucopia.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.common.base.BaseActivity;

/**
 * EdgeBumpView - activity
 * Created by ldp on 16/8/2.
 */
public class EdgeBumpActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edge_bump);

        showActionBarBack();
    }
}
