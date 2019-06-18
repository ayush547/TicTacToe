package com.example.tictactoe;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class HallOfFame extends Activity {

    DatabaseManager myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);
        myDB = new DatabaseManager(getApplication());
        Cursor easy,medium,hard;
        easy = myDB.getAllData(1);
        medium = myDB.getAllData(2);
        hard = myDB.getAllData(3);
        StringBuffer buffer = new StringBuffer();
        TextView easyText = findViewById(R.id.easy);
        TextView mediumText = findViewById(R.id.medium);
        TextView hardText = findViewById(R.id.hard);

        if(easy.getCount()==0) easyText.setText("No Records");
        else {
            while (easy.moveToNext()) {
                buffer.append("ID: " + easy.getString(0) + "\t");
                buffer.append("Time: " + easy.getString(1) + "\n");
            }
            easyText.setText(buffer);
        }

        if(medium.getCount()==0) mediumText.setText("No Records");
        else {
            while (medium.moveToNext()) {
                buffer.append("ID: " + medium.getString(0) + "\t");
                buffer.append("Time: " + medium.getString(1) + "\n");
            }
            mediumText.setText(buffer);
        }

        if(hard.getCount()==0) hardText.setText("No Records");
        else {
            while (hard.moveToNext()) {
                buffer.append("ID: " + hard.getString(0) + "\t");
                buffer.append("Time: " +hard.getString(1) + "\n");
            }
            hardText.setText(buffer);
        }
    }
}
