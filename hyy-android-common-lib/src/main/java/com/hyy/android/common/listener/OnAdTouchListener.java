package com.hyy.android.common.listener;


import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.hyy.android.common.component.AutoScrollViewPager;


public abstract class OnAdTouchListener implements OnTouchListener {

    private AutoScrollViewPager autoScrollViewPager;

    private float DownX;
    private float DownY;
    private float MoveX;
    private float MoveY;
    private static final float ZERO_MOVE = 5.0f;


    public OnAdTouchListener(AutoScrollViewPager autoScrollViewPager) {
        this.autoScrollViewPager = autoScrollViewPager;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (autoScrollViewPager.equals(v)) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_MOVE:

                    MoveX = event.getX() - DownX;
                    MoveY = event.getY() - DownY;

                    break;

                case MotionEvent.ACTION_DOWN:

                    DownX = event.getX();
                    DownY = event.getY();

                    autoScrollViewPager.stopAutoScroll();

                    break;
                case MotionEvent.ACTION_UP:
                    autoScrollViewPager.startAutoScroll();

                    //判断是否滑动ZERO_MOVE距离
                    if(Math.abs(MoveX) > ZERO_MOVE || Math.abs(MoveY) > ZERO_MOVE){
                        //滑动不触发AD点击响应
                        //TODO add move response
                    } else {
                        //非滑动触发AD点击响应
                        //TODO add no move response
                        onAdTouchUpProcess(autoScrollViewPager.getCurrentItem());
                    }


                    break;
            }
        }

        return false;
    }


    /**
     * AD抬起触发
     *
     * @param index AD序号，从0开始
     */
    protected abstract void onAdTouchUpProcess(int index);
}
