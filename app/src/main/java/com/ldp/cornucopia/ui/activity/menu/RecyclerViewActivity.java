package com.ldp.cornucopia.ui.activity.menu;

import android.os.Bundle;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.activity.RecyclerViewCardGalleryActivity;
import com.ldp.cornucopia.ui.activity.RefreshAndLoadingRVActivity;
import com.ldp.cornucopia.ui.activity.StickyHeaderRecycleViewActivity;
import com.ldp.cornucopia.ui.base.BaseListActivity;

public class RecyclerViewActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAdapter(getResources().getStringArray(R.array.recyclerView_item));
    }

    @Override
    public void onClickView(int position) {
        switch (position) {
            case 0:
                startActivityWithoutParams(StickyHeaderRecycleViewActivity.class);
                break;
            case 1:
                startActivityWithoutParams(RefreshAndLoadingRVActivity.class);
                break;
            case 2:
                startActivityWithoutParams(RecyclerViewCardGalleryActivity.class);
        }
    }
}
