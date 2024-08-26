package com.example.projectandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class createDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_user.db";
    public static final String TABLE_NAME = "user";
    public static final String COL_1 = "id";
    public static final String COL_2 = "user";
    public static final String COL_3 = "psw";

    private static final String TAG = "DatabaseHelper";

    public createDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d(TAG, "Cơ sở dữ liệu đã được khởi tạo.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_2 + " TEXT, " + COL_3 + " TEXT)");
            Log.d(TAG, "Tạo bảng thành công");
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi tạo bảng: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
            Log.d(TAG, "Nâng cấp bảng thành công");
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi nâng cấp bảng: " + e.getMessage());
        }
    }
}