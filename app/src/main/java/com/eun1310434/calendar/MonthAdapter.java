/*=====================================================================
□ Infomation
  ○ Data : 08.03.2018
  ○ Mail : eun1310434@naver.com
  ○ Blog : https://blog.naver.com/eun1310434
  ○ Reference : Do it android app Programming

□ Function
  ○ 어뎁터와 뷰객체의 상호 작용을 통한 효율적 구현

□ Study
  ○
=====================================================================*/
package com.eun1310434.calendar;

import android.content.Context;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;

public class MonthAdapter extends BaseAdapter {

	public static final String TAG = "MonthAdapter";
	
	Context mContext;

	private int selectedPosition = -1;
	
	private MonthItem[] items;
	
	private int countColumn = 7;

	int mStartDay;
	int startDay;
	int curYear;
	int curMonth;

	int firstDay;
	int lastDay;

	Calendar mCalendar;
	
	public MonthAdapter(Context context) {
		super();
		mContext = context;
		init();
	}
	
	public MonthAdapter(Context context, AttributeSet attrs) {
		super();
		mContext = context;
		init();
	}

	private void init() {
		items = new MonthItem[7 * 6];

		mCalendar = Calendar.getInstance();
		recalculate(); //최초 설정
		resetDayNumbers();
		
	}
	
	public void recalculate() {
		// set to the first day of the month
		mCalendar.set(Calendar.DAY_OF_MONTH, 1);
		
		// get week day
		int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
		firstDay = getFirstDay(dayOfWeek);
		Log.d(TAG, "firstDay : " + firstDay);
		
		mStartDay = mCalendar.getFirstDayOfWeek();
		curYear = mCalendar.get(Calendar.YEAR);
		curMonth = mCalendar.get(Calendar.MONTH);
		lastDay = getMonthLastDay(curYear, curMonth); //마지막 일
		
		Log.d(TAG, "curYear : " + curYear + ", curMonth : " + curMonth + ", lastDay : " + lastDay);
		
		int diff = mStartDay - Calendar.SUNDAY - 1;
        startDay = getFirstDayOfWeek();
		Log.d(TAG, "mStartDay : " + mStartDay + ", startDay : " + startDay);
		
	}
	
	public void setPreviousMonth() {
		mCalendar.add(Calendar.MONTH, -1);
        recalculate();
        
        resetDayNumbers();
        selectedPosition = -1;
	}
	
	public void setNextMonth() {
		mCalendar.add(Calendar.MONTH, 1);
        recalculate();
        
        resetDayNumbers();
        selectedPosition = -1;
	}
	
	public void resetDayNumbers() {
		for (int i = 0; i < 42; i++) {
			// calculate day number
			int dayNumber = (i+1) - firstDay;
			if (dayNumber < 1 || dayNumber > lastDay) {
				dayNumber = 0;
			}
			
	        // save as a data item
	        items[i] = new MonthItem(dayNumber);
		}
	}
	
	private int getFirstDay(int dayOfWeek) {
		int result = 0;
		if (dayOfWeek == Calendar.SUNDAY) {
			result = 0;
		} else if (dayOfWeek == Calendar.MONDAY) {
			result = 1;
		} else if (dayOfWeek == Calendar.TUESDAY) {
			result = 2;
		} else if (dayOfWeek == Calendar.WEDNESDAY) {
			result = 3;
		} else if (dayOfWeek == Calendar.THURSDAY) {
			result = 4;
		} else if (dayOfWeek == Calendar.FRIDAY) {
			result = 5;
		} else if (dayOfWeek == Calendar.SATURDAY) {
			result = 6;
		}
		
		return result;
	}
	
	
	public int getCurYear() {
		return curYear;
	}
	
	public int getCurMonth() {
		return curMonth;
	}
	
	
	public int getNumColumns() {
		return 7;
	}

	public int getCount() {
		return 7 * 6;
	}

	public Object getItem(int position) {
		return items[position];
	}

	public long getItemId(int position) {
		return position;
	}



	MonthItemView itemView = null;
	public View getView(int position, View convertView, ViewGroup parent) {

		// calculate row and column
		int rowIndex = position / countColumn;
		int columnIndex = position % countColumn;
		Log.d(TAG, "getView(" + position + ") called.");
		Log.d(TAG, "Index : " + rowIndex + ", " + columnIndex);


		if (convertView == null) {
			itemView = new MonthItemView(mContext);
		} else {
			itemView = (MonthItemView) convertView;
		}	
		
		// create a params
		// 각각의 날자 배경의 크기 설정
		GridView.LayoutParams params= new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT,250);



		// set item data and properties
		itemView.setItem(
				position,
				items[position],
				params,
				2,
				columnIndex,
				getSelectedPosition()
		);

		return itemView;
	}

	
     //Get first day of week as android.text.format.Time constant.
     //the first day of week in android.text.format.Time
    public static int getFirstDayOfWeek() {
        int startDay = Calendar.getInstance().getFirstDayOfWeek();
        if (startDay == Calendar.SATURDAY) {
            return Time.SATURDAY;
        } else if (startDay == Calendar.MONDAY) {
            return Time.MONDAY;
        } else {
            return Time.SUNDAY;
        }
    }
 
	
    //get day count for each month
    private int getMonthLastDay(int year, int month){
    	switch (month) {
 	   		case 0:
      		case 2:
      		case 4:
      		case 6:
      		case 7:
      		case 9:
      		case 11:
      			return (31);

      		case 3:
      		case 5:
      		case 8:
      		case 10:
      			return (30);

      		default:
      			if(((year%4==0)&&(year%100!=0)) || (year%400==0) ) {
      				return (29);   // 2월 윤년계산
      			} else { 
      				return (28);
      			}
 	   	}
 	}

	//set selected row
	public void setSelectedoPsition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	//get selected row
	public int getSelectedPosition() {
		return selectedPosition;
	}
}
