package hyy.com.demo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hyy.android.common.dialog.DatePickDialog;
import com.hyy.android.common.dialog.TimePickDialog;
import com.hyy.android.common.view.HyyDatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import hyy.com.dropdownlistviewdemo.R;


public class HyyDatePickerFragment extends Fragment {


    HyyDatePicker datePickerStart;
    Button btn_show;
    Button btn_date_dialog;
    Button btn_time_dialog;


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
        btn_date_dialog = (Button) view.findViewById(R.id.btn_date_dialog);
        btn_time_dialog = (Button) view.findViewById(R.id.btn_time_dialog);


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerStart.init(year, month, day, new HyyDatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(HyyDatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
//                startYear = datePickerStart.getYear();
//                startMonth = datePickerStart.getMonth();
//                startDay = datePickerStart.getDayOfMonth();

                startYear = year;
                startMonth = monthOfYear;
                startDay = dayOfMonth;

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


        btn_date_dialog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                String initStr = "2014-01-13";
                Calendar calendar = Calendar.getInstance();
                final DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

                try {

                    Date initDate = format1.parse(initStr);
                    calendar.setTime(initDate);

                } catch (ParseException e) {
                    e.printStackTrace();
                }


                DatePickDialog.Builder builder = new DatePickDialog.Builder(getActivity());
                builder.setTitleBg(ContextCompat.getDrawable(getActivity(), R.drawable.custom_dialog_title_bg));
                builder.setInitDate(calendar);
                builder.setYMDDisplay(true, true, true);
                builder.setTitle("提示");
                builder.setPositiveButton("确定",
                        new DatePickDialog.OnDatePickDialogClickListener() {
                            @Override
                            public void onClick(Calendar cale, int which) {

                                Date date =cale.getTime();
                                String dateStr=format1.format(date);

                                Toast.makeText(getActivity(), "POSITIVE seleced date:" + dateStr, Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.setNegativeButton("取消",
                        new DatePickDialog.OnDatePickDialogClickListener() {
                            @Override
                            public void onClick(Calendar cale, int which) {

                                Date date =cale.getTime();
                                String dateStr=format1.format(date);

                                Toast.makeText(getActivity(), "NEGATIVE seleced date:" + dateStr, Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.create().show();

            }
        });

        btn_time_dialog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                final DateFormat format2 = new SimpleDateFormat("hh:mm:ss");


                TimePickDialog.Builder builder = new TimePickDialog.Builder(getActivity());
                builder.setTitleBg(ContextCompat.getDrawable(getActivity(), R.drawable.custom_dialog_title_bg));
                builder.setInitDate(calendar);
                builder.setHMSDisplay(true, true, true);
                builder.setTitle("提示");

                builder.setPositiveButton("确定", new TimePickDialog.OnTimePickDialogClickListener() {
                    @Override
                    public void onClick(Calendar chooseCanlendar, int which) {

                        Date date =chooseCanlendar.getTime();
                        String dateStr=format2.format(date);

                        Toast.makeText(getActivity(), "POSITIVE seleced time:" + dateStr, Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("取消", new TimePickDialog.OnTimePickDialogClickListener() {
                    @Override
                    public void onClick(Calendar chooseCanlendar, int which) {
                        Date date =chooseCanlendar.getTime();
                        String dateStr=format2.format(date);

                        Toast.makeText(getActivity(), "NEGATIVE seleced time:" + dateStr, Toast.LENGTH_SHORT).show();
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
//                startYear = datePickerStart.getYear();
//                startMonth = datePickerStart.getMonth();
//                startDay = datePickerStart.getDayOfMonth();

                startYear = year;
                startMonth = monthOfYear;
                startDay = dayOfMonth;

            }
        });

    }
}
