package com.ldp.cornucopia.ui.mvp.presenter;

import com.ldp.cornucopia.ui.base.BasePresenter;

/**
 * RefreshAndLoadingModel
 * Created by ldp on 16/9/26.
 */

public interface RefreshAndLoadingPresenter extends BasePresenter {

    void onRefresh(int refreshTime);

    void onLoad();
}
