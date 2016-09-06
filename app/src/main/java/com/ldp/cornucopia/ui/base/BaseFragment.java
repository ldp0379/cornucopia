package com.ldp.cornucopia.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * fragment基类
 * Created by ldp on 16/7/29.
 */
public abstract class BaseFragment extends Fragment {

    // 为了防止Activity被销毁后，getActivity()返回null，使用全局变量替代getActivity()
    protected BaseActivity mActivity;

    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }
}
