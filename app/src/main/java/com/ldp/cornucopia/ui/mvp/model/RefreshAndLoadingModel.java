package com.ldp.cornucopia.ui.mvp.model;

import com.ldp.cornucopia.ui.mvp.listener.OnRefreshAndLoadingListener;

/**
 * RefreshAndLoadingModel
 * Created by ldp on 16/9/26.
 */

public interface RefreshAndLoadingModel {

    void getRefreshData(int refreshTime, OnRefreshAndLoadingListener listener);

    void getLoadData(OnRefreshAndLoadingListener listener);
}
