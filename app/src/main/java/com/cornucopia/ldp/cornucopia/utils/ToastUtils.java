package com.cornucopia.ldp.cornucopia.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtils
 * Created by ldp on 16/8/3.
 */
public class ToastUtils {
    private static Toast toast;
    // 弹出toast, 避免生成多个toast对象，点击后连续弹出
    public static void showToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }
}
