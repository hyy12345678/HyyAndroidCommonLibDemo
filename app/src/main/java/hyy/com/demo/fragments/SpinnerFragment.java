package hyy.com.demo.fragments;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.hyy.android.common.view.spinner.SpinnerItem;
import com.hyy.android.common.view.spinner.adapter.MyHintSpinnerArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hyy.com.demo.data.PepoleStateCodeMapHelper;
import hyy.com.dropdownlistviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpinnerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpinnerFragment extends Fragment {

    public Spinner spinner_pepoleState;
    private ArrayAdapter<SpinnerItem> pepoleStateAdapter;
    private List<SpinnerItem> pepoleStateList;
    public SpinnerItem pepoleStateChosen;

    List<String> list;

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment AutoExtVGFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SpinnerFragment newInstance() {
        SpinnerFragment fragment = new SpinnerFragment();

        return fragment;
    }

    public SpinnerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_spinner, container, false);
        spinner_pepoleState = (Spinner) view.findViewById(R.id.spinner_pepoleState);

        pepoleStateList = getPepoleStateList();
        pepoleStateAdapter = new MyHintSpinnerArrayAdapter<SpinnerItem>(getActivity(), pepoleStateList);
        pepoleStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pepoleState.setAdapter(pepoleStateAdapter);
        spinner_pepoleState.setSelection(pepoleStateList.size() - 1);  //手工定位到hint
        pepoleStateChosen = pepoleStateList.get(pepoleStateList.size() - 1); //初始化指定选择的为hint


        spinner_pepoleState.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(spinner_pepoleState.getWindowToken(), 0);

                return false;
            }
        });

        //spinner_pepoleState spinner click
        spinner_pepoleState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                pepoleStateChosen = pepoleStateList.get(position);
                Toast.makeText(getContext(),pepoleStateChosen.toString(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public List<SpinnerItem> getPepoleStateList() {
        List<SpinnerItem> result = new ArrayList<>();

        for (Map.Entry<String, String> entry : PepoleStateCodeMapHelper.getInnerMap().entrySet()) {
            SpinnerItem spinnerItem = new SpinnerItem(entry.getKey(), entry.getValue(), false);
            result.add(spinnerItem);
        }

        result.add(new SpinnerItem(SpinnerItem.HINT_KEY, SpinnerItem.HINT_VALUE, true));
        return result;
    }
}
