package com.ldp.cornucopia.ui.base;

import android.view.KeyEvent;

/**
 * 基类activity，封装了对内部Fragment的基本操作
 * Created by ldp on 16/7/29.
 */
public abstract class BaseFragmentActivity extends BaseActivity {
    // 布局文件ID
    protected abstract int getContentViewId();

    // 布局中的Fragment的ID
    protected abstract int getFragmentContentId();

    /**
     * 添加fragment
     * <p/>
     * addToBackStack():保存事物，因此用户可以退回事物，否则事物提交后fragment会被销毁
     * commitAllowingStateLoss()：在发生异常时状态值丢失的情况下也能正常提交事物
     *
     * @param fragment 被添加的fragment
     */
    protected void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    // 移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    // 处理返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
