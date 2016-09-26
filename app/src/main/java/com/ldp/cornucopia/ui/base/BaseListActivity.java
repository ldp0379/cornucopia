package com.ldp.cornucopia.ui.base;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 列表页Activity的基类
 * Created by ldp on 16/9/21.
 */

public class BaseListActivity extends BaseActivity {
    @BindView(R.id.view_list)
    ListView mViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        showActionBarBack();
    }

    protected void initAdapter(String[] listItem) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.adapter_item,
                R.id.adapter_item_tv, listItem);
        mViewList.setAdapter(adapter);

        mViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onClickView(i);
            }
        });
    }

    protected void onClickView(int position) {

    }
}
