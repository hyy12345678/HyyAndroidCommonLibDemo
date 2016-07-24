package com.hyy.android.common.listener;


import com.hyy.android.common.component.AutoScrollViewPager;

/**
 * Created by neusoft on 15/11/3.
 */
public class DefaultOnAdTouchListener extends OnAdTouchListener {

    public DefaultOnAdTouchListener(AutoScrollViewPager autoScrollViewPager) {
        super(autoScrollViewPager);
    }


    @Override
    public void onAdTouchUpProcess(int index) {
        //Toast.makeText(MyApplication.getInstance(),"AD:"+index,Toast.LENGTH_SHORT).show();
    }
}
