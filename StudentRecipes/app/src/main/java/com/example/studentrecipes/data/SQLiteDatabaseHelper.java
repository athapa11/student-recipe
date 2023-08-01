package com.example.studentrecipes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 3;
    public static final String DB_NAME = "StudentRecipes.db";
    public SQLiteDatabase myDB;

    public SQLiteDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        myDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        myDB = db;
        db.execSQL("CREATE TABLE IF NOT EXISTS USER_TABLE ( USERNAME TEXT PRIMARY KEY, PASSWORD TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS SAVED_RECIPES_TABLE ( _ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, RECIPENAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS USER_TABLE");
        db.execSQL("DROP TABLE IF EXISTS SAVED_RECIPES_TABLE");
        onCreate(db);
    }

    // Add user to database
    public boolean addUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("USERNAME", username);
        cv.put("PASSWORD", password);
        long result = db.insert("USER_TABLE", null, cv);
        if(result == -1) return false;
        else return true;
    }

    // Check if username exists
    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE USERNAME=?", new String[]{username});
        if(cursor.getCount()>0){
            return false;
        }else{
            return true;
        }
    }

    // Authenticate username + password
    public Boolean authenticate(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE USERNAME=? AND PASSWORD=?", new String[]{username, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    // Save user's recipe to database
    public void saveRecipe(String username, String recipeName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("USERNAME", username);
        cv.put("RECIPENAME", recipeName);
        db.insert("SAVED_RECIPES_TABLE", null, cv);
    }
}
