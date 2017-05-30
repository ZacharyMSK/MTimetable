package com.zjgsu.luoweiguang.mtimetable;

/**
 * Created by Zachary on 2017/5/31.
 */

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentB extends Fragment{
    LinearLayout weekPanels[]=new LinearLayout[7];
    List courseData[]=new ArrayList[7];
    int itemHeight;
    int marTop,marLeft;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view=inflater.inflate(R.layout.layout2, null);
        itemHeight=getResources().getDimensionPixelSize(R.dimen.weekItemHeight);
        marTop=getResources().getDimensionPixelSize(R.dimen.weekItemMarTop);
        marLeft=getResources().getDimensionPixelSize(R.dimen.weekItemMarLeft);
        //数据
        getData();

        for (int i = 0; i < weekPanels.length; i++) {
            weekPanels[i]=(LinearLayout) view.findViewById(R.id.weekPanel_1+i);
            initWeekPanel(weekPanels[i], courseData[i]);
        }
        return view;
    }

    public void getData(){
        List<Course>list1=new ArrayList<Course>();
        Course c1 =new Course("软件工程","A402", 1, 4, "典韦", "1002");
        list1.add(c1);
        list1.add(new Course("C语言", "A101", 6, 3, "甘宁", "1001"));
        courseData[0]=list1;

        List<Course>list2=new ArrayList<Course>();
        list2.add(new Course("计算机组成原理", "A106", 6, 3, "马超", "1001"));
        courseData[1]=list2;

        List<Course>list3=new ArrayList<Course>();
        list3.add(new Course("数据库原理", "A105", 2, 3, "孙权", "1008"));
        list3.add(new Course("计算机网络", "A405", 6, 2, "司马懿", "1009"));
        list3.add(new Course("电影赏析", "A112", 9, 2, "诸葛亮", "1039"));
        courseData[2]=list3;

        List<Course>list4=new ArrayList<Course>();
        list4.add(new Course("数据结构", "A223", 1, 3, "刘备", "1012"));
        list4.add(new Course("操作系统", "A405", 6, 3, "曹操", "1014"));
        courseData[3]=list4;

        List<Course>list5=new ArrayList<Course>();
        list5.add(new Course("Android开发","C120",1,4,"黄盖","1250"));
        list5.add(new Course("游戏设计原理","C120",8,4,"陆逊","1251"));
        courseData[4]=list5;
    }

    public void initWeekPanel(LinearLayout ll,List<Course>data){
        if(ll==null || data==null || data.size()<1)return;
        Log.i("Msg", "初始化面板");
        Course pre=data.get(0);
        for (int i = 0; i < data.size(); i++) {
            Course c =data.get(i);
            TextView tv =new TextView(getContext());
            LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT ,
                    itemHeight*c.getStep()+marTop*(c.getStep()-1));
            if(i>0){
                lp.setMargins(marLeft, (c.getStart()-(pre.getStart()+pre.getStep()))*(itemHeight+marTop)+marTop, 0, 0);
            }else{
                lp.setMargins(marLeft, (c.getStart()-1)*(itemHeight+marTop)+marTop, 0, 0);
            }
            tv.setLayoutParams(lp);
            tv.setGravity(Gravity.TOP);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setTextSize(12);
            tv.setTextColor(getResources().getColor(R.color.courseTextColor));
            tv.setText(c.getName()+"\n"+c.getRoom()+"\n"+c.getTeach());
            //tv.setBackgroundColor(getResources().getColor(R.color.classIndex));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tv.setBackground(getResources().getDrawable(R.drawable.tvb));
            }
            ll.addView(tv);
            pre=c;
        }
    }




}
