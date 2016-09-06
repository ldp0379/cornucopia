package com.ldp.cornucopia.ui.view.CoordinatorLayoutView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.cornucopia.ldp.cornucopia.R;

/**
 * 带有波纹效果的TextView
 * Created by ldp on 16/9/2.
 */
public class RippleButton extends TextView {

    private Paint mPaint;// 绘制波纹的画笔
    private int mStepSize;// 波纹变化的步长
    private int mMinRadius = 0;// 波纹变化的最小值（起始值）
    private int mRadius;// 波纹的大小
    private int mMaxRadius;// 波纹最大半径
    private int mCenterX;// 控件中心店X坐标
    private int mCenterY;// 控件中心店Y坐标
    private boolean isAnimating;// 是否正在动画中

    private OnBeforeClickedListener mListener;

    public RippleButton(Context context) {
        this(context, null, 0);
    }

    public RippleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        resolveAttrs(context, attrs, defStyle);
    }

    private void resolveAttrs(Context context, AttributeSet attrs, int defStyle) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Bar, defStyle, 0);
        mPaint.setColor(ta.getColor(R.styleable.Bar_ripple_color, Color.TRANSPARENT));
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 根据控件的大小来计算波纹的最大半径和步长
        mMaxRadius = Math.max(getMeasuredWidth(), getMeasuredHeight()) / 2;
        mCenterX = getMeasuredWidth() / 2;
        mCenterY = getMeasuredHeight() / 2;
        mStepSize = mMaxRadius / 20;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (mListener != null) {
                mListener.onBeforeClicked(this);
            }
            // 初始化波纹半径
            mRadius = mMinRadius;
            isAnimating = true;
            // 开始重绘，显示波纹，没有调用super.onTouchEvent()，不会触发点击事件
            postInvalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isAnimating) {
            super.onDraw(canvas);
            return;
        }

        if (isAnimating && mRadius > mMaxRadius) {
            isAnimating = false;
            mRadius = mMinRadius;
            // 相应点击事件
            performClick();
            super.onDraw(canvas);
            return;
        }

        mRadius += mStepSize;
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
        super.onDraw(canvas);
        postInvalidate();
    }

    public void setRippleColor(int color) {
        mPaint.setColor(color);
    }

    public void cancel() {
        isAnimating = false;
    }

    public void setOnBeforeClickedListener(OnBeforeClickedListener li) {
        mListener = li;
    }

    public interface OnBeforeClickedListener {
        void onBeforeClicked(View view);
    }
}
