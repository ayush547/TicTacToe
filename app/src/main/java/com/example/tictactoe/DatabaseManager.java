package com.example.tictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context) {
        super(context,"BestTimes.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table easy(ID integer primary key autoincrement,Time Integer)");
        db.execSQL("create table medium(ID integer primary key autoincrement,Time Integer)");
        db.execSQL("create table hard(ID integer primary key autoincrement, Time Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists easy");
        db.execSQL("drop table if exists medium");
        db.execSQL("drop table if exists hard");
        onCreate(db);
    }

    public boolean writeData(int mode,long time){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Time",time);
        long result=-1;
        if(mode==1) result = db.insert("easy",null,contentValues);
        if(mode==2) result = db.insert("medium",null,contentValues);
        if(mode==3) result = db.insert("hard",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(int mode){
        SQLiteDatabase db = getWritableDatabase();
        Cursor result;
        if(mode==1)result = db.rawQuery("select * from easy",null);
        else if(mode==2)  result = db.rawQuery("select * from medium",null);
        else result = db.rawQuery("select * from hard",null);
        return result;
    }

    public void clearData() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from easy");
        db.execSQL("delete from medium");
        db.execSQL("delete from hard");
    }
}
