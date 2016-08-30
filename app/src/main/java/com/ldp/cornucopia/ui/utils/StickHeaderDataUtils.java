package com.ldp.cornucopia.ui.utils;

import com.ldp.cornucopia.model.StickHeaderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * StickHeader - 数据模拟
 * Created by ldp on 16/8/5.
 */
public class StickHeaderDataUtils {
    public static final int MODEL_COUNT = 30;

    public static List<StickHeaderModel> getData() {
        List<StickHeaderModel> stickyExampleModels = new ArrayList<>();

        for (int index = 0; index < MODEL_COUNT; index++) {
            if (index < 5) {
                stickyExampleModels.add(new StickHeaderModel(
                        "吸顶文本1", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 15) {
                stickyExampleModels.add(new StickHeaderModel(
                        "吸顶文本2", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 25) {
                stickyExampleModels.add(new StickHeaderModel(
                        "吸顶文本3", "name" + index, "gender" + index, "profession" + index));
            } else {
                stickyExampleModels.add(new StickHeaderModel(
                        "吸顶文本4", "name" + index, "gender" + index, "profession" + index));
            }
        }

        return stickyExampleModels;
    }
}
