package com.hyy.android.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.hyy.android.common.R;

import java.util.ArrayList;
import java.util.List;


public class HyyDatePicker extends RelativeLayout {

    private Context context;
    private LayoutInflater inflater;

    private View innerView;
    private RelativeLayout innerLayout;

    // 年选择器
    PickerView year_pv;
    // 月选择器
    PickerView month_pv;
    // 日选择器
    PickerView day_pv;

    int selectedYear;
    int selectedMonth; // 0~11
    int selectedDay;

    OnDateChangedListener dateChangeLister;

    Drawable bgColor;

    boolean useYear;
    boolean useMonth;
    boolean useDay;


    private int SELECT_FROM_YEAR = 1970;
    private int SELECT_TO_YEAR = 2100;

    public HyyDatePicker(Context context) {
        super(context);
        this.context = context;
        init(null, 0);
    }

    public HyyDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs, 0);
    }

    public HyyDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor
        init(attrs, defStyleAttr);

    }

    private void init(AttributeSet attrs, int defStyle) {
        // TODO Auto-generated method stub
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.hyy_commlib_hyyDatePicker, defStyle, 0);

        // init bgColor
        if (a.hasValue(R.styleable.hyy_commlib_hyyDatePicker_hyy_commlib_dpBgColor)) {
            bgColor = a.getDrawable(R.styleable.hyy_commlib_hyyDatePicker_hyy_commlib_dpBgColor);
        }

        // init useYear
        if (a.hasValue(R.styleable.hyy_commlib_hyyDatePicker_hyy_commlib_useYear)) {
            useYear = a.getBoolean(R.styleable.hyy_commlib_hyyDatePicker_hyy_commlib_useYear, true);
        }

        // init useMonth
        if (a.hasValue(R.styleable.hyy_commlib_hyyDatePicker_hyy_commlib_useMonth)) {
            useMonth = a.getBoolean(R.styleable.hyy_commlib_hyyDatePicker_hyy_commlib_useMonth, true);
        }

        // init useDay
        if (a.hasValue(R.styleable.hyy_commlib_hyyDatePicker_hyy_commlib_useDay)) {
            useDay = a.getBoolean(R.styleable.hyy_commlib_hyyDatePicker_hyy_commlib_useDay, true);
        }


        initView();
        initData();
        initEvent();
    }

    @SuppressLint("NewApi")
    private void initView() {
        // TODO Auto-generated method stub
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        innerView = inflater.inflate(R.layout.hyy_commlib_date_picker, null);
        innerLayout = (RelativeLayout) innerView
                .findViewById(R.id.rLayout_date_picker);

        innerLayout.setBackground(bgColor);

        year_pv = (PickerView) innerView.findViewById(R.id.year_pv);
        month_pv = (PickerView) innerView.findViewById(R.id.month_pv);
        day_pv = (PickerView) innerView.findViewById(R.id.day_pv);

        refreshYMD();

        this.addView(innerView);
    }

    private void initData() {
        // TODO Auto-generated method stub
        List<String> years = new ArrayList<String>();
        List<String> months = new ArrayList<String>();
        List<String> days = new ArrayList<String>();

        for (int i = SELECT_FROM_YEAR; i < SELECT_TO_YEAR; i++) {
            years.add(String.valueOf(i));
        }
        for (int i = 1; i < 13; i++) {
            months.add(i < 10 ? "0" + i : "" + i);
        }
        for (int i = 1; i < 32; i++) {
            days.add(i < 10 ? "0" + i : "" + i);
        }

        year_pv.setData(years);
        month_pv.setData(months);
        day_pv.setData(days);

    }

    private void initEvent() {
        // TODO Auto-generated method stub

        year_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {

                selectedYear = Integer.valueOf(text);

                adjustDay(selectedYear, selectedMonth);
                if (dateChangeLister != null) {
                    dateChangeLister.onDateChanged(HyyDatePicker.this,
                            getYear(), getMonth(), getDayOfMonth());
                }

            }

        });

        month_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {

                selectedMonth = Integer.valueOf(text) - 1;

                adjustDay(selectedYear, selectedMonth);
                if (dateChangeLister != null) {
                    dateChangeLister.onDateChanged(HyyDatePicker.this,
                            getYear(), getMonth(), getDayOfMonth());
                }
            }
        });

        day_pv.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {

                selectedDay = Integer.valueOf(text) - 1;
                if (dateChangeLister != null) {
                    dateChangeLister.onDateChanged(HyyDatePicker.this,
                            getYear(), getMonth(), getDayOfMonth());
                }
            }
        });
    }

    /**
     * 刷新YEAR，MONTH，DAY项目显示控制
     */
    public void refreshYMD() {

        if (!useYear) {
            year_pv.setVisibility(GONE);
        }

        if (!useMonth) {
            month_pv.setVisibility(GONE);
        }

        if (!useDay) {
            day_pv.setVisibility(GONE);
        }

    }


    /***
     * 日根据年月进行联动
     *
     * @param year
     * @param month
     */
    private void adjustDay(int year, int month) {
        // TODO Auto-generated method stub

        int maxDay = 0;

        switch (month) {
            case 0:
                maxDay = 30;
                break;

            case 1:
                if (isLeapYear(Integer.valueOf(year))) {
                    maxDay = 28;
                } else {
                    maxDay = 27;
                }

                break;

            case 2:
                maxDay = 30;
                break;

            case 3:
                maxDay = 29;
                break;

            case 4:
                maxDay = 30;
                break;

            case 5:
                maxDay = 29;
                break;

            case 6:
                maxDay = 30;
                break;

            case 7:
                maxDay = 30;
                break;

            case 8:
                maxDay = 29;
                break;

            case 9:
                maxDay = 30;
                break;

            case 10:
                maxDay = 29;
                break;

            case 11:
                maxDay = 30;
                break;

            default:
                break;
        }

        // 刷新日列表
        List<String> days = new ArrayList<String>();
        for (int i = 1; i <= maxDay + 1; i++) {
            days.add(i < 10 ? "0" + i : "" + i);
        }
        day_pv.setData(days);

        if (maxDay < selectedDay) {
            selectedDay = maxDay;
        }

        day_pv.setSelected(selectedDay);

    }

    private static int gregorianCutoverYear = 1582;

    /**
     * 判断是否是闰年
     *
     * @param year
     * @return
     */
    private static boolean isLeapYear(int year) {
        return year >= gregorianCutoverYear ? ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)))
                : (year % 4 == 0);
    }

    /**
     * 获取当前选择的年
     *
     * @return
     */
    public int getYear() {
        return Integer.valueOf(selectedYear);
    }

    /**
     * 获取当前选择的月
     *
     * @return
     */
    public int getMonth() {
        return Integer.valueOf(selectedMonth);
    }

    /**
     * 获取当前选择的日
     *
     * @return
     */
    public int getDayOfMonth() {
        return Integer.valueOf(selectedDay) + 1;
    }

    /**
     * 获取当前是否显示YEAR
     *
     * @return
     */
    public boolean isUseYear() {
        return useYear;
    }

    /**
     * 设置当前是否显示YEAR
     *
     * @param useYear
     */
    public void setUseYear(boolean useYear) {
        this.useYear = useYear;
    }

    /**
     * 获取当前是否显示MONTH
     *
     * @return
     */
    public boolean isUseMonth() {
        return useMonth;
    }

    /**
     * 设置当前是否显示MONTH
     *
     * @param useMonth
     */
    public void setUseMonth(boolean useMonth) {
        this.useMonth = useMonth;
    }

    /**
     * 获取当前是否显示DAY
     *
     * @return
     */
    public boolean isUseDay() {
        return useDay;
    }

    /**
     * 设置当前是否显示DAY
     *
     * @param useDay
     */
    public void setUseDay(boolean useDay) {
        this.useDay = useDay;
    }

    /***
     * 初始化日期选择控件
     *
     * @param year     (1970~2100)
     * @param month    (0~11)
     * @param day
     * @param listener
     */
    public void init(int year, int month, int day,
                     OnDateChangedListener listener) {

        this.initData();

        this.dateChangeLister = listener;

        this.selectedYear = year;
        this.selectedMonth = month;
        this.selectedDay = day - 1;

        int yearGap = year - SELECT_FROM_YEAR;
        year_pv.setSelected(yearGap);
        month_pv.setSelected(month);

        adjustDay(selectedYear, selectedMonth);
    }

    /***
     * 初始化日期选择控件
     *
     * @param year     (SELECT_FROM_YEAR~SELECT_TO_YEAR)
     * @param month    (0~11)
     * @param day
     * @param select_from_year SELECT_FROM_YEAR
     * @param select_to_year SELECT_TO_YEAR
     * @param listener
     */
    public void init(int year, int month, int day,
                     int select_from_year,int select_to_year,
                     OnDateChangedListener listener) {

        this.SELECT_FROM_YEAR = select_from_year;
        this.SELECT_TO_YEAR = select_to_year;

        this.initData();

        this.dateChangeLister = listener;

        this.selectedYear = year;
        this.selectedMonth = month;
        this.selectedDay = day - 1;

        int yearGap = year - SELECT_FROM_YEAR;
        year_pv.setSelected(yearGap);
        month_pv.setSelected(month);

        adjustDay(selectedYear, selectedMonth);
    }


    /**
     * 年月日变更触发监听
     */
    public interface OnDateChangedListener {
        public void onDateChanged(HyyDatePicker view, int year,
                                  int monthOfYear, int dayOfMonth);
    }


}
