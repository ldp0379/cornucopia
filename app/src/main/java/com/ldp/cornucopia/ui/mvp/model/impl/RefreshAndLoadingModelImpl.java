package com.ldp.cornucopia.ui.mvp.model.impl;

import android.os.Handler;

import com.ldp.cornucopia.ui.mvp.listener.OnRefreshAndLoadingListener;
import com.ldp.cornucopia.ui.mvp.model.RefreshAndLoadingModel;

import java.util.ArrayList;

/**
 * RefreshAndLoadingModel
 * Created by ldp on 16/9/26.
 */

public class RefreshAndLoadingModelImpl implements RefreshAndLoadingModel {

    @Override
    public void getRefreshData(int refreshTime, final OnRefreshAndLoadingListener listener) {
        ArrayList<String> data = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                for (int i = 0; i < 15; i++) {
                    data.add("item [" + i + "] after [" + refreshTime + "] times of refresh");
                }
                listener.onRefreshDataSuccess(data);
            }
        }, 1000);
    }

    @Override
    public void getLoadData(final OnRefreshAndLoadingListener listener) {
        ArrayList<String> data = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                for (int i = 0; i < 15; i++) {
                    data.add("item [" + (1 + i) + "]");
                }
                listener.onLoadDataSuccess(data);
            }
        }, 1000);
    }
}
