package com.hyy.android.common.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hyy.android.common.R;

public class SuperCircleView extends View {
    private final String TAG = SuperCircleView.class.getSimpleName();

    private ValueAnimator valueAnimator;
    private int mViewCenterX; //view宽的中心点（可暂时理解为圆心）
    private int mViewCenterY; //view高的中心点（可暂时理解为圆心）

    private int mMinRadio; //最里边白色圆的半径
    private float mRingWidth; //圆环的宽度
    private int mMinCircleColor; //最里面圆的颜色
    private int mRingNormalColor; //默认圆环的颜色
    private Paint mPaint;
    private int color[] = new int[3];

    private RectF mRectF; // 圆环的矩形区域
    private int mSelectRing = 0; //要显示的彩色区域（随数值变化）
    private int mMaxValue;


    public SuperCircleView(Context context) {
        this(context, null);
    }

    public SuperCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SuperCircleView);
        //最里面的白色圆的半径
        mMinRadio = a.getInteger(R.styleable.SuperCircleView_min_circle_radio, 300);
        //圆环的宽度
        mRingWidth = a.getFloat(R.styleable.SuperCircleView_ring_width, 40);

        //最里面的圆的颜色（绿色）
        mRingNormalColor = a.getColor(R.styleable.SuperCircleView_circle_color, context.getResources().getColor(R.color.hyy_commlib_green));
        //圆环的默认颜色（圆环占据的里面圆的空间）
        mRingNormalColor = a.getColor(R.styleable.SuperCircleView_ring_normal_color, context.getResources().getColor(R.color.hyy_commlib_gray));
        //圆环要显示的彩色的区域
        mSelectRing = a.getInt(R.styleable.SuperCircleView_ring_color_select, 0);

        mMaxValue = a.getInt(R.styleable.SuperCircleView_maxValue, 100);

        a.recycle();

        //抗锯齿画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //防止边缘锯齿
        mPaint.setAntiAlias(true);
        //需要重写onDraw就得调用此
        this.setWillNotDraw(false);

        //圆环渐变的颜色
        color[0] = Color.parseColor("#FFD300");
        color[1] = Color.parseColor("#FF0084");
        color[2] = Color.parseColor("#16FF00");

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //view的宽和高，相对于父布局（用于确定圆心）
        int viewWidth = getMeasuredWidth();
        int viewHeight = getMeasuredHeight();

        mViewCenterX = viewWidth / 2;
        mViewCenterY = viewHeight / 2;

        //画矩形
        mRectF = new RectF(mViewCenterX - mMinRadio - mRingWidth / 2, mViewCenterY - mMinRadio - mRingWidth / 2, mViewCenterX + mMinRadio + mRingWidth / 2, mViewCenterY + mMinRadio + mRingWidth / 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mMinCircleColor);
        canvas.drawCircle(mViewCenterX, mViewCenterY, mMinRadio, mPaint);

        //画默认圆环
        drawNormalRing(canvas);
        //画彩色圆环
        drawColorRing(canvas);

    }

    /////

    /**
     * 画默认圆环
     *
     * @param canvas
     */
    private void drawNormalRing(Canvas canvas) {
        Paint ringNormalPaint = new Paint(mPaint);
        ringNormalPaint.setStyle(Paint.Style.STROKE);
        ringNormalPaint.setStrokeWidth(mRingWidth);
        ringNormalPaint.setColor(mRingNormalColor); //圆环默认颜色为灰色
        canvas.drawArc(mRectF, 360, 360, false, ringNormalPaint);
    }


    private void drawColorRing(Canvas canvas) {

        Paint ringColorPaint = new Paint(mPaint);
        ringColorPaint.setStyle(Paint.Style.STROKE);
        ringColorPaint.setStrokeWidth(mRingWidth);
        ringColorPaint.setShader(new SweepGradient(mViewCenterX, mViewCenterY, color, null));
        //逆时针旋转90度
        canvas.rotate(-90, mViewCenterX, mViewCenterY);
        canvas.drawArc(mRectF, 360, mSelectRing, false, ringColorPaint);
        ringColorPaint.setShader(null);

    }


    //***********************************用于更新圆环表示的数值***************************************

    public void setValue(int value) {
        if (value > mMaxValue) {
            value = mMaxValue;
        }
        int start = 0;
        int end = value;
        startAnimator(start,end,2000);

    }

    private void startAnimator(int start, int end, long animTime) {
        valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(animTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG, "onAnimationUpdate: animation.getAnimatedValue()::" + animation.getAnimatedValue());
                int i = Integer.valueOf(String.valueOf(animation.getAnimatedValue()));
                //每个单位长度占多少
                mSelectRing = (int) (360 * (i/100f));
                Log.i(TAG, "onAnimationUpdate: mSelectRing::"+mSelectRing);
                invalidate();
            }
        });
        valueAnimator.start();
    }



    public void setmMinRadio(int radio){
        this.mMinRadio = radio;
        invalidate();
    }

}
