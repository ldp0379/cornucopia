package com.ldp.cornucopia.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.base.BaseActivity;
import com.ldp.cornucopia.ui.utils.BlurBitmapUtils;
import com.ldp.cornucopia.ui.utils.ViewSwitchUtils;
import com.ldp.cornucopia.ui.view.recyclerViewCardGallery.CardAdapter;
import com.ldp.cornucopia.ui.view.recyclerViewCardGallery.CardScaleHelper;
import com.ldp.cornucopia.ui.view.recyclerViewCardGallery.SpeedRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 使用RecyclerView实现Gallery画廊效果
 * Created by ldp on 16/9/7.
 */
public class RecyclerViewCardGalleryActivity extends BaseActivity {

    @BindView(R.id.blurView)
    ImageView mBlurView;
    @BindView(R.id.speed_recycler_view)
    SpeedRecyclerView mSpeedRecyclerView;

    private List<Integer> mList = new ArrayList<>();
    private CardScaleHelper mCardScaleHelper = null;
    private Runnable mBlurRunnable;
    private int mLastPos = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_card_gallery);
        ButterKnife.bind(this);

        initImages();
    }

    private void initImages() {
        mList.add(R.mipmap.pic4);
        mList.add(R.mipmap.pic5);
        mList.add(R.mipmap.pic6);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mSpeedRecyclerView.setLayoutManager(linearLayoutManager);
        mSpeedRecyclerView.setAdapter(new CardAdapter(mList));
        // mRecyclerView绑定scale效果
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(2);
        mCardScaleHelper.attachToRecyclerView(mSpeedRecyclerView);

        initBlurBackground();
    }

    private void initBlurBackground() {
        mBlurView = (ImageView) findViewById(R.id.blurView);
        mSpeedRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    notifyBackgroundChange();
                }
            }
        });

        notifyBackgroundChange();
    }

    private void notifyBackgroundChange() {
        if (mLastPos == mCardScaleHelper.getCurrentItemPos()) return;
        mLastPos = mCardScaleHelper.getCurrentItemPos();
        final int resId = mList.get(mCardScaleHelper.getCurrentItemPos());
        mBlurView.removeCallbacks(mBlurRunnable);
        mBlurRunnable = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
                ViewSwitchUtils.startSwitchBackgroundAnim(mBlurView, BlurBitmapUtils.getBlurBitmap(mBlurView.getContext(), bitmap, 15));
            }
        };
        mBlurView.postDelayed(mBlurRunnable, 500);
    }
}
