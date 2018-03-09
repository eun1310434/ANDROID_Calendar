/*=====================================================================
□ Infomation
  ○ Data : 08.03.2018
  ○ Mail : eun1310434@naver.com
  ○ Blog : https://blog.naver.com/eun1310434
  ○ Reference : Do it android app Programming

□ Function
  ○ 일자 정보를 담기 위한 클래스 정의
  ○ 추후 선택 구별 부분도 작성 필요

□ Study
  ○
=====================================================================*/
package com.eun1310434.calendar;

//
public class MonthItem {

	private int dayValue;

	public MonthItem() {
		
	}
	
	public MonthItem(int day) {
		dayValue = day;
	}
	
	public int getDay() {
		return dayValue;
	}

	public void setDay(int day) {
		this.dayValue = day;
	}
	
	
	
}
