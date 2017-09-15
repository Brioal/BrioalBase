package com.brioal.baselib.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.brioal.baselib.utils.log.BLog;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2017/9/15.
 */

public class FlexLoadingView extends android.support.v7.widget.AppCompatImageView {
    private float mFlexOffset;//要伸缩的长度
    private int mLinesCount;//要显示的线条数量
    private int mWidth;//宽度
    private int mHeight;//高度
    private float mCurrentFlexOffset;//当前的高度

    private Paint mPaint;

    public FlexLoadingView(Context context) {
        this(context, null);
    }

    public FlexLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void closeAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(mFlexOffset, mFlexOffset / 3 );
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentFlexOffset = (Float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startAnimation();
            }
        });
        animator.setStartDelay(200);
        animator.start();
    }

    /**
     * 开始动画
     */
    private void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(mFlexOffset / 3, mFlexOffset);
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentFlexOffset = (Float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                closeAnimation();
            }
        });
        animator.start();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
    }

    /**
     * 设置要显示的线条数量
     *
     * @param linesCount
     * @return
     */
    public FlexLoadingView setLinesCount(int linesCount) {
        mLinesCount = linesCount;
        return this;
    }

    /**
     * 设置背景
     *
     * @param backDrawable
     */
    public FlexLoadingView setBackSrc(Drawable backDrawable) {
        setBackgroundDrawable(backDrawable);
        return this;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置为正方形
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = Math.min(mWidth, mHeight);
        mHeight = mWidth;
        mFlexOffset = mHeight / 3;
        mCurrentFlexOffset = mFlexOffset;
        setMeasuredDimension(mWidth, mHeight);
    }

    private boolean isStarted = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制拦截的白色方框
        if (mLinesCount > 2) {
            //线条的宽度
            float singleLineWidth = mWidth * 1.0f / (mLinesCount * 2 + 1);
            //绘制所有线条
            for (int i = 0; i <= mLinesCount; i++) {
                //方块内容
                RectF rect = new RectF(singleLineWidth * (2 * i), 0, singleLineWidth * (2 * i + 1), mHeight);
                canvas.drawRect(rect, mPaint);
            }
        }
        if (mCurrentFlexOffset < mFlexOffset / 3) {
            mCurrentFlexOffset = mFlexOffset / 3;
        }
        //动态绘制遮挡层
        //指定高度的方块
        RectF topRectf = new RectF(0, 0, mWidth, mCurrentFlexOffset);
        RectF bottomRectf = new RectF(0, mHeight - mCurrentFlexOffset, mWidth, mHeight);
        BLog.title(mCurrentFlexOffset + "");
        //绘制
        canvas.drawRect(topRectf, mPaint);
        canvas.drawRect(bottomRectf, mPaint);
        if (!isStarted) {
            closeAnimation();
            isStarted = true;
        }
    }
}
