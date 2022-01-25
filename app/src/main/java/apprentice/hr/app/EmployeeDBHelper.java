package apprentice.hr.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EmployeeDBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public EmployeeDBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase EDB) {
        EDB.execSQL("create Table users (username TEXT primary key, password TEXT, status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase EDB, int i, int i1) {
        EDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password, String status) {
        SQLiteDatabase EDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("status", status);
        long result = EDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase EDB = this.getWritableDatabase();
        Cursor cursor = EDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase EDB = this.getWritableDatabase();
        Cursor cursor = EDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkStatus(String status) {
        SQLiteDatabase EDB = this.getWritableDatabase();
        Cursor cursor = EDB.rawQuery("Select * from users where status = ?", new String[]{status});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}



