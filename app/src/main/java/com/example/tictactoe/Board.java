package com.example.tictactoe;

import java.util.Arrays;
import java.util.Vector;

public class Board {

    int[][] board;
    Vector lastPlayedX,lastPlayedY;  //to allow undo
    Vector validX,validY;
    int initialTurn=1,turn;

    public int getTurn() {
        return turn;
    }

    public void getValidMoves(){
        validX.clear();
        validY.clear();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
                if(play(i,j)==true) {
                    validX.add(i);
                    validY.add(j);
                    undo();
                }
            }
    }

    public Vector getValidX() {
        return validX;
    }

    public Vector getValidY() {
        return validY;
    }

    public Board() {
        board = new int[3][3];
        for(int i=0;i<3;i++)
            Arrays.fill(board[i],0);
        lastPlayedX = new Vector<Integer>();
        lastPlayedY = new Vector<Integer>();
        validX = new Vector<Integer>();
        validY = new Vector<Integer>();
        turn = initialTurn;
    }
    public int getBoard(int r,int c){
        return board[r][c];
    }

    public void setInitialTurn(int initialTurn) {  //1 represents player and -1 for comp
        this.initialTurn = initialTurn;
    }

    private void flipturn(){
        turn*=-1;
    }

    public void undo(){
        if (lastPlayedX.size()!=0&&lastPlayedY.size()!=0) {  //undo if these is a move to undo
            board[(int) lastPlayedX.lastElement()][(int) lastPlayedY.lastElement()] = 0;
            lastPlayedX.remove(lastPlayedX.size() - 1);
            lastPlayedY.remove(lastPlayedY.size() - 1);
            flipturn();
        }
    }

    public void reset(){
        for(int i=0;i<3;i++)
            Arrays.fill(board[i],0);
        turn = initialTurn;
        lastPlayedY.clear();
        lastPlayedY.clear();
        validX.clear();
        validY.clear();
    }

    public int winner(){  //will return 2 for draw 1 for user wins, -1 for comp wins and 0 for nothing
        getValidMoves();
        if(check(1)!=0) return 1;
        else if(check(-1)!=0) return -1;
        else if(validX.size()==0) return 2;
        else return 0;
    }

    public int check(int player) {  //returns winning state
        //vertical checks returns 1,2,3 for col 1,2,3 respectively
        // and
        //horizontal check returns 4,5,6 for row 1,2,3 respectively
        for(int i=0;i<3;i++){
            if(board[0][i]+board[1][i]+board[2][i]==3*player) return i+1;
            if(board[i][0]+board[i][1]+board[i][2]==3*player) return i+4;
        }
        //diagonal checks returns 7,8 for right and left diagonal respectively
        if(board[0][0]+board[1][1]+board[2][2]==3*player) return 7;
        else if(board[0][2]+board[1][1]+board[2][0]==3*player) return 8;

        //returns 0 for nothing
        return 0;
    }

    public boolean play(int r,int c){
        if(board[r][c]==0) {  //checks if board is blank at given spot
            board[r][c]=turn;  //then plays
            flipturn();
            lastPlayedX.add(r);
            lastPlayedY.add(c);
            return true;
        }
        else return false;  //returns false for unplayable
    }
}
