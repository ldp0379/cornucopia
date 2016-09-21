package com.ldp.cornucopia.ui.activity.menu;

import android.os.Bundle;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.activity.EdgeBumpActivity;
import com.ldp.cornucopia.ui.base.BaseListActivity;

public class SelfViewActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAdapter(getResources().getStringArray(R.array.view_item));
    }

    @Override
    public void onClickView(int position) {
        switch (position) {
            case 0:
                startActivityWithoutParams(EdgeBumpActivity.class);
                break;
        }
    }
}
