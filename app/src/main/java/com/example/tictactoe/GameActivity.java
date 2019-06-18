package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GameActivity extends Activity {

    Board board;
    Boolean AIPlayer;
    AI computer;
    ImageButton grid00,grid01,grid02,grid10,grid11,grid12,grid20,grid21,grid22;
    ImageView playerTurn;
    MediaPlayer playSound,undoSound,resetSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        board = new Board();
        computer = new AI(getApplicationContext());
        grid00 = findViewById(R.id.block_00);
        grid01 = findViewById(R.id.block_01);
        grid02 = findViewById(R.id.block_02);
        grid10 = findViewById(R.id.block_10);
        grid11 = findViewById(R.id.block_11);
        grid12 = findViewById(R.id.block_12);
        grid20 = findViewById(R.id.block_20);
        grid21 = findViewById(R.id.block_21);
        grid22 = findViewById(R.id.block_22);
        playerTurn = findViewById(R.id.playerTurn);
        playSound = MediaPlayer.create(this,R.raw.play);
        undoSound = MediaPlayer.create(this,R.raw.undo);
        resetSound = MediaPlayer.create(this,R.raw.reset);
        Intent in = getIntent();
        AIPlayer = (in.getIntExtra("AIPlayer",0)==1?true:false);
    }

    public void player(View view) {
        playSound.start();
        int caller = view.getId();
        switch (caller) {
            case R.id.block_00:
                board.play(0, 0);
                break;
            case R.id.block_01:
                board.play(0, 1);
                break;
            case R.id.block_02:
                board.play(0, 2);
                break;
            case R.id.block_10:
                board.play(1, 0);
                break;
            case R.id.block_11:
                board.play(1, 1);
                break;
            case R.id.block_12:
                board.play(1, 2);
                break;
            case R.id.block_20:
                board.play(2, 0);
                break;
            case R.id.block_21:
                board.play(2, 1);
                break;
            case R.id.block_22:
                board.play(2, 2);
                break;
        }
        updateGrid();
        if(board.winner()!=0){
            outToWinner();
        }
        if(AIPlayer == true && board.getTurn()==-1) {
            computer.setBoard(board);
            if (board.winner() == 0) {  //no body has won and moves exist
                int arr[] = computer.getMove();
                board.play(arr[0], arr[1]);
            }
            updateGrid();
            if (board.winner() != 0) {
                outToWinner();
            }
        }
    }

    private void outToWinner() {
        Intent outWinner = new Intent(GameActivity.this, WinnerActivity.class);
        outWinner.putExtra("winner",board.winner());  //board.winner returns -1 for comp and 1 for player and 2 for draw
        outWinner.putExtra("AIPlayer",(AIPlayer?1:0));
        startActivity(outWinner);
        finish();
    }

    private int imager(int a){
        if(a==1) return R.drawable.x;
        else if(a==-1) return R.drawable.o;
        else return R.drawable.blank;
    }

    private void updateGrid() {
        grid00.setImageDrawable(getResources().getDrawable(imager(board.getBoard(0, 0))));
        grid01.setImageDrawable(getResources().getDrawable(imager(board.getBoard(0, 1))));
        grid02.setImageDrawable(getResources().getDrawable(imager(board.getBoard(0, 2))));
        grid10.setImageDrawable(getResources().getDrawable(imager(board.getBoard(1, 0))));
        grid11.setImageDrawable(getResources().getDrawable(imager(board.getBoard(1, 1))));
        grid12.setImageDrawable(getResources().getDrawable(imager(board.getBoard(1, 2))));
        grid20.setImageDrawable(getResources().getDrawable(imager(board.getBoard(2, 0))));
        grid21.setImageDrawable(getResources().getDrawable(imager(board.getBoard(2, 1))));
        grid22.setImageDrawable(getResources().getDrawable(imager(board.getBoard(2, 2))));
        playerTurn.setImageDrawable(getResources().getDrawable(imager(board.getTurn())));
    }

    public void undo(View view) {
        undoSound.start();
        board.undo();
        if(AIPlayer==true) board.undo(); //two moves need to be undo-ed
        updateGrid();
    }

    public void reset(View view) {
        resetSound.start();
        board.reset();
        updateGrid();
    }
}
