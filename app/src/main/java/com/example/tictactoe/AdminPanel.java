package com.example.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class AdminPanel extends Activity {

    SharedPreferences sharedPreferences;
    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        buttonSound = MediaPlayer.create(this,R.raw.button);
        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.tictactoe_preferences", Context.MODE_PRIVATE);
    }

    public void save(View view) {
        buttonSound.start();
        RadioGroup difficult = findViewById(R.id.radio);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(difficult.getCheckedRadioButtonId()==R.id.easy) editor.putInt("difficulty",1);
        else if(difficult.getCheckedRadioButtonId()==R.id.medium) editor.putInt("difficulty",2);
        else if(difficult.getCheckedRadioButtonId()==R.id.hard) editor.putInt("difficulty",3);
        editor.apply();
        mainMenu(view);
    }

    public void bestTimes(View view) {
        Intent outToRecords = new Intent(this,HallOfFame.class);
        startActivity(outToRecords);
    }

    public void clearData(View view) {
        DatabaseManager myDb = new DatabaseManager(getApplication());
        myDb.clearData();
        myDb.close();
        this.deleteDatabase("BestTimes.db");
    }

    public void mainMenu(View view){
        Intent outToMainMenu = new Intent(this,MainMenu.class);
        startActivity(outToMainMenu);
        finish();
    }
}
