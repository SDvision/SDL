package audioapk.com.example.android.farmertofarmer.LogIn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

class LogInDatabase extends SQLiteOpenHelper {

    static final String DB_TABLE_LOGIN = "login_data";
    private SQLiteDatabase sqLiteDatabase;

    LogInDatabase(@Nullable Context context) {
        super(context, "LOG_IN_2018_DATABASE_TABLE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DB_TABLE_LOGIN+" (_id integer primary key autoincrement ,username text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isLogInExist(String userName){
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + DB_TABLE_LOGIN + " where username = '" + userName +"'", null);

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        return true;
    }

    public boolean checkPassword(String userName,String password){
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + DB_TABLE_LOGIN + " where username = '" + userName +"' and password = '" + password + "'", null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        return true;
    }

//    public Cursor getAll(){
//        sqLiteDatabase = getReadableDatabase();
//        return sqLiteDatabase.rawQuery("Select * from "+DB_TABLE_LOGIN,null);
//    }

    public void insert(String userName,String password){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("insert into "+DB_TABLE_LOGIN+" (username,password) values ('"+userName+"','"+password+"')");

    }

}
