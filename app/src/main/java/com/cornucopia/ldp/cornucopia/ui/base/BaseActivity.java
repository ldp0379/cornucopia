package com.cornucopia.ldp.cornucopia.ui.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * activity基类
 * Created by ldp on 16/7/29.
 */
public class BaseActivity extends AppCompatActivity {

    // startActivity无需参数
    protected void startActivityWithoutParams(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    // startActivity，同时清除栈中的activity
    protected void startActivityWithoutParamsAndClearTop(Class clazz) {
        Intent intent = new Intent(this, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
