package com.ldp.cornucopia.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.base.BaseListActivity;

import butterknife.BindView;

public class RecyclerViewActivity extends BaseListActivity {


    @BindView(R.id.view_list)
    ListView mViewList;

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
