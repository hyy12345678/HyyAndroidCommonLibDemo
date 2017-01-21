package hyy.com.demo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hyy.android.common.dialog.DatePickDialog;
import com.hyy.android.common.view.HyyDatePicker;

import java.util.Calendar;

import hyy.com.dropdownlistviewdemo.R;


public class HyyDatePickerFragment extends Fragment {


    HyyDatePicker datePickerStart;
    Button btn_show;
    Button btn_dialog;


    int startYear;
    int startMonth;
    int startDay;

    public static Fragment getInstance() {
        return new HyyDatePickerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_hyy_date_picker, container, false);

        datePickerStart = (HyyDatePicker) view.findViewById(R.id.date_picker);
        btn_show = (Button) view.findViewById(R.id.btn_show);
        btn_dialog = (Button) view.findViewById(R.id.btn_dialog);


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerStart.init(year, month, day, new HyyDatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(HyyDatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                startYear = datePickerStart.getYear();
                startMonth = datePickerStart.getMonth();
                startDay = datePickerStart.getDayOfMonth();


            }
        });


        init();

        btn_show.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                startYear = datePickerStart.getYear();
                startMonth = datePickerStart.getMonth();
                startDay = datePickerStart.getDayOfMonth();

                Toast.makeText(
                        getActivity(),
                        "选择了 " + startYear + " 年 " + (startMonth + 1) + " 月 "
                                + startDay + " 日", Toast.LENGTH_SHORT).show();

            }
        });



        btn_dialog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

//                final int year = 2013;
//                final int month = 7;
//                final int day = 23;

                DatePickDialog.Builder builder = new DatePickDialog.Builder(getActivity());
                builder.setInitDate(year, month, day);
                builder.setYMDDisplay(true,true,false);
                builder.setTitle("提示");
                builder.setPositiveButton("确定",
                        new DatePickDialog.OnDatePickDialogClickListener() {
                            @Override
                            public void onClick(int YEAR, int MONTH, int DAY ,int which) {
                                Toast.makeText(getActivity(),"POSITIVE seleced date:"+YEAR+(MONTH+1)+DAY,Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.setNegativeButton("取消",
                        new DatePickDialog.OnDatePickDialogClickListener() {
                            @Override
                            public void onClick(int YEAR, int MONTH, int DAY ,int which) {
                                Toast.makeText(getActivity(),"NEGATIVE seleced date:"+YEAR+(MONTH+1)+DAY,Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.create().show();

            }
        });

        return view;
    }


    private void init() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerStart.init(year, month, day, new HyyDatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(HyyDatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                startYear = datePickerStart.getYear();
                startMonth = datePickerStart.getMonth();
                startDay = datePickerStart.getDayOfMonth();


            }
        });

    }
}
