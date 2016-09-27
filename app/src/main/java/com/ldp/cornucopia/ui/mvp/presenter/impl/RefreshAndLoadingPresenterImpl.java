package com.ldp.cornucopia.ui.mvp.presenter.impl;

import com.ldp.cornucopia.ui.mvp.listener.OnRefreshAndLoadingListener;
import com.ldp.cornucopia.ui.mvp.model.RefreshAndLoadingModel;
import com.ldp.cornucopia.ui.mvp.model.impl.RefreshAndLoadingModelImpl;
import com.ldp.cornucopia.ui.mvp.presenter.RefreshAndLoadingPresenter;
import com.ldp.cornucopia.ui.mvp.view.RefreshAndLoadingView;

import java.util.ArrayList;

/**
 * RefreshAndLoadingPresenterImpl
 * Created by ldp on 16/9/27.
 */

public class RefreshAndLoadingPresenterImpl implements RefreshAndLoadingPresenter, OnRefreshAndLoadingListener {
    private RefreshAndLoadingView view;
    private RefreshAndLoadingModel model;

    public RefreshAndLoadingPresenterImpl(RefreshAndLoadingView view) {
        this.view = view;
        this.model = new RefreshAndLoadingModelImpl();
    }

    @Override
    public void onRefresh(int refreshTime) {
        model.getRefreshData(refreshTime, this);
    }

    @Override
    public void onLoad() {
        model.getLoadData(this);
    }

    @Override
    public void onLoadDataSuccess(ArrayList<String> data) {
        view.onLoadFinish(data);
    }

    @Override
    public void onRefreshDataSuccess(ArrayList<String> data) {
        view.onRefreshFinish(data);
    }
}
