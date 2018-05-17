package com.hyy.android.common.view.spinner;

/**
 * Created by hyy on 2016/6/15.
 */
public class SpinnerItem {

    public static final String HINT_KEY = "请选择";
    public static final String HINT_KEY_EMPTY = "";
    public static final String HINT_VALUE = "-1";

    private final String text;
    private final String value;
    private final boolean isHint;

    public SpinnerItem(String strItem, String value , boolean flag) {
        this.isHint = flag;
        this.text = strItem;
        this.value = value;
    }

    public String getItemString() {
        return text;
    }

    public boolean isHint() {
        return isHint;
    }

    public String getItemValue(){
        return value;
    }

    @Override
    public String toString() {
        return text;
    }
}