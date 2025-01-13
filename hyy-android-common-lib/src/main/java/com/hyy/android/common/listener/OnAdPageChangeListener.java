package com.hyy.android.common.listener;

import android.graphics.drawable.Drawable;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;

import java.util.List;


public class OnAdPageChangeListener implements OnPageChangeListener {

    private ImageView[] indicators;
    private List<View> adViewList;
    private Drawable indicatorSelectedDrawable;
    private Drawable indicatorUnSelectedDrawable;

    public OnAdPageChangeListener(ImageView[] indicators, List<View> adViewList, Drawable indicatorSelectedDrawable, Drawable indicatorUnSelectedDrawable) {
        this.indicators = indicators;
        this.adViewList = adViewList;
        this.indicatorSelectedDrawable = indicatorSelectedDrawable;
        this.indicatorUnSelectedDrawable = indicatorUnSelectedDrawable;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        if (arg0 >= 2) {
            arg0 = arg0 % adViewList.size();
        }

        for (int i = 0; i < indicators.length; i++) {
            indicators[arg0].setBackgroundDrawable(indicatorSelectedDrawable);


            if (arg0 != i) {
                indicators[i].setBackgroundDrawable(indicatorUnSelectedDrawable);
            }
        }

    }

}
