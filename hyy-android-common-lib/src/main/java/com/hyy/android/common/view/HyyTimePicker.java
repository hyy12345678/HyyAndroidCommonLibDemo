package com.hyy.android.common.view;

import java.util.ArrayList;
import java.util.List;



import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hyy.android.common.R;


public class HyyTimePicker extends RelativeLayout
{

	private Context context;
    private LayoutInflater inflater;
    
    private View innerView;
	
    PickerView hour_pv;
	PickerView minute_pv;
	PickerView second_pv;
	

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
		
		
		initView();
		initData();
		initEvent();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		innerView = inflater.inflate(R.layout.hyy_time_picker, null);
		
		hour_pv = (PickerView) innerView.findViewById(R.id.hour_pv);
		minute_pv = (PickerView) innerView.findViewById(R.id.minute_pv);
		second_pv = (PickerView) innerView.findViewById(R.id.second_pv);
		
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
		
		hour_pv.setSelected(0);
		minute_pv.setSelected(0);
		second_pv.setSelected(0);
		
		
	}
	
	private void initEvent() {
		// TODO Auto-generated method stub
		
		
		hour_pv.setOnSelectListener(new PickerView.onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
				Toast.makeText(context, "选择了 " + text + " 时",
						Toast.LENGTH_SHORT).show();
			}
		});
		
		minute_pv.setOnSelectListener(new PickerView.onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
				Toast.makeText(context, "选择了 " + text + " 分",
						Toast.LENGTH_SHORT).show();
			}
		});
		
		second_pv.setOnSelectListener(new PickerView.onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
				Toast.makeText(context, "选择了 " + text + " 秒",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	

	
}
