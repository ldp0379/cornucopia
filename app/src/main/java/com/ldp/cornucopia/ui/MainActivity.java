package com.ldp.cornucopia.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.activity.menu.BarsActivity;
import com.ldp.cornucopia.ui.activity.menu.MaterialDesignActivity;
import com.ldp.cornucopia.ui.activity.menu.RecyclerViewActivity;
import com.ldp.cornucopia.ui.activity.menu.SelfViewActivity;
import com.ldp.cornucopia.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.main_list)
    ListView mMainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initAdapter();
    }

    private void initAdapter() {
        String[] listItem = getResources().getStringArray(R.array.main_list_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.adapter_item, R.id
                .adapter_item_tv, listItem);
        mMainList.setAdapter(adapter);

        mMainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onClickView(i);
            }
        });
    }

    /**
     * ListView的item点击处理事件
     *
     * @param position item position
     */
    private void onClickView(int position) {
        switch (position) {
            case 0:
                startActivityWithoutParams(RecyclerViewActivity.class);
                break;
            case 1:
                startActivityWithoutParams(SelfViewActivity.class);
                break;
            case 2:
                startActivityWithoutParams(MaterialDesignActivity.class);
                break;
            case 3:
                startActivityWithoutParams(BarsActivity.class);
                break;
        }
    }
}
