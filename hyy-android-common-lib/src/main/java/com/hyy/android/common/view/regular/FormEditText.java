package com.hyy.android.common.view.regular;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by hyy on 2018/3/11.
 */

public class FormEditText extends LinearLayout {

    Context context;
    private LayoutInflater inflater;

    public FormEditText(Context context) {
        super(context);
        this.context = context;
        init(null, 0);
    }

    public FormEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs, 0);
    }

    public FormEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs, defStyleAttr);
    }


    private void init(AttributeSet attrs, int defStyle){

        //TODO Load attributes



        initView();
        initData();
        initEvent();
    }



    private void initView() {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private void initData() {

    }

    private void initEvent() {

    }

}
