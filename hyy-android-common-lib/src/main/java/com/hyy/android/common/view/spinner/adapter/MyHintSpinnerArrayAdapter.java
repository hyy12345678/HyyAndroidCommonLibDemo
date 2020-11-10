package com.hyy.android.common.view.spinner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.hyy.android.common.R;

import java.util.List;

public class MyHintSpinnerArrayAdapter<T> extends ArrayAdapter<T> {

	private Context mContext;
	private List<T> mStringArray;

	public MyHintSpinnerArrayAdapter(Context context, List<T> stringArray) {
		super(context, android.R.layout.simple_spinner_item, stringArray);
		mContext = context;
		mStringArray = stringArray;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// 修改Spinner展开后的字体颜色
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(
					android.R.layout.simple_spinner_dropdown_item, parent,
					false);
		}

		// 此处text1是Spinner默认的用来显示文字的TextView
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(String.valueOf(mStringArray.get(position)));
		tv.setTextSize(15f);
		tv.setTextColor(Color.DKGRAY);

		return convertView;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 修改Spinner选择后结果的字体颜色
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(
					android.R.layout.simple_spinner_item, parent, false);
		}

		// 此处text1是Spinner默认的用来显示文字的TextView
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(String.valueOf(mStringArray.get(position)));
		tv.setTextSize(14f);
		tv.setGravity(Gravity.RIGHT);
        tv.setSingleLine();
		tv.setEllipsize(TextUtils.TruncateAt.valueOf("MIDDLE"));
		tv.setTextColor(mContext.getResources().getColor(R.color.hyy_commlib_black));
		return convertView;
	}

	@Override
	public int getCount() {
		return super.getCount()-1;
	}
}
