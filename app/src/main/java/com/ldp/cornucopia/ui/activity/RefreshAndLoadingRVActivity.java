package com.ldp.cornucopia.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.adapter.RefreshAndLoadingRVAdapter;
import com.ldp.cornucopia.common.base.BaseActivity;
import com.ldp.cornucopia.ui.view.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 下拉刷新和上拉加载更多的RecyclerView Activity
 * Created by ldp on 16/8/30.
 */
public class RefreshAndLoadingRVActivity extends BaseActivity {

    @BindView(R.id.refresh_and_loading_rv)
    XRecyclerView mRefreshAndLoadingRv;

    private RefreshAndLoadingRVAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_and_loading_rv);
        ButterKnife.bind(this);

        showActionBarBack();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRefreshAndLoadingRv.setLayoutManager(manager);

        mRefreshAndLoadingRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listData.clear();
                        for (int i = 0; i < 15; i++) {
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        mRefreshAndLoadingRv.refreshComplete();
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {
                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 15; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRefreshAndLoadingRv.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 9; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRefreshAndLoadingRv.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times++;
            }
        });

        listData = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            listData.add("item" + i);
        }
        mAdapter = new RefreshAndLoadingRVAdapter(listData);

        mRefreshAndLoadingRv.setAdapter(mAdapter);
        mRefreshAndLoadingRv.setRefreshing(true);
    }
}
