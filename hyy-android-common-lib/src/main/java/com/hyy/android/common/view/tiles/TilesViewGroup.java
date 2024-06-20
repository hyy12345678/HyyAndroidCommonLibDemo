package com.hyy.android.common.view.tiles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class TilesViewGroup extends ViewGroup {

    private static String HYY_TAG = "TilesViewGroup";

    public TilesViewGroup(Context context) {
        super(context);
    }

    public TilesViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TilesViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int wholeWidth = r;

        int injectWidth = l;
//        int injectHeight = 0;

        Pivoet latestHigh = new Pivoet(l, t);
        Pivoet latestLow = new Pivoet(l, t);


        if (getChildCount() > 0) {

            for (int i = 0; i < getChildCount(); i++) {

                View child = getChildAt(i);

                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();


                if (injectWidth + childWidth <= wholeWidth
                        && latestHigh.getX() < injectWidth ) {
                    //不需要换行
                    if (childHeight + latestLow.getY() > latestHigh.getY()) {
                        //inject给High，Low变成原来的High
                        int tempX = latestHigh.getX();
                        int tempY = latestHigh.getY();
                        latestHigh.setY(childHeight + latestLow.getY());
                        latestHigh.setX(injectWidth);
                        latestLow.setX(tempX);
                        latestLow.setY(tempY);

                        child.layout(latestHigh.getX(), latestHigh.getY() - childHeight,
                                latestHigh.getX() + child.getMeasuredWidth(),
                                latestHigh.getY());

                    } else {
                        //inject给low，High不变
                        latestLow.setY(latestLow.getY() + childHeight);
                        latestLow.setX(injectWidth);

                        child.layout(latestLow.getX(), latestLow.getY() - childHeight,
                                latestLow.getX() + child.getMeasuredWidth(),
                                latestLow.getY());
                    }

                    injectWidth += childWidth;


                } else {
                    //需要换行
                    //重置injectWidth到low的X
                    if(latestHigh.getY() == latestLow.getY()){
                        injectWidth = (latestHigh.getX()-latestLow.getX()>0)?latestLow.getX():latestHigh.getX();
                    }else{
                        injectWidth = latestLow.getX();
                    }


                    //TODO 这里需要判别一下换行过去后，inject点能否放置下，即不会超过右边界
                    //TODO 这里又分几种情况
                    //TODO 情况1。injectWidth + child.width > wholeWidth 此时injectWidth重置为0
                    //TODO 这时需要高度从High开始插入，并且High，low要最后全归结到这个控件上
                    //TODO 情况2。injectWidth + child.width <= wholeWidth 正常插入，走原有逻辑
                    if (injectWidth + childWidth > wholeWidth
                            || childWidth == wholeWidth) {
                        //情况1
                        injectWidth = 0;
                        latestHigh.setX(injectWidth);
                        latestHigh.setY(latestHigh.getY() + childHeight);
                        latestLow.setX(latestHigh.getX());
                        latestLow.setY(latestHigh.getY());

                        child.layout(latestHigh.getX(), latestHigh.getY() - childHeight,
                                latestHigh.getX() + child.getMeasuredWidth(),
                                latestHigh.getY());

                    } else {
                        //情况2
                        if (childHeight + latestLow.getY() > latestHigh.getY()) {
                            //inject给High，Low变成原来的High
                            int tempX = latestHigh.getX();
                            int tempY = latestHigh.getY();
                            latestHigh.setY(childHeight + latestLow.getY());
                            latestHigh.setX(injectWidth);
                            latestLow.setX(tempX);
                            latestLow.setY(tempY);

                            child.layout(latestHigh.getX(), latestHigh.getY() - childHeight,
                                    latestHigh.getX() + child.getMeasuredWidth(),
                                    latestHigh.getY());

                        } else {
                            //inject给low，High不变
                            latestLow.setY(latestLow.getY() + childHeight);
                            latestLow.setX(injectWidth);

                            child.layout(latestLow.getX(), latestLow.getY() - childHeight,
                                    latestLow.getX() + child.getMeasuredWidth(),
                                    latestLow.getY());
                        }
                    }

                    injectWidth += childWidth;
                }
            }
        }


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int parentDesireWidth = 0;
        int parentDesireHeight = 0;


        int wholeWidth = MeasureSpec.getSize(widthMeasureSpec);

        int injectWidth = 0;
//        int injectHeight = 0;

        Pivoet latestHigh = new Pivoet(0, 0);
        Pivoet latestLow = new Pivoet(0, 0);


        if (getChildCount() > 0) {

            for (int i = 0; i < getChildCount(); i++) {

                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);

                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();


                if (injectWidth + childWidth <= wholeWidth
                    && latestHigh.getX() < injectWidth) {
                    //不需要换行
                    if (childHeight + latestLow.getY() > latestHigh.getY()) {
                        //inject给High，Low变成原来的High
                        int tempX = latestHigh.getX();
                        int tempY = latestHigh.getY();
                        latestHigh.setY(childHeight + latestLow.getY());
                        latestHigh.setX(injectWidth);
                        latestLow.setX(tempX);
                        latestLow.setY(tempY);
                    } else {
                        //inject给low，High不变
                        latestLow.setY(latestLow.getY() + childHeight);
                        latestLow.setX(injectWidth);
                    }

                    injectWidth += childWidth;


                } else {
                    //需要换行
                    //重置injectWidth到low的X
                    if(latestHigh.getY() == latestLow.getY()){
                        injectWidth = (latestHigh.getX()-latestLow.getX()>0)?latestLow.getX():latestHigh.getX();
                    }else{
                        injectWidth = latestLow.getX();
                    }

                    //TODO 这里需要判别一下换行过去后，inject点能否放置下，即不会超过右边界
                    //TODO 这里又分几种情况
                    //TODO 情况1。injectWidth + child.width > wholeWidth 此时injectWidth重置为0
                    //TODO 这时需要高度从High开始插入，并且High，low要最后全归结到这个控件上
                    //TODO 情况2。injectWidth + child.width <= wholeWidth 正常插入，走原有逻辑

                    if (injectWidth + childWidth > wholeWidth
                            || childWidth == wholeWidth) {
                        //情况1
                        injectWidth = 0;
                        latestHigh.setX(injectWidth);
                        latestHigh.setY(latestHigh.getY() + childHeight);
                        latestLow.setX(latestHigh.getX());
                        latestLow.setY(latestHigh.getY());

                    } else {
                        //情况2
                        if (childHeight + latestLow.getY() > latestHigh.getY()) {

                            //inject给High，Low变成原来的High
                            int tempX = latestHigh.getX();
                            int tempY = latestHigh.getY();
                            latestHigh.setY(childHeight + latestLow.getY());
                            latestHigh.setX(injectWidth);
                            latestLow.setX(tempX);
                            latestLow.setY(tempY);
                        } else {
                            //inject给low，High不变
                            latestLow.setY(latestLow.getY() + childHeight);
                            latestLow.setX(injectWidth);
                        }
                    }

                    injectWidth += childWidth;
                }
            }
        }

        parentDesireHeight = latestHigh.getY();
        parentDesireWidth = wholeWidth;

        // report this final dimension
        setMeasuredDimension(resolveSize(parentDesireWidth, widthMeasureSpec),
                resolveSize(parentDesireHeight, heightMeasureSpec));

    }


    private class Pivoet {
        private int x;
        private int y;

        public Pivoet(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }


    }

}
