package com.hyy.android.common.adapter;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;


public class AdPagerAdapter extends PagerAdapter {

	@SuppressWarnings("unused")
	private Context context;
	private List<View> list;

	public AdPagerAdapter(List<View> list) {
		this.list = list;
	}

	public AdPagerAdapter(Context context, List<View> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// list.get(position).setOnClickListener();
		ImageView tempIv = (ImageView)list.get(position);
		tempIv.setScaleType(ImageView.ScaleType.FIT_XY);
		container.addView(tempIv);
		return list.get(position);
	}

}
