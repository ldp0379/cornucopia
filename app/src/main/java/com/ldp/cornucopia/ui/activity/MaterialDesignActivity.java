package com.ldp.cornucopia.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ldp on 16/9/2.
 */
public class MaterialDesignActivity extends BaseActivity {

    @BindView(R.id.view_list)
    ListView mViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        showActionBarBack();

        initAdapter();
    }

    private void initAdapter() {
        String[] listItem = getResources().getStringArray(R.array.material_design_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.adapter_item, R.id
                .adapter_item_tv, listItem);
        mViewList.setAdapter(adapter);

        mViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                startActivityWithoutParams(CoordinatorLayoutActivity.class);
                break;
        }
    }
}
