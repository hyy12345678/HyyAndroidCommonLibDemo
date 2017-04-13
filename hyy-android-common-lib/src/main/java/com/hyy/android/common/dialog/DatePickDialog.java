package com.hyy.android.common.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hyy.android.common.R;
import com.hyy.android.common.view.HyyDatePicker;

import java.util.Calendar;

/**
 * Created by hyy on 2017/1/20.
 */

public class DatePickDialog extends Dialog {

    public interface OnDatePickDialogClickListener {

        int POSITIVE = 1;
        int NEGATIVE = 0;

        void onClick(Calendar chooseCanlendar,int which);
    }


    public DatePickDialog(Context context) {
        super(context);
    }

    public DatePickDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        private HyyDatePicker datePicker;
        private String positiveButtonText;
        private String negativeButtonText;

        private OnDatePickDialogClickListener positiveButtonClickListener;
        private OnDatePickDialogClickListener negativeButtonClickListener;

        private int YEAR;
        private int MONTH;
        private int DAY;

        private boolean useYear;
        private boolean useMonth;
        private boolean useDay;
        private Drawable titleBg;

        private int selectFromYear = -1;
        private int selectToYear = -1;

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

        public DatePickDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final DatePickDialog dialog = new DatePickDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_date_pick_layout, null);
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

                                    Calendar ca = Calendar.getInstance();
                                    ca.set(YEAR,MONTH,DAY);

                                    positiveButtonClickListener.onClick(ca,
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

                                    Calendar ca = Calendar.getInstance();
                                    ca.set(YEAR,MONTH,DAY);

                                    negativeButtonClickListener.onClick(ca,
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

            // set the content datePicker
            Calendar calendar = Calendar.getInstance();
            YEAR = 0 == YEAR ?  calendar.get(Calendar.YEAR):YEAR;
            MONTH = 0 == MONTH ?  calendar.get(Calendar.MONTH):MONTH;
            DAY = 0 == DAY ?  calendar.get(Calendar.DAY_OF_MONTH):DAY;

            datePicker = ((HyyDatePicker) layout.findViewById(R.id.date_picker));

            if(-1 == selectFromYear || -1 == selectToYear){
                datePicker.init(YEAR, MONTH, DAY, new HyyDatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(HyyDatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
//                    YEAR = datePicker.getYear();
//                    MONTH = datePicker.getMonth();
//                    DAY = datePicker.getDayOfMonth();

                        YEAR = year;
                        MONTH = monthOfYear;
                        DAY = dayOfMonth;

                    }
                });
            }else{
                datePicker.init(YEAR, MONTH, DAY,selectFromYear,selectToYear, new HyyDatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(HyyDatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
//                    YEAR = datePicker.getYear();
//                    MONTH = datePicker.getMonth();
//                    DAY = datePicker.getDayOfMonth();

                        YEAR = year;
                        MONTH = monthOfYear;
                        DAY = dayOfMonth;

                    }
                });
            }



            datePicker.setUseYear(useYear);
            datePicker.setUseMonth(useMonth);
            datePicker.setUseDay(useDay);
            datePicker.refreshYMD();


            //set bg
            if(null == titleBg){
                titleBg =  ContextCompat.getDrawable(context,R.drawable.dialog_title_bg);
            }
            layout.findViewById(R.id.title).setBackgroundDrawable(titleBg);

            dialog.setContentView(layout);



            return dialog;
        }
    }
}
