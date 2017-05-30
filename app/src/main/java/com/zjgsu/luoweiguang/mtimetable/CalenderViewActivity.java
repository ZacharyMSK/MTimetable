package com.zjgsu.luoweiguang.mtimetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dsw.calendar.component.MonthView;
import com.dsw.calendar.views.GridCalendarView;

public class CalenderViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);
        GridCalendarView gridCalendarView = (GridCalendarView) findViewById(R.id.gridMonthView);
        gridCalendarView.setDateClick(new MonthView.IDateClick(){

            @Override
            public void onClickOnDate(int year, int month, int day) {
                Toast.makeText(CalenderViewActivity.this,"点击了" +  year + "-" + month + "-" + day,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
