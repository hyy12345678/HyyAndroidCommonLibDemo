package hyy.com.demo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hyy.com.dropdownlistviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AutoExtVGFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutoExtVGFragment extends Fragment {



    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment AutoExtVGFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AutoExtVGFragment newInstance() {
        AutoExtVGFragment fragment = new AutoExtVGFragment();

        return fragment;
    }

    public AutoExtVGFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auto_ext_vg, container, false);
    }



}
