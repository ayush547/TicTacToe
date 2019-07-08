package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends Activity {

    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        buttonSound = MediaPlayer.create(this,R.raw.button);
    }

    public void startGame(View view) {
        buttonSound.start();
        Intent startGame = new Intent(this, GameActivity.class);

        boolean AIPlayer;
        if(view.getId()==R.id.singlePlayer) AIPlayer = true;
        else AIPlayer = false;

        startGame.putExtra("AIPlayer",(AIPlayer?1:0));
        startActivity(startGame);
        finish();
    }

    public void adminPanel(View view) {
        buttonSound.start();
        Intent outToAdmin = new Intent(this,AdminPanel.class);
        startActivity(outToAdmin);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAndRemoveTask();
    }
}
