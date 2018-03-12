package hyy.com.demo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyy.android.common.component.MultiSpinner;
import com.hyy.android.common.data.SimpleSpinnerOption;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import hyy.com.dropdownlistviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegularFragment extends Fragment {

    private View mView;

    @Bind(R.id.ms_test)
    MultiSpinner ms_test;


    public RegularFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance(){
        return new RegularFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_regular, container, false);
            ButterKnife.bind(this,mView);

            init();
        }

        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        return mView;

    }

    private void init() {

        ms_test.setTitle("月份选择");
        ArrayList multiSpinnerList=new ArrayList();
        for(int i=0;i<12;i++){
            SimpleSpinnerOption option=new SimpleSpinnerOption();
            option.setName((i+1)+" 月");
            option.setValue(i+1);
            multiSpinnerList.add(option);
        }
        ms_test.setDataList(multiSpinnerList);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
