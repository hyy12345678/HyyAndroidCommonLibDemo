package com.hyy.android.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.hyy.android.common.R;
import com.hyy.android.common.adapter.AdPagerAdapter;
import com.hyy.android.common.component.AutoScrollViewPager;
import com.hyy.android.common.listener.DefaultOnAdTouchListener;
import com.hyy.android.common.listener.OnAdPageChangeListener;
import com.hyy.android.common.listener.OnAdTouchListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neusoft on 15/10/23.
 * 目前通过xml方式最多可以设置5张AD图片
 */
public class AdViewLayout extends RelativeLayout {

    private Context context;
    private LayoutInflater inflater;
    private View headerView;

    private AutoScrollViewPager viewPagerAutoScroll;
    private ViewGroup linearLayoutIndicator;

    private ImageView[] indicators;
    private List<View> adViewList;

    private AdPagerAdapter adPagerAdapter;

    private OnAdTouchListener onAdTouchListener;
    private OnAdPageChangeListener onAdPageChangeListener;

    private Drawable ad01Drawable;
    private Drawable ad02Drawable;
    private Drawable ad03Drawable;
    private Drawable ad04Drawable;
    private Drawable ad05Drawable;

    private Drawable indicatorSelectedDrawable;
    private Drawable indicatorUnSelectedDrawable;

    public AdViewLayout(Context context) {
        super(context);
        this.context = context;
        init(null, 0);
    }

    public AdViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs, 0);
    }

    public AdViewLayout(Context context, AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs, defStyleAttr);
    }

    /***
     * 初始化xml解析参数
     * @param attrs
     * @param defStyle
     */
    private void init(AttributeSet attrs, int defStyle) {

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.AdViewLayout, defStyle, 0);

        //init ad01
        if (a.hasValue(R.styleable.AdViewLayout_ads01Drawable)) {
            ad01Drawable = a.getDrawable(
                    R.styleable.AdViewLayout_ads01Drawable);

        }

        //init ad02
        if (a.hasValue(R.styleable.AdViewLayout_ads02Drawable)) {
            ad02Drawable = a.getDrawable(
                    R.styleable.AdViewLayout_ads02Drawable);

        }

        //init ad03
        if (a.hasValue(R.styleable.AdViewLayout_ads03Drawable)) {
            ad03Drawable = a.getDrawable(
                    R.styleable.AdViewLayout_ads03Drawable);

        }

        //init ad04
        if (a.hasValue(R.styleable.AdViewLayout_ads04Drawable)) {
            ad04Drawable = a.getDrawable(
                    R.styleable.AdViewLayout_ads04Drawable);

        }

        //init ad05
        if (a.hasValue(R.styleable.AdViewLayout_ads05Drawable)) {
            ad05Drawable = a.getDrawable(
                    R.styleable.AdViewLayout_ads05Drawable);

        }

        //init indicator selected drawable
        if (a.hasValue(R.styleable.AdViewLayout_indicatorSelectedDrawable)) {
            indicatorSelectedDrawable = a.getDrawable(R.styleable.AdViewLayout_indicatorSelectedDrawable);
        } else {
            indicatorSelectedDrawable = getResources().getDrawable(R.drawable.indicator_selected);
        }

        //init indicator unselected drawable
        if (a.hasValue(R.styleable.AdViewLayout_indicatorUnSelectedDrawable)) {
            indicatorUnSelectedDrawable = a.getDrawable(R.styleable.AdViewLayout_indicatorUnSelectedDrawable);
        } else {
            indicatorUnSelectedDrawable = getResources().getDrawable(R.drawable.indicator_unselected);
        }


        a.recycle();

        initView();
        initData();
        initEvent();
    }


    private void initView() {

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        headerView = inflater.inflate(R.layout.hyy_commlib_view_ad_header, null);

        viewPagerAutoScroll = (AutoScrollViewPager) headerView
                .findViewById(R.id.viewPagerAutoScroll);
        linearLayoutIndicator = (ViewGroup) headerView
                .findViewById(R.id.linearLayoutIndicator);

        this.addView(headerView);

    }

    private void initData() {

        //init ads local
        adViewList = new ArrayList<View>();

        invalidateAds();

    }

    private void initEvent() {


    }

    private void initAds() {

        //防止重复加载
        if(indicators == null){
            indicators = new ImageView[adViewList.size()];
            ImageView imageView;

            linearLayoutIndicator.removeAllViews();

            for (int i = 0; i < adViewList.size(); i++) {
                imageView = new ImageView(context);
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setMargins(10, 0, 0, 0);
                imageView.setLayoutParams(layout);
                indicators[i] = imageView;
                if (i == 0) {
                    indicators[i].setBackgroundDrawable(indicatorSelectedDrawable);

                } else {
                    indicators[i].setBackgroundDrawable(indicatorUnSelectedDrawable);
                }

                linearLayoutIndicator.addView(imageView);
            }
        }


        adPagerAdapter = new AdPagerAdapter(adViewList);

        //如果之前有绑定监听，先去除
        if(null != onAdPageChangeListener){
            viewPagerAutoScroll.removeOnPageChangeListener(onAdPageChangeListener);
        }

        onAdPageChangeListener = new OnAdPageChangeListener(indicators,
                adViewList, indicatorSelectedDrawable, indicatorUnSelectedDrawable);
        onAdTouchListener = new DefaultOnAdTouchListener(viewPagerAutoScroll);

        viewPagerAutoScroll.setAdapter(adPagerAdapter);
        viewPagerAutoScroll.addOnPageChangeListener(onAdPageChangeListener);
        viewPagerAutoScroll.setOnTouchListener(onAdTouchListener);

        viewPagerAutoScroll.setCycle(true);
        viewPagerAutoScroll.setInterval(2 * 1000);
        viewPagerAutoScroll
                .setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_CYCLE);
        viewPagerAutoScroll.setCurrentItem(Integer.MAX_VALUE / 2
                - Integer.MAX_VALUE / 2 % adViewList.size());

        viewPagerAutoScroll.startAutoScroll();
    }


    /**
     * invalidateAds for xml configuration
     */
    private void invalidateAds() {
        adViewList.clear();

        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        if (ad01Drawable != null) {

            ImageView imageAd1 = new ImageView(context);
            imageAd1.setLayoutParams(layout);
            imageAd1.setScaleType(ImageView.ScaleType.FIT_XY);
            imageAd1.setImageDrawable(ad01Drawable);
            adViewList.add(imageAd1);
        }


        if (ad02Drawable != null) {
            ImageView imageAd2 = new ImageView(context);
            imageAd2.setLayoutParams(layout);
            imageAd2.setScaleType(ImageView.ScaleType.FIT_XY);
            imageAd2.setImageDrawable(ad02Drawable);
            adViewList.add(imageAd2);
        }


        if (ad03Drawable != null) {
            ImageView imageAd3 = new ImageView(context);
            imageAd3.setLayoutParams(layout);
            imageAd3.setScaleType(ImageView.ScaleType.FIT_XY);
            imageAd3.setImageDrawable(ad03Drawable);
            adViewList.add(imageAd3);
        }

        if (ad04Drawable != null) {
            ImageView imageAd4 = new ImageView(context);
            imageAd4.setLayoutParams(layout);
            imageAd4.setScaleType(ImageView.ScaleType.FIT_XY);
            imageAd4.setImageDrawable(ad04Drawable);
            adViewList.add(imageAd4);
        }


        if (ad05Drawable != null) {
            ImageView imageAd5 = new ImageView(context);
            imageAd5.setLayoutParams(layout);
            imageAd5.setScaleType(ImageView.ScaleType.FIT_XY);
            imageAd5.setImageDrawable(ad05Drawable);
            adViewList.add(imageAd5);
        }

        if (!adViewList.isEmpty()) {
            indicators = null;
            initAds();
        }

    }


    /***
     * 设置Drawable list for Ads (Drawable 格式)
     * 注意，使用这个方法可以添加超过5张AD，
     * 注意，如果同时也在xml中定义了AD的话，这里的设置会覆盖xml中的配置
     *
     * @param adsDrawableList
     */
    public void setAdsDrawableList(List<Drawable> adsDrawableList) {

        adViewList.clear();

        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        for (Drawable d : adsDrawableList) {
            ImageView tempIv = new ImageView(context);
            tempIv.setLayoutParams(layout);
            tempIv.setScaleType(ImageView.ScaleType.FIT_XY);
            tempIv.setImageDrawable(d);
            adViewList.add(tempIv);
        }

        if (!adViewList.isEmpty()) {
            indicators = null;
            initAds();
        }

    }

    /***
     * 设置ImageView list for Ads (ImageView 格式)
     * 注意，使用这个方法可以添加超过5张AD，
     * 注意，如果同时也在xml中定义了AD的话，这里的设置会覆盖xml中的配置
     * @param adsImageViewList
     */
    public void setAdsImageViewList(List<ImageView> adsImageViewList){

        adViewList.clear();

        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        for(ImageView imageView :adsImageViewList){
            imageView.setLayoutParams(layout);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            adViewList.add(imageView);
        }

        if(!adViewList.isEmpty()){
            indicators = null;
            initAds();
        }


    }


    /***
     * 设置自行扩展的OnAdTouchListener
     *
     * @param adTouchListener
     */
    public void setOnAdTouchListener(OnAdTouchListener adTouchListener) {
        this.onAdTouchListener = adTouchListener;
        viewPagerAutoScroll.setOnTouchListener(onAdTouchListener);
    }

    /***
     * 获取ViewPagerAutoScroll
     *
     * @return autoScrollViewPager
     */
    public AutoScrollViewPager getViewPagerAutoScroll() {
        return this.viewPagerAutoScroll;
    }


}
