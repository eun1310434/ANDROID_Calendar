/*=====================================================================
□ Infomation
  ○ Data : 08.03.2018
  ○ Mail : eun1310434@naver.com
  ○ Blog : https://blog.naver.com/eun1310434
  ○ Reference : Do it android app Programming

□ Function
  ○ 일자에 표시하는 텍스트뷰
  ○ Custom TextView


□ Study
  ○
=====================================================================*/
package com.eun1310434.calendar;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.GridView;

public class MonthItemView extends AppCompatTextView {

	private MonthItem item;
	
	public MonthItemView(Context context) {
		super(context);
		init();
	}
	
	public MonthItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setTextSize(15);
	}
	

	public MonthItem getItem() {
		return item;
	}

	public void setItem(int _position, MonthItem _item, GridView.LayoutParams _params, int paddingSpace, int _columnIndex, int _selectedPosition) {
		this.item = _item;
		int day = item.getDay();
		setText((day != 0) ? String.valueOf(day) : "");
		setLayoutParams(_params);

		// set properties
		setPadding(paddingSpace, paddingSpace, paddingSpace, paddingSpace);
		setGravity(Gravity.CENTER);

		// set background text olor
		if (_position == _selectedPosition) {
			setBackgroundColor(getResources().getColor(R.color.colorAccent));
			setTextColor((_columnIndex == 0) ? Color.RED : Color.BLACK);
		} else {
			setBackgroundColor(Color.BLACK);
			setTextColor((_columnIndex == 0) ? Color.RED : Color.WHITE);
		}

	}


}
