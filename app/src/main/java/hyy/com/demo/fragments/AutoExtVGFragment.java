package hyy.com.demo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hyy.android.common.view.checkbox.CheckBoxSingleGroup;

import java.util.ArrayList;
import java.util.List;

import hyy.com.dropdownlistviewdemo.R;

import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AutoExtVGFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutoExtVGFragment extends Fragment {

//    AutoExtViewGroup aevg;
    CheckBoxSingleGroup cbsg;

    List<String> list;

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
        View view = inflater.inflate(R.layout.fragment_auto_ext_vg, container, false);

        cbsg =  (CheckBoxSingleGroup) view.findViewById(R.id.cbsg_test);


        list = new ArrayList<>();
        list.add("一");
        list.add("一个");
        list.add("一个小");
        list.add("一个小姑娘");
        list.add("一个小姑娘在");
        list.add("一个小姑娘在卖");
        list.add("一个小姑娘在卖火");
        list.add("一个小姑娘在卖火柴");

        cbsg.setList(list);
        cbsg.setOnItemSelectedListener(new CheckBoxSingleGroup.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position, boolean isChecked) {
                Toast.makeText(getContext(),""+position+","+isChecked,Toast.LENGTH_SHORT).show();
            }
        });




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
