package com.hyy.android.common.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import com.hyy.android.common.R;


import java.util.Random;

public class RotateCaptchaViewGroup extends RelativeLayout {

    private static final String TAG = RotateCaptchaViewGroup.class.getSimpleName();

    private Context context;
    private LayoutInflater inflater;
    private View rotateCaptchaView;

    private ImageView captchaImageView;
    private ImageView captchaImageViewMask;
    private SuperCircleView circleView;
    private SeekBar rotationSeekBar;


    private float mMatchDeviation = 10f;
    private Random mRandom;
    ;
    private int randomDegree;


    private RotateCaptchaCallback callback;


    public RotateCaptchaViewGroup(Context context) {
        super(context);
        this.context = context;
        init(null, 0);
    }

    public RotateCaptchaViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs, 0);
    }

    public RotateCaptchaViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs, defStyleAttr);
    }


    private void init(AttributeSet attrs, int defStyle) {

        //TODO 解析xml中配置的属性值，目前不需要
        //**Future do TypedArray analysis and recycled HERE**

        initView();

        mRandom = new Random(System.nanoTime());

    }

    private void initView() {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rotateCaptchaView = inflater.inflate(R.layout.hyy_commlib_view_rotate_captcha, null);

        captchaImageView = rotateCaptchaView.findViewById(R.id.captchaImageView);
        captchaImageViewMask = rotateCaptchaView.findViewById(R.id.captchaImageViewMask);
        circleView = rotateCaptchaView.findViewById(R.id.circleView);
        rotationSeekBar = rotateCaptchaView.findViewById(R.id.rotationSeekBar);

        this.addView(rotateCaptchaView);


    }

    private void initData() {

        randomDegree = mRandom.nextInt(240) + 60;
        rotationSeekBar.setMax(10000);

        captchaImageView.post(new Runnable() {
            @Override
            public void run() {

                int cIvWidth = captchaImageView.getWidth();
                Log.d(TAG, "cIvWidth = " + cIvWidth);
                int cIvHeight = captchaImageView.getHeight();
                Log.d(TAG, "cIvHeight = " + cIvHeight);

                Drawable drawable = captchaImageView.getDrawable();
                if (drawable instanceof BitmapDrawable) {


                    Bitmap originBitmap = ((BitmapDrawable) drawable).getBitmap();
                    int width = originBitmap.getWidth();
                    int height = originBitmap.getHeight();
                    int centerX = width / 2;
                    Log.d(TAG, "centerX = " + centerX);
                    int centerY = height / 2;
                    Log.d(TAG, "centerY = " + centerY);
                    int radius = Math.min(width, height) / 2;
                    Log.d(TAG, "radius = " + radius);

                    int dripX = -centerX + radius;
                    int dripY = -centerY + radius;


                    //------------------------handle captchaImageViewMask begin------------------------

//                    Bitmap circularBitmap = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888);
                    Bitmap circularBitmap = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888);

                    Canvas canvas = new Canvas(circularBitmap);
                    Paint paint = new Paint();

                    paint.setAntiAlias(true);

                    canvas.drawCircle(radius, radius, radius / 2, paint);
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

                    canvas.drawBitmap(originBitmap, dripX, dripY, paint);

                    captchaImageViewMask.setImageBitmap(circularBitmap);
                    captchaImageViewMask.setRotation(randomDegree);

                    circleView.setmMinRadio(radius / 2);
                    //------------------------handle captchaImageViewMask end--------------------------


                }

            }
        });

    }

    private void initEvent() {

        rotationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Rotate the image based on the SeekBar's progress

                int rDegree = progress * 360 / 10000 + randomDegree;
                captchaImageViewMask.setRotation(rDegree);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something when the user starts interacting with the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do something when the user stops interacting with the SeekBar
                int progress = seekBar.getProgress();
                int rDegree = progress * 360 / 10000 + randomDegree;
                int gap = 360 - rDegree;
                if (Math.abs(gap) < mMatchDeviation) {
                    if (null != callback) {
                        callback.onPass(RotateCaptchaViewGroup.this);
                    }
                } else {
                    if (null != callback) {
                        callback.onUnPass(RotateCaptchaViewGroup.this);
                    }
                }
            }
        });
    }

    public void setImageResAndCallback2Show(@DrawableRes int drawableRes, RotateCaptchaCallback callback) {

        this.callback = callback;
        captchaImageView.setImageDrawable(context.getDrawable(drawableRes));

        restart();
    }

    public void setImageBitmapAndCallback2Show(Bitmap bitmap, RotateCaptchaCallback callback) {
        this.callback = callback;
        captchaImageView.setImageBitmap(bitmap);

        restart();
    }


    public void restart() {

        rotationSeekBar.setProgress(0);
        initData();
        initEvent();
    }


    public interface RotateCaptchaCallback {

        void onPass(RotateCaptchaViewGroup rotateCaptchaViewGroup);
        void onUnPass(RotateCaptchaViewGroup rotateCaptchaViewGroup);
    }


}
