package audioapk.com.example.android.farmertofarmer.Processes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProcessDatabase extends SQLiteOpenHelper {

    static private String DB_TABLE_PROCESS = "process";

    private SQLiteDatabase sqLiteDatabase;

    public ProcessDatabase(Context context, String DB_NAME) {
        super(context, DB_NAME, null ,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ DB_TABLE_PROCESS +" (_id integer primary key autoincrement ,title text, description text, date text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}


    public void insetFarm(String title,String description,String date){

        sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL("insert into "+ DB_TABLE_PROCESS +" (title,description,date) values ('"+title+"','"+description+"','"+date+"')");
    }

    public Cursor getAll(){
        sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("Select * from "+ DB_TABLE_PROCESS,null);
    }


}
