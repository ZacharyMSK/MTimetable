package com.zjgsu.luoweiguang.mtimetable;

/**
 * Created by Zachary on 2017/5/31.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zjgsu.luoweiguang.mtimetable.com.zjgsu.luoweiguang.db.MyDatabaseHelper;

public class FragmentD extends Fragment {
    //depart-db
    private MyDatabaseHelper dbHelper;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout4, null);
        Button createDatabase =(Button) view.findViewById(R.id.create_db);
        dbHelper =new MyDatabaseHelper(getContext(),"Schedule.db",null,1);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });
        Button deleteDatabase =(Button) view.findViewById(R.id.delete_button);
        try {
            deleteDatabase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHelper.getWritableDatabase();
                    dbHelper.onUpgrade(dbHelper.getWritableDatabase(),1,2 );
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Button exitButton =(Button) view.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        return view;

    }
}