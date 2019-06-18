package com.example.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Random;
import java.util.Vector;

public class AI {
    Board board;
    Vector validX,validY;
    SharedPreferences sharedPreferences;
    int difficulty;

    public AI(Context context){
        board = new Board();
        validX = new Vector<Integer>();
        validY = new Vector<Integer>();
        sharedPreferences = context.getSharedPreferences("com.example.tictactoe_preferences", Context.MODE_PRIVATE);
        difficulty = sharedPreferences.getInt("difficulty",1);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int[] getMove(){
        if(difficulty==1) return easy();
        else if(difficulty==2) return medium();
        else return difficult();
    }

    private int[] difficult() {

    }

    private int[] medium(){
        Random random = new Random();
        validX = board.getValidX();
        validY = board.getValidY();
        int i = random.nextInt(validX.size());
        int arr[] = {(int)validX.get(i),(int)validY.get(i)};
        return arr;
    }

    private int[] easy(){

    }
}
