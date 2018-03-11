package com.hyy.android.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.hyy.android.common.R;
import com.hyy.android.common.view.HyyDatePicker;

import java.util.Calendar;

/**
 * Created by hyy on 2017/1/20.
 */

public class DoubleDatePickDialog extends Dialog {

    public interface OnDatePickDialogClickListener {

        int POSITIVE = 1;
        int NEGATIVE = 0;

        void onClick(Calendar chooseCanlendar1,Calendar chooseCanlendar2, int which);
    }


    public DoubleDatePickDialog(Context context) {
        super(context);
    }

    public DoubleDatePickDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        private HyyDatePicker datePicker1;
        private HyyDatePicker datePicker2;
        private String positiveButtonText;
        private String negativeButtonText;

        private OnDatePickDialogClickListener positiveButtonClickListener;
        private OnDatePickDialogClickListener negativeButtonClickListener;

        private int YEAR = -1;
        private int MONTH = -1;
        private int DAY = -1;

        private int YEAR2 = -1;
        private int MONTH2 = -1;
        private int DAY2 = -1;


        private boolean useYear;
        private boolean useMonth;
        private boolean useDay;
        private Drawable titleBg;

        private int selectFromYear = -1;
        private int selectToYear = -1;

        private String data_picker1_desc;
        private String data_picker2_desc;


        public Builder(Context context) {
            this.context = context;
        }

//        public Builder setInitDate(int  year,int month,int day) {
//            YEAR = year;
//            MONTH = month;
//            DAY = day;
//            return this;
//        }


        public Builder setSelectRange(int selectFromYear,int selectToYear){
            this.selectFromYear = selectFromYear;
            this.selectToYear = selectToYear;
            return this;
        }


        public Builder setInitDate(Calendar initCalendar) {
            YEAR = initCalendar.get(Calendar.YEAR);
            MONTH = initCalendar.get(Calendar.MONTH);
            DAY = initCalendar.get(Calendar.DAY_OF_MONTH);
            return this;
        }

        public Builder setInitDate2(Calendar initCalendar) {
            YEAR2 = initCalendar.get(Calendar.YEAR);
            MONTH2 = initCalendar.get(Calendar.MONTH);
            DAY2 = initCalendar.get(Calendar.DAY_OF_MONTH);
            return this;
        }


        public Builder setYMDDisplay(boolean useYear,boolean useMonth,boolean useDay) {
            this.useYear = useYear;
            this.useMonth = useMonth;
            this.useDay = useDay;
            return this;
        }

        public Builder setTitleBg(Drawable titleBgDrawable){

            titleBg = titleBgDrawable;
            return this;
        }


        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }


        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnDatePickDialogClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnDatePickDialogClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnDatePickDialogClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnDatePickDialogClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }


        public Builder setDataPicker1Desc(String desc){

            data_picker1_desc = desc;
            return this;
        }

        public Builder setDataPicker2Desc(String desc){

            data_picker2_desc = desc;
            return this;
        }


        public DoubleDatePickDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final DoubleDatePickDialog dialog = new DoubleDatePickDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.hyy_commlib_dialog_double_date_pick_layout, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            ((TextView) layout.findViewById(R.id.title)).setText(title);
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {

                                    Calendar ca1 = Calendar.getInstance();
                                    ca1.set(YEAR,MONTH,DAY);

                                    Calendar ca2 = Calendar.getInstance();
                                    ca2.set(YEAR2,MONTH2,DAY2);

                                    positiveButtonClickListener.onClick(ca1,ca2,
                                            OnDatePickDialogClickListener.POSITIVE);
                                    dialog.dismiss();
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {

                                    Calendar ca1 = Calendar.getInstance();
                                    ca1.set(YEAR,MONTH,DAY);

                                    Calendar ca2 = Calendar.getInstance();
                                    ca2.set(YEAR2,MONTH2,DAY2);

                                    negativeButtonClickListener.onClick(ca1,ca2,
                                            OnDatePickDialogClickListener.NEGATIVE);
                                    dialog.dismiss();
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }

            // set the content datePicker1
            Calendar calendar = Calendar.getInstance();
            YEAR = -1 == YEAR ?  calendar.get(Calendar.YEAR):YEAR;
            MONTH = -1 == MONTH ?  calendar.get(Calendar.MONTH):MONTH;
            DAY = -1 == DAY ?  calendar.get(Calendar.DAY_OF_MONTH):DAY;

            YEAR2 = -1 == YEAR2 ?  calendar.get(Calendar.YEAR):YEAR2;
            MONTH2 = -1 == MONTH2 ?  calendar.get(Calendar.MONTH):MONTH2;
            DAY2 = -1 == DAY2 ?  calendar.get(Calendar.DAY_OF_MONTH):DAY2;

            datePicker1 = ((HyyDatePicker) layout.findViewById(R.id.date_picker1));
            datePicker2 = ((HyyDatePicker) layout.findViewById(R.id.date_picker2));

            if(-1 == selectFromYear || -1 == selectToYear){
                datePicker1.init(YEAR, MONTH, DAY, new HyyDatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(HyyDatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
//                    YEAR = datePicker1.getYear();
//                    MONTH = datePicker1.getMonth();
//                    DAY = datePicker1.getDayOfMonth();

                        YEAR = year;
                        MONTH = monthOfYear;
                        DAY = dayOfMonth;

                    }
                });

                datePicker2.init(YEAR2, MONTH2, DAY2, new HyyDatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(HyyDatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
//                    YEAR = datePicker1.getYear();
//                    MONTH = datePicker1.getMonth();
//                    DAY = datePicker1.getDayOfMonth();

                        YEAR2 = year;
                        MONTH2 = monthOfYear;
                        DAY2 = dayOfMonth;

                    }
                });

            }else{
                datePicker1.init(YEAR, MONTH, DAY,selectFromYear,selectToYear, new HyyDatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(HyyDatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
//                    YEAR = datePicker1.getYear();
//                    MONTH = datePicker1.getMonth();
//                    DAY = datePicker1.getDayOfMonth();

                        YEAR = year;
                        MONTH = monthOfYear;
                        DAY = dayOfMonth;

                    }
                });

                datePicker2.init(YEAR2, MONTH2, DAY2,selectFromYear,selectToYear, new HyyDatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(HyyDatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
//                    YEAR = datePicker1.getYear();
//                    MONTH = datePicker1.getMonth();
//                    DAY = datePicker1.getDayOfMonth();

                        YEAR2 = year;
                        MONTH2 = monthOfYear;
                        DAY2 = dayOfMonth;

                    }
                });
            }



            datePicker1.setUseYear(useYear);
            datePicker1.setUseMonth(useMonth);
            datePicker1.setUseDay(useDay);
            datePicker1.refreshYMD();

            datePicker2.setUseYear(useYear);
            datePicker2.setUseMonth(useMonth);
            datePicker2.setUseDay(useDay);
            datePicker2.refreshYMD();



            //set bg
            if(null == titleBg){
                titleBg =  ContextCompat.getDrawable(context,R.drawable.dialog_title_bg);
            }

            layout.findViewById(R.id.title).setBackgroundDrawable(titleBg);

            //set desc
            ((TextView)layout.findViewById(R.id.tv_data_pick1_desc)).setText(data_picker1_desc);
            ((TextView)layout.findViewById(R.id.tv_data_pick2_desc)).setText(data_picker2_desc);


            dialog.setContentView(layout);



            return dialog;
        }
    }
}
