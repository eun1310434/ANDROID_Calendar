/*=====================================================================
□ Infomation
  ○ Data : 08.03.2018
  ○ Mail : eun1310434@naver.com
  ○ Blog : https://blog.naver.com/eun1310434
  ○ Reference : Do it android app Programming

□ Function
  ○ 그리드뷰 활용 월별 캘린더

□ Study
  ○
=====================================================================*/
package com.eun1310434.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //뷰 객체
    GridView monthView;

    //어댑터
    MonthAdapter monthViewAdapter;

    //월을 표시하는 텍스트뷰ㄴ
    TextView monthText;

    //현재 연도
    int curYear;

    //현재 월
    int curMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 월별 캘린더 뷰 객체 참조
        monthView = (GridView) findViewById(R.id.monthView);
        monthViewAdapter = new MonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);

        // 리스너 설정
        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 현재 선택한 일자 정보 표시
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                int day = curItem.getDay();
                Log.e("MainActivity", "Selected : " + day);


                //선택한 날짜의 배경색을 달리함
                monthViewAdapter.setSelectedoPsition(position);

                // notifyDataSetChanged()은 Adapter의 getView()가 불려짐
                monthViewAdapter.notifyDataSetChanged();
            }
        });


        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        // 이전 월로 넘어가는 이벤트 처리
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // 다음 월로 넘어가는 이벤트 처리
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

    }

    //월 표시 텍스트 설정
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();
        monthText.setText(curYear + ". " +(curMonth + 1));
    }

}
