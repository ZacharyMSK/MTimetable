package com.zjgsu.luoweiguang.mtimetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private FragmentTransaction transaction;
    private FragmentManager  manager;
    private FragmentTransaction transactiona;
    private FragmentTransaction transactionb;
    private FragmentTransaction transactionc;
    private FragmentTransaction transactiond;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=getSupportFragmentManager();//获取fragment管理者
        transaction=manager.beginTransaction();//通过管理者开启事务
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

        FragmentA fa=new FragmentA();//首先加载第一个界面
        transaction.add(R.id.line, fa);
        transaction.commit();//非常关键  这句话的意思提交  没有这句的话  是没有反应的

    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button1:
                transactiona=manager.beginTransaction();
                FragmentA fa=new FragmentA();
                transactiona.replace(R.id.line, fa);
                transactiona.commit();
                break;
            case R.id.button2:
                transactionb=manager.beginTransaction();
                FragmentB fb=new FragmentB();
                transactionb.replace(R.id.line, fb);
                transactionb.commit();
                break;
            case R.id.button3:
                transactionc=manager.beginTransaction();
                FragmentC fc=new FragmentC();
                transactionc.replace(R.id.line, fc);
                transactionc.commit();
                break;
            case R.id.button4:
                transactiond=manager.beginTransaction();
                FragmentD fd=new FragmentD();
                transactiond.replace(R.id.line, fd);
                transactiond.commit();
                break;
            case R.id.button5:
                Intent intent =new Intent(MainActivity.this,ScheduleAddActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

//depart
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
}



}
