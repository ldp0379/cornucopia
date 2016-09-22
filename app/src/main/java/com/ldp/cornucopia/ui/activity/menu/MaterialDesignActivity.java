package com.ldp.cornucopia.ui.activity.menu;

import android.os.Bundle;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.activity.CoordinatorLayoutActivity;
import com.ldp.cornucopia.ui.activity.ScrollingActivity;
import com.ldp.cornucopia.ui.base.BaseListActivity;

/**
 * MaterialDesignActivity
 * Created by ldp on 16/9/2.
 */
public class MaterialDesignActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAdapter(getResources().getStringArray(R.array.material_design_view));
    }

    @Override
    public void onClickView(int position) {
        switch (position) {
            case 0:
                startActivityWithoutParams(CoordinatorLayoutActivity.class);
                break;
            case 1:
                startActivityWithoutParams(ScrollingActivity.class);
                break;
        }
    }
}
