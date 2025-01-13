package com.hyy.android.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.hyy.android.common.R;
import com.hyy.android.common.view.HyyTimePicker;

import java.util.Calendar;

/**
 * Created by hyy on 2017/1/20.
 */

public class TimePickDialog extends Dialog {

    public interface OnTimePickDialogClickListener {

        int POSITIVE = 1;
        int NEGATIVE = 0;

        void onClick(Calendar chooseCanlendar, int which);
    }


    public TimePickDialog(Context context) {
        super(context);
    }

    public TimePickDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        private HyyTimePicker timePicker;
        private String positiveButtonText;
        private String negativeButtonText;

        private OnTimePickDialogClickListener positiveButtonClickListener;
        private OnTimePickDialogClickListener negativeButtonClickListener;

        private int HOUR;
        private int MINUTE;
        private int SECOND;

        private boolean useHour;
        private boolean useMinute;
        private boolean useSecond;
        private Drawable titleBg;


        public Builder(Context context) {
            this.context = context;
        }


        public Builder setInitDate(Calendar initCalendar) {
            HOUR = initCalendar.get(Calendar.HOUR);
            MINUTE = initCalendar.get(Calendar.MINUTE);
            SECOND = initCalendar.get(Calendar.SECOND);
            return this;
        }

        public Builder setHMSDisplay(boolean useHour, boolean useMinute, boolean useSecond) {
            this.useHour = useHour;
            this.useMinute = useMinute;
            this.useSecond = useSecond;
            return this;
        }

        public Builder setTitleBg(Drawable titleBgDrawable) {

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
                                         OnTimePickDialogClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnTimePickDialogClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnTimePickDialogClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnTimePickDialogClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public TimePickDialog create() {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final TimePickDialog dialog = new TimePickDialog(context, R.style.hyy_commlib_Dialog);
            View layout = inflater.inflate(R.layout.hyy_commlib_dialog_time_pick_layout, null);
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
//                                    ca.set(YEAR,MONTH,DAY);
                                    ca.set(Calendar.HOUR, HOUR);
                                    ca.set(Calendar.MINUTE, MINUTE);
                                    ca.set(Calendar.SECOND, SECOND);


                                    positiveButtonClickListener.onClick(ca,
                                            OnTimePickDialogClickListener.POSITIVE);
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
//                                    ca.set(YEAR,MONTH,DAY);
                                    ca.set(Calendar.HOUR, HOUR);
                                    ca.set(Calendar.MINUTE, MINUTE);
                                    ca.set(Calendar.SECOND, SECOND);

                                    negativeButtonClickListener.onClick(ca,
                                            OnTimePickDialogClickListener.NEGATIVE);
                                    dialog.dismiss();
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }

            // set the content timePicker
            Calendar calendar = Calendar.getInstance();
            HOUR = 0 == HOUR ? calendar.get(Calendar.HOUR) : HOUR;
            MINUTE = 0 == MINUTE ? calendar.get(Calendar.MINUTE) : MINUTE;
            SECOND = 0 == SECOND ? calendar.get(Calendar.SECOND) : SECOND;

            timePicker = ((HyyTimePicker) layout.findViewById(R.id.time_picker));
            timePicker.init(HOUR, MINUTE, SECOND, new HyyTimePicker.OnTimeChangedListener() {

                @Override
                public void onTimeChanged(HyyTimePicker view, int hour,
                                          int minute, int second) {
                    // TODO Auto-generated method stub
                    HOUR = hour;
                    MINUTE = minute;
                    SECOND = second;
                }
            });
            timePicker.setUseHour(useHour);
            timePicker.setUseMinute(useMinute);
            timePicker.setUseSecond(useSecond);
            timePicker.refreshHMS();


            //set bg
            if (null == titleBg) {
                titleBg = ContextCompat.getDrawable(context, R.drawable.hyy_commlib_dialog_title_bg);
            }
            layout.findViewById(R.id.title).setBackgroundDrawable(titleBg);

            dialog.setContentView(layout);



            return dialog;
        }
    }
}
