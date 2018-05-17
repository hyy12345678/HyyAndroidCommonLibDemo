package com.hyy.android.common.view.checkbox;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.hyy.android.common.R;
import com.hyy.android.common.view.AutoExtViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyy on 2018/5/17.
 */

public class CheckBoxSingleGroup extends LinearLayout {


    private Context context;
    private LayoutInflater inflater;
    private View innerView;

    private AutoExtViewGroup aevg_container;

    private List<CheckBoxItem> innerList = new ArrayList<>();
    private List<CheckBox> innerListView = new ArrayList<>();

    private OnItemSelectedListener innerListener;

    private int lastCheckedIX = -1;

    public CheckBoxSingleGroup(Context context) {
        super(context);
        this.context = context;
        init(null, 0);
    }

    public CheckBoxSingleGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs, 0);
    }

    public CheckBoxSingleGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {

        initView();
        initData();
        initEvent();
    }

    private void initView() {

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        innerView = inflater.inflate(R.layout.hyy_commlib_view_cbg_single, null);
        aevg_container = (AutoExtViewGroup) innerView.findViewById(R.id.aevg_container);

        addView(innerView);

    }

    private void initData() {



    }

    private void initEvent() {

    }

    private void setViews(){
        if(null!= aevg_container){
            aevg_container.removeAllViews();
        }

        innerListView.clear();
        for(CheckBoxItem cbi:innerList){
            CheckBox cb = new CheckBox(context);
            cb.setText(cbi.getDis());
            cb.setTag(R.id.tag_ix,cbi.getIndex());

            if(null!= innerListener){
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        boolean needReset = false;

                        int index = (int)((CheckBox)buttonView).getTag(R.id.tag_ix);

                        if(lastCheckedIX != -1 && lastCheckedIX !=index && !isChecked){
                            //not init value,and not selected value
                            //it is last unchecked situation
                            return;
                        }else{

                            if(lastCheckedIX == -1 || isChecked){
                                //init situation || new selected
                                lastCheckedIX =index;
                                needReset = true;
                            }else{
                                //checked unchecked situation
                                lastCheckedIX = -1;
                            }

                        }

                        innerListener.onItemSelected (index,isChecked);
                        if(needReset){
                            resetExceptIx(index);
                        }


                    }
                });
            }

            aevg_container.addView(cb);
            innerListView.add(cb);
        }
    }


    public void setList(List<String> list){
        innerList.clear();
        for(int i=0;i<list.size();i++){
            CheckBoxItem cbi = new CheckBoxItem(list.get(i),i);
            innerList.add(cbi);
        }

        setViews();
    }


    private void resetExceptIx(int ix){
        for(CheckBox cb:innerListView){
            int index = (int)cb.getTag(R.id.tag_ix);
            if(ix == index){
                continue;
            }else {
                cb.setChecked(false);
            }
        }
    }



    public void setOnItemSelectedListener(OnItemSelectedListener listener){
        this.innerListener = listener;
        if(!innerList.isEmpty()){
            setViews();
        }
    }

    public interface OnItemSelectedListener{

        void onItemSelected(int position,boolean isChecked);
    }


}
