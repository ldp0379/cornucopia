package com.ldp.cornucopia.ui.activity.menu;

import android.os.Bundle;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.base.BaseListActivity;

/**
 * StatusBar/ActionBar/NavigationBar Activity
 * Created by ldp on 16/9/21.
 */

public class BarsActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAdapter(getResources().getStringArray(R.array.status_action_navigation_bar));
    }

    @Override
    protected void onClickView(int position) {
        super.onClickView(position);
        switch (position) {
            case 0:
                startActivityWithoutParams(StatusBarActivity.class);
                break;
        }
    }
}
