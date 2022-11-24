package com.example.lifestyle_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USER_RECORD";

    // Table to store user data
    private static final String TABLE_NAME = "USER_DATA";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PASSWORD";

    // Table to Breaks data
    private static final String BREAKS_TABLE = "BREAKS_TABLE";
    private static final String BREAK_ID = "BREAK_ID";
    private static final String BREAK_NAME = "BREAK_NAME";
    private static final String BREAK_DATE = "BREAK_DATE";
    private static final String BREAK_TIME = "BREAK_TIME";
    private final Context context;


    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME , null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , USERNAME TEXT , EMAIL TEXT , PASSWORD TEXT )");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+BREAKS_TABLE + "(BREAK_ID INTEGER PRIMARY KEY AUTOINCREMENT , BREAK_NAME TEXT , BREAK_DATE TEXT , BREAK_TIME TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + BREAKS_TABLE);
        onCreate(db);
    }

    public boolean registerUser(String username , String email , String password){

        SQLiteDatabase db = this.getWritableDatabase();
        password = md5(password);
        ContentValues values = new ContentValues();
        values.put(COL_2 , username);
        values.put(COL_3 , email);
        values.put(COL_4 , password);
        Cursor c = db.rawQuery("SELECT * FROM USER_DATA where email= '"+email +"'", null);
            if(c.getCount()>0)
            {
                return false;
            }
            else{
                long result = db.insert(TABLE_NAME , null , values);
                if(result == -1)
                    return false;
                else
                    return true;
            }
    }

    public boolean checkUser(String email , String password){

        SQLiteDatabase db = this.getWritableDatabase();
        password = md5(password);
        String [] columns = { COL_1 };
        String selection = COL_3 + "=?" + " and " + COL_4 + "=?";
        String [] selectionargs = { email , password};
        Cursor cursor = db.query(TABLE_NAME , columns , selection ,selectionargs , null , null , null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        if (count > 0)
            return true;
        else
            return false;

    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void addBreak(String breakName, String breakDate, String breakTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BREAK_NAME,breakName);
        values.put(BREAK_DATE,breakDate);
        values.put(BREAK_TIME,breakTime);

        long result = db.insert(BREAKS_TABLE, null, values);

        if(result == -1){
            Toast.makeText(context,"error while adding user", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Break added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getALlBreaksData(){
        String query = "SELECT * FROM " + BREAKS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }

}
