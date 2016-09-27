package com.ldp.cornucopia.ui.mvp.view;

import com.ldp.cornucopia.ui.base.BaseView;

import java.util.ArrayList;

/**
 * RefreshAndLoadingModel
 * Created by ldp on 16/9/26.
 */

public interface RefreshAndLoadingView extends BaseView {

    void onRefreshFinish(ArrayList<String> data);

    void onLoadFinish(ArrayList<String> data);
}
