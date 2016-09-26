package com.ldp.cornucopia.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cornucopia.ldp.cornucopia.R;
import com.ldp.cornucopia.ui.adapter.StickHeaderRVAdapter;
import com.ldp.cornucopia.common.base.BaseActivity;
import com.ldp.cornucopia.ui.utils.StickHeaderDataUtils;
import com.ldp.cornucopia.common.utils.SnackBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 顶部悬浮的RecycleView
 * <p>参考：https://github.com/christmasjason/StickyHeaderView</p>
 * <p>
 * Created by ldp on 16/7/29.
 */
public class StickyHeaderRecycleViewActivity extends BaseActivity {

    @BindView(R.id.rv_sticky_example)
    RecyclerView mRVStickyExample;
    @BindView(R.id.tv_sticky_header_view)
    TextView mTVStickyHeaderView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_header_rv);
        ButterKnife.bind(this);

        showActionBarBack();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRVStickyExample.setLayoutManager(new LinearLayoutManager(this));
        mRVStickyExample.setAdapter(new StickHeaderRVAdapter(this, StickHeaderDataUtils.getData()));
        mRVStickyExample.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View stickyInfoView = recyclerView.findChildViewUnder(mTVStickyHeaderView.getMeasuredWidth() / 2, 5);
                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    mTVStickyHeaderView.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }

                // 找到RecyclerView的item中，和RecyclerView的getTop 向下相距5个像素的那个item
                // (尝试2、3个像素位置都找不到，所以干脆用了5个像素)，
                // 我们根据这个item，来更新吸顶布局的内容，
                // 因为我们的StickyLayout展示的信息肯定是最上面的那个item的信息.
                View transInfoView = recyclerView.findChildViewUnder(
                        mTVStickyHeaderView.getMeasuredWidth() / 2, mTVStickyHeaderView.getMeasuredHeight() + 1);

                if (transInfoView != null && transInfoView.getTag() != null) {
                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - mTVStickyHeaderView.getMeasuredHeight();
                    // 如果当前item需要展示StickyLayout，
                    // 那么根据这个item的getTop和FakeStickyLayout的高度相差的距离来滚动FakeStickyLayout.
                    // 这里有一处需要注意，如果这个item的getTop已经小于0，也就是滚动出了屏幕，
                    // 那么我们就要把假的StickyLayout恢复原位，来覆盖住这个item对应的吸顶信息.
                    if (transViewStatus == StickHeaderRVAdapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            mTVStickyHeaderView.setTranslationY(dealtY);
                        } else {
                            mTVStickyHeaderView.setTranslationY(0);
                        }
                    } else if (transViewStatus == StickHeaderRVAdapter.NONE_STICKY_VIEW) {
                        // 如果当前item不需要展示StickyLayout，那么就不会引起FakeStickyLayout的滚动.
                        mTVStickyHeaderView.setTranslationY(0);
                    }
                }
            }
        });
    }

    @OnClick(R.id.fab)
    public void onClickFab() {
        SnackBarUtil.LongSnackbar(mFab, "onClick", Color.RED, Color.WHITE);
    }
}
