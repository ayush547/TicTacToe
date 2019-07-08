package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WinnerActivity extends Activity {

    int winner;
    MediaPlayer winSound,buttonSound;
    boolean AIPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        winSound = MediaPlayer.create(this,R.raw.win);
        buttonSound = MediaPlayer.create(this,R.raw.button);
        winSound.start();
        Intent intent = getIntent();
        winner = intent.getIntExtra("winner",0);
        AIPlayer = (intent.getIntExtra("AIPlayer",0)==1?true:false);
        TextView textView = findViewById(R.id.winnerTextView);
        if(winner==2)
            textView.setText("Its a Draw :)");

        else if(winner==1){
            if(AIPlayer == false)
                textView.setText("Congrats Player 1");
            else
                textView.setText("AI Defeated!");
        }
        else{
            if(AIPlayer == false)
                textView.setText("Congrats Player 2");
            else
                textView.setText("You Lose!");
        }
    }

    public void restart(View view){
        buttonSound.start();
        Intent outToGame = new Intent(this,GameActivity.class);
        outToGame.putExtra("AIPlayer",(AIPlayer?1:0));
        startActivity(outToGame);
        finish();
    }

    public void mainMenu(View view){
        buttonSound.start();
        Intent outToMainMenu = new Intent(this,MainMenu.class);
        startActivity(outToMainMenu);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent outToMainMenu = new Intent(this,MainMenu.class);
        startActivity(outToMainMenu);
        finish();
    }
}
