package com.ldp.cornucopia.ui.mvp.listener;

import java.util.ArrayList;

/**
 * OnRefreshAndLoadingListener
 * Created by ldp on 16/9/27.
 */

public interface OnRefreshAndLoadingListener {

    void onLoadDataSuccess(ArrayList<String> data);

    void onRefreshDataSuccess(ArrayList<String> data);
}
