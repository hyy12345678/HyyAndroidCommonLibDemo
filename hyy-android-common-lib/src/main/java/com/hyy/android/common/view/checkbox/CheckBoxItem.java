package com.hyy.android.common.view.checkbox;



/**
 * Created by huangyy on 2018/5/17.
 */

public class CheckBoxItem {

    private String dis;
    private int index;

    public CheckBoxItem(String dis,int index){
        this.dis = dis;
        this.index = index;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
