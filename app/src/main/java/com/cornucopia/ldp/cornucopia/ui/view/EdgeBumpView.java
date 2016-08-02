package com.cornucopia.ldp.cornucopia.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 边缘凹凸的View
 * <p>参考文档：http://blog.csdn.net/yissan/article/details/51429281</p>
 * Created by ldp on 16/8/2.
 */
public class EdgeBumpView extends LinearLayout {

    private Paint mPaint;
    // 圆之间的距离
    private float gap = 8;
    // 圆半径
    private float radius = 20;
    // 圆数量
    private int circleNum;
    // 圆的数量非整数时，多余的部分
    private float remain;

    public EdgeBumpView(Context context) {
        super(context);
    }

    public EdgeBumpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public EdgeBumpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            // 计算不能整除的剩余部分
            remain = (int) (w - gap) % (2 * radius + gap);
        }
        // 圆的数量 = 圆间距的数量 - 1
        circleNum = (int) ((w - gap) / (2 * radius + gap));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++) {
            /**
             * x: 圆心的X轴坐标
             * remain/2是因为，可以一些情况，计算出来的可以画的数量不是刚好整除的。这样就会出现右边最后一个间距会比其它的间距都要宽。
             * 所以我们在绘制第一个的时候加上了余下的间距的一半，即使是不整除的情况。至少也能保证第一个和最后一个间距宽度一致。
             */
            float x = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            canvas.drawCircle(x, 0, radius, mPaint);
            canvas.drawCircle(x, getHeight(), radius, mPaint);
        }
    }
}
