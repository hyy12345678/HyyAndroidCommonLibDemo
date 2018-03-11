package com.hyy.android.common.view;

import java.util.ArrayList;
import java.util.List;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.hyy.android.common.R;


public class HyyTimePicker extends RelativeLayout
{

	private Context context;
    private LayoutInflater inflater;
    
    private View innerView;
	
    PickerView hour_pv;
	PickerView minute_pv;
	PickerView second_pv;


	private OnTimeChangedListener timeChangedListener;

	private int selectedHour;
	private int selectedMinute;
	private int selectedSecond;


	boolean useHour;
	boolean useMinute;
	boolean useSecond;

    Drawable bgColor;

	public HyyTimePicker(Context context)
	{
		super(context);
		this.context = context;
		init(null,0);
	}

	public HyyTimePicker(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		this.context = context;
		init(attrs,0);
	}
	
	public HyyTimePicker(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor 
		init(attrs, defStyleAttr);
		
		
	}

	private void init(AttributeSet attrs, int defStyle) {
		// TODO Auto-generated method stub

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.hyyTimePicker, defStyle, 0);

        // init bgColor
        if (a.hasValue(R.styleable.hyyTimePicker_tpBgColor)) {
            bgColor = a.getDrawable(R.styleable.hyyTimePicker_tpBgColor);
        }

        // init useHour
        if (a.hasValue(R.styleable.hyyTimePicker_useHour)) {
            useHour = a.getBoolean(R.styleable.hyyTimePicker_useHour, true);
        }

        // init useMinute
        if (a.hasValue(R.styleable.hyyTimePicker_useMinute)) {
            useMinute = a.getBoolean(R.styleable.hyyTimePicker_useMinute, true);
        }

        // init useSecond
        if (a.hasValue(R.styleable.hyyTimePicker_useSecond)) {
            useSecond = a.getBoolean(R.styleable.hyyTimePicker_useSecond, true);
        }


		
		initView();
		initData();
		initEvent();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		innerView = inflater.inflate(R.layout.hyy_commlib_time_picker, null);
		
		hour_pv = (PickerView) innerView.findViewById(R.id.hour_pv);
		minute_pv = (PickerView) innerView.findViewById(R.id.minute_pv);
		second_pv = (PickerView) innerView.findViewById(R.id.second_pv);

        refreshHMS();
		
		this.addView(innerView);
	}
	
	private void initData() {
		// TODO Auto-generated method stub
		
		List<String> hours = new ArrayList<String>();
		List<String> minutes = new ArrayList<String>();
		List<String> seconds = new ArrayList<String>();
		
		for (int i = 0; i < 24; i++)
		{
			hours.add(i < 10 ? "0" + i : "" + i);
		}
		
		for (int i = 0; i < 60 ; i++)
		{
			minutes.add(i < 10 ? "0" + i : "" + i);
		}
		
		for (int i = 0; i < 60; i++)
		{
			seconds.add(i < 10 ? "0" + i : "" + i);
		}
		
		hour_pv.setData(hours);
		minute_pv.setData(minutes);
		second_pv.setData(seconds);
		
		
	}
	
	private void initEvent() {
		// TODO Auto-generated method stub
		
		
		hour_pv.setOnSelectListener(new PickerView.onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
//				Toast.makeText(context, "选择了 " + text + " 时",
//						Toast.LENGTH_SHORT).show();

				selectedHour = Integer.valueOf(text);

				if (timeChangedListener != null) {
					timeChangedListener.onTimeChanged(HyyTimePicker.this,
							getHour(), getMinute(), getSecond());
				}

			}
		});
		
		minute_pv.setOnSelectListener(new PickerView.onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
//				Toast.makeText(context, "选择了 " + text + " 分",
//						Toast.LENGTH_SHORT).show();


				selectedMinute = Integer.valueOf(text);

				if (timeChangedListener != null) {
					timeChangedListener.onTimeChanged(HyyTimePicker.this,
							getHour(), getMinute(), getSecond());
				}


			}
		});
		
		second_pv.setOnSelectListener(new PickerView.onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
//				Toast.makeText(context, "选择了 " + text + " 秒",
//						Toast.LENGTH_SHORT).show();

				selectedSecond = Integer.valueOf(text);

				if (timeChangedListener != null) {
					timeChangedListener.onTimeChanged(HyyTimePicker.this,
							getHour(), getMinute(), getSecond());
				}

			}
		});
	}



	/**
	 * 获取当前选择的Hour
	 *
	 * @return
	 */
	public int getHour() {
		return Integer.valueOf(selectedHour);
	}

	/**
	 * 获取当前选择的Minute
	 *
	 * @return
	 */
	public int getMinute() {
		return Integer.valueOf(selectedMinute);
	}

	/**
	 * 获取当前选择的Second
	 *
	 * @return
	 */
	public int getSecond() {
		return Integer.valueOf(selectedSecond);
	}


	public boolean isUseHour() {
		return useHour;
	}

	public void setUseHour(boolean useHour) {
		this.useHour = useHour;
	}

	public boolean isUseMinute() {
		return useMinute;
	}

	public void setUseMinute(boolean useMinute) {
		this.useMinute = useMinute;
	}

	public boolean isUseSecond() {
		return useSecond;
	}

	public void setUseSecond(boolean useSecond) {
		this.useSecond = useSecond;
	}



    /**
     * 刷新项目显示控制
     */
    public void refreshHMS() {

        if (!useHour) {
            hour_pv.setVisibility(GONE);
        }

        if (!useMinute) {
            minute_pv.setVisibility(GONE);
        }

        if (!useSecond) {
            second_pv.setVisibility(GONE);
        }

    }

	/***
	 * 初始化时间选择控件
	 *
	 * @param hour
	 * @param minute
	 * @param second
	 * @param listener
	 */
	public void init(int hour, int minute, int second,
					 OnTimeChangedListener listener) {

		this.initData();

		this.timeChangedListener = listener;

		this.selectedHour = hour;
		this.selectedMinute = minute;
		this.selectedSecond = second;


		hour_pv.setSelected(hour);
		minute_pv.setSelected(minute);
		second_pv.setSelected(second);

	}


	/**
	 * 年月日变更触发监听
	 */
	public interface OnTimeChangedListener {
		public void onTimeChanged(HyyTimePicker view, int hour,
								  int minute, int second);
	}

	
}
