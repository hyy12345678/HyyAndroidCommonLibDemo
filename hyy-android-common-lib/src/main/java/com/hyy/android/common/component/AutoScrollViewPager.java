package com.hyy.android.common.component;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import androidx.core.view.MotionEventCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * Created by hyy on 2016/7/24.
 */
public class AutoScrollViewPager extends ViewPager {
    public static final int DEFAULT_INTERVAL = 1500;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int SLIDE_BORDER_MODE_NONE = 0;
    public static final int SLIDE_BORDER_MODE_CYCLE = 1;
    public static final int SLIDE_BORDER_MODE_TO_PARENT = 2;
    private long interval = 1500L;
    private int direction = 1;
    private boolean isCycle = true;
    private boolean stopScrollWhenTouch = true;
    private int slideBorderMode = 0;
    private boolean isBorderAnimation = true;
    private double autoScrollFactor = 1.0D;
    private double swipeScrollFactor = 1.0D;
    private Handler handler;
    private boolean isAutoScroll = false;
    private boolean isStopByTouch = false;
    private float touchX = 0.0F;
    private float downX = 0.0F;
    private CustomDurationScroller scroller = null;
    public static final int SCROLL_WHAT = 0;
    private static final float ROUTE = 10.0F;

    public AutoScrollViewPager(Context paramContext) {
        super(paramContext);
        this.init();
    }

    public AutoScrollViewPager(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        this.init();
    }

    private void init() {
        this.handler = new AutoScrollViewPager.MyHandler(this);
        this.setViewPagerScroller();
    }

    public void startAutoScroll() {
        this.isAutoScroll = true;
        this.sendScrollMessage((long)((double)this.interval + (double)this.scroller.getDuration() / this.autoScrollFactor * this.swipeScrollFactor));
    }

    public void startAutoScroll(int delayTimeInMills) {
        this.isAutoScroll = true;
        this.sendScrollMessage((long)delayTimeInMills);
    }

    public void stopAutoScroll() {
        this.isAutoScroll = false;
        this.handler.removeMessages(0);
    }

    public void setSwipeScrollDurationFactor(double scrollFactor) {
        this.swipeScrollFactor = scrollFactor;
    }

    public void setAutoScrollDurationFactor(double scrollFactor) {
        this.autoScrollFactor = scrollFactor;
    }

    private void sendScrollMessage(long delayTimeInMills) {
        this.handler.removeMessages(0);
        this.handler.sendEmptyMessageDelayed(0, delayTimeInMills);
    }

    private void setViewPagerScroller() {
        try {
            Field e = ViewPager.class.getDeclaredField("mScroller");
            e.setAccessible(true);
            Field interpolatorField = ViewPager.class.getDeclaredField("sInterpolator");
            interpolatorField.setAccessible(true);
            this.scroller = new CustomDurationScroller(this.getContext(), (Interpolator)interpolatorField.get((Object)null));
            e.set(this, this.scroller);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void scrollOnce() {
        PagerAdapter adapter = this.getAdapter();
        int currentItem = this.getCurrentItem();
        int totalCount;
        if(adapter != null && (totalCount = adapter.getCount()) > 1) {
            int var10000;
            if(this.direction == 0) {
                --currentItem;
                var10000 = currentItem;
            } else {
                ++currentItem;
                var10000 = currentItem;
            }

            int nextItem = var10000;
            if(nextItem < 0) {
                if(this.isCycle) {
                    this.setCurrentItem(totalCount - 1, this.isBorderAnimation);
                }
            } else if(nextItem == totalCount) {
                if(this.isCycle) {
                    this.setCurrentItem(0, this.isBorderAnimation);
                }
            } else {
                this.setCurrentItem(nextItem, true);
            }

        }
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        if(this.stopScrollWhenTouch) {
            if(action == 0 && this.isAutoScroll) {
                this.isStopByTouch = true;
                this.stopAutoScroll();
            } else if(ev.getAction() == 1 && this.isStopByTouch) {
                this.startAutoScroll();
            }
        }

        if(this.slideBorderMode == 2 || this.slideBorderMode == 1) {
            this.touchX = ev.getX();
            if(ev.getAction() == 0) {
                this.downX = this.touchX;
            }

            int currentItem = this.getCurrentItem();
            PagerAdapter adapter = this.getAdapter();
            int pageCount = adapter == null?0:adapter.getCount();
            if(this.downX <= this.touchX - 10.0F) {
                if(currentItem == 0) {
                    if(this.slideBorderMode == 2) {
                        this.getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        if(pageCount > 1) {
                            this.setCurrentItem(pageCount - currentItem - 1, this.isBorderAnimation);
                        }

                        this.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                }

                return super.dispatchTouchEvent(ev);
            }

            if(this.downX - 10.0F >= this.touchX) {
                if(currentItem == pageCount - 1) {
                    if(this.slideBorderMode == 2) {
                        this.getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        if(pageCount > 1) {
                            this.setCurrentItem(pageCount - currentItem - 1, this.isBorderAnimation);
                        }

                        this.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                }

                return super.dispatchTouchEvent(ev);
            }

            this.getParent().requestDisallowInterceptTouchEvent(false);
        }

        return super.dispatchTouchEvent(ev);
    }

    public long getInterval() {
        return this.interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public int getDirection() {
        return this.direction == 0?0:1;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isCycle() {
        return this.isCycle;
    }

    public void setCycle(boolean isCycle) {
        this.isCycle = isCycle;
    }

    public boolean isStopScrollWhenTouch() {
        return this.stopScrollWhenTouch;
    }

    public void setStopScrollWhenTouch(boolean stopScrollWhenTouch) {
        this.stopScrollWhenTouch = stopScrollWhenTouch;
    }

    public int getSlideBorderMode() {
        return this.slideBorderMode;
    }

    public void setSlideBorderMode(int slideBorderMode) {
        this.slideBorderMode = slideBorderMode;
    }

    public boolean isBorderAnimation() {
        return this.isBorderAnimation;
    }

    public void setBorderAnimation(boolean isBorderAnimation) {
        this.isBorderAnimation = isBorderAnimation;
    }

    private static class MyHandler extends Handler {
        private final WeakReference<AutoScrollViewPager> autoScrollViewPager;

        public MyHandler(AutoScrollViewPager autoScrollViewPager) {
            this.autoScrollViewPager = new WeakReference(autoScrollViewPager);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what) {
                case 0:
                    AutoScrollViewPager pager = (AutoScrollViewPager)this.autoScrollViewPager.get();
                    if(pager != null) {
                        pager.scroller.setScrollDurationFactor(pager.autoScrollFactor);
                        pager.scrollOnce();
                        pager.scroller.setScrollDurationFactor(pager.swipeScrollFactor);
                        pager.sendScrollMessage(pager.interval + (long)pager.scroller.getDuration());
                    }
                default:
            }
        }
    }
}
