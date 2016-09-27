package com.ldp.cornucopia.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.common.base.BaseActivity;
import com.ldp.cornucopia.ui.adapter.RefreshAndLoadingRVAdapter;
import com.ldp.cornucopia.ui.mvp.presenter.RefreshAndLoadingPresenter;
import com.ldp.cornucopia.ui.mvp.presenter.impl.RefreshAndLoadingPresenterImpl;
import com.ldp.cornucopia.ui.mvp.view.RefreshAndLoadingView;
import com.ldp.cornucopia.ui.view.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 下拉刷新和上拉加载更多的RecyclerView Activity
 * Created by ldp on 16/8/30.
 */
public class RefreshAndLoadingRVActivity extends BaseActivity implements RefreshAndLoadingView {

    private RefreshAndLoadingPresenter presenter;

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
        presenter = new RefreshAndLoadingPresenterImpl(this);

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
                presenter.onRefresh(refreshTime);
            }

            @Override
            public void onLoadMore() {
                presenter.onLoad();
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

    @Override
    public void onRefreshFinish(ArrayList<String> data) {
        listData.clear();
        listData.addAll(data);
        mAdapter.notifyDataSetChanged();
        mRefreshAndLoadingRv.refreshComplete();
    }

    @Override
    public void onLoadFinish(ArrayList<String> data) {
        listData.addAll(data);
        if(times < 2){
            mRefreshAndLoadingRv.loadMoreComplete();
        } else {
            mRefreshAndLoadingRv.setNoMore(true);
        }
        mAdapter.notifyDataSetChanged();
    }
}
