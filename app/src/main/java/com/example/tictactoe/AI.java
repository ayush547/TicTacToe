package com.example.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
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
        int arr[] = new int[2];
        Vector x,y,value;
        x=new Vector<Integer>();
        y=new Vector<Integer>();
        value=new Vector<Integer>();

        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board.play(i, j) == true) {
                    value.add(minimax(board, 0, true));
                    x.add(i);
                    y.add(j);
                    board.undo();
                }
            }
        }
        int index = value.indexOf(Collections.max(value));
        arr[0] = (int)x.get(index);
        arr[1] = (int)y.get(index);
        return arr;
    }

    private int minimax(Board node,int depth,boolean isMaximising){
        if(node.winner()!=0){ //is a terminal node
            if(node.winner()==-1) return 10-depth;  //winner is AI
            else if(node.winner()==1) return depth-10;  //winner is Player
            else return 0;
        }
        else{
            Vector values = new Vector<Integer>();
            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++){
                    if(node.play(i,j)==false) continue;
                    else{
                        values.add(minimax(node,depth+1,(isMaximising?false:true)));
                    }
                    node.undo();
                }
            if(isMaximising){
                int max = (int)Collections.max(values),index;
                return max;
            }
            else {
                int min = (int)Collections.min(values),index;
                return min;
            }
        }
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
        int arr[] = new int[2];
        Vector x,y,value;
        x=new Vector<Integer>();
        y=new Vector<Integer>();
        value=new Vector<Integer>();

        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (board.play(i, j) == true) {
                    value.add(minimax(board, 0, true));
                    x.add(i);
                    y.add(j);
                    board.undo();
                }
            }
        }
        int index = value.indexOf(Collections.min(value));
        arr[0] = (int)x.get(index);
        arr[1] = (int)y.get(index);
        return arr;
    }
}
