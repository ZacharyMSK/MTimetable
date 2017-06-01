package com.zjgsu.luoweiguang.mtimetable;

/**
 * Created by Zachary on 2017/5/31.
 */

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dsw.calendar.component.MonthView;
import com.dsw.calendar.entity.CalendarInfo;
import com.dsw.calendar.views.GridCalendarView;
import com.zjgsu.luoweiguang.mtimetable.com.zjgsu.luoweiguang.db.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class FragmentA extends Fragment {
    protected boolean isCreate = false;
    private Button renewButton;
    private Button deleteButton;
    private MyDatabaseHelper dbHelper;
    List<CalendarInfo> list;
    GridCalendarView gridCalendarView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout1, null);

        gridCalendarView = (GridCalendarView) view.findViewById(R.id.gridMonthView);
        list = new ArrayList<CalendarInfo>();
        dbHelper=new MyDatabaseHelper(getContext(),"schedule.db",null,1);
        renewButton=(Button)view.findViewById(R.id.renew_button);
        deleteButton=(Button)view.findViewById(R.id.delete_button);
        renewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db=dbHelper.getWritableDatabase();
                Cursor cursor =db.query("Schedule",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String date=cursor.getString(cursor.getColumnIndex("datetime"));
                        String startTime=cursor.getString(cursor.getColumnIndex("startTime"));
                        String endtime=cursor.getString(cursor.getColumnIndex("endtime"));
                        String loca=cursor.getString(cursor.getColumnIndex("location"));
                        int year=Integer.valueOf(date.substring(0,4));
                        int month=Integer.valueOf(date.substring(5,6));
                        int day=Integer.valueOf(date.substring(7));
//                        Toast.makeText(getContext(), name+" at "+loca+" in "+date+" last "+startTime+"-"+endtime, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), "已标记", Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity", "name: "+name);
                        Log.d("MainActivity", "datetime: "+date);
                        Log.d("MainActivity", "startTime: "+startTime);
                        Log.d("MainActivity", "endtime: "+endtime);
                        Log.d("MainActivity", "loca: "+loca);
                        list.add(new CalendarInfo(year,month,day,name+"@"+loca));
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(),ScheduleDeleteActivity.class);
                startActivity(intent);
            }
        });



        gridCalendarView.setCalendarInfos(list);

        gridCalendarView.setDateClick(new MonthView.IDateClick() {
            @Override

            public void onClickOnDate(int year, int month, int day) {
                String date_query=year + "-" + month + "-" + day;
                //Toast.makeText(getContext(),"点击了" +  date_query,Toast.LENGTH_SHORT).show();
                SQLiteDatabase db=dbHelper.getWritableDatabase();

                Cursor cursor =db.query("Schedule",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String loca=cursor.getString(cursor.getColumnIndex("location"));
                        String startTime=cursor.getString(cursor.getColumnIndex("startTime"));
                        String endtime=cursor.getString(cursor.getColumnIndex("endtime"));
                        String date=cursor.getString(cursor.getColumnIndex("datetime"));
                        if (date.equals(date_query)) {
                            Toast.makeText(getContext(), "日期：" + date_query + "\n时间：从" + startTime + "到" + endtime + " \n事项：" + name, Toast.LENGTH_LONG).show();
                        }
                    }while (cursor.moveToNext());
                }
            }

        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            renewButton.callOnClick();
            //相当于Fragment的onResume
            //在这里处理加载数据等操作
        } else {
            //相当于Fragment的onPause
        }
    }
}