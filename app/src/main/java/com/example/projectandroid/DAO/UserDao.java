package com.example.projectandroid.DAO;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projectandroid.DAL.User;
import com.example.projectandroid.database.createDatabase;


public class UserDao {

    private static final String TAG = "UserDao";
    private createDatabase dbHelper;

    public UserDao(createDatabase dbHelper) {
        this.dbHelper = dbHelper;
    }

    public boolean insertUser(User user) {
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(createDatabase.COL_2, user.getUsername());
            contentValues.put(createDatabase.COL_3, user.getPassword());
            long result = db.insert(createDatabase.TABLE_NAME, null, contentValues);
            return result != -1;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi chèn dữ liệu: " + e.getMessage());
            return false;
        }
    }

    public boolean checkUser(String username, String password) {
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + createDatabase.TABLE_NAME + " WHERE " +
                            createDatabase.COL_2 + " = ? AND " + createDatabase.COL_3 + " = ?",
                    new String[]{username, password});
            boolean exists = cursor.getCount() > 0;
            cursor.close();
            return exists;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi kiểm tra người dùng: " + e.getMessage());
            return false;
        }
    }
}
