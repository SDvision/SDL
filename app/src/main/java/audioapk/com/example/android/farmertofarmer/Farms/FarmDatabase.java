package audioapk.com.example.android.farmertofarmer.Farms;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import audioapk.com.example.android.farmertofarmer.LogIn.LogIn;

import static android.content.Context.MODE_PRIVATE;

public class FarmDatabase extends SQLiteOpenHelper{

    static private String DB_TABLE_FARM = "farm";

    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public FarmDatabase(Context context, String DB_NAME) {
        super(context, DB_NAME, null ,1);
        this.context = context;
    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table "+DB_TABLE_FARM+" (_id integer primary key autoincrement ,title text, image_resource integer, land text, date text)");
//    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DB_TABLE_FARM+" (_id integer primary key autoincrement ,title text, image_resource integer, land text, date text,location text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}


    public int insetFarm(String title,int img,String land,String date){

        sqLiteDatabase = getWritableDatabase();
        String location = context.getSharedPreferences(LogIn.SHARED_FILE,MODE_PRIVATE).getString(LogIn.DISTRICT,"notFound");
        sqLiteDatabase.execSQL("insert into "+DB_TABLE_FARM+" (title,image_resource,land,date,location) values ('"+title+"',"+img+",'"+land+"','"+date+"','"+location+"')");
        Cursor cursor = getAll();
        cursor.moveToLast();
        int result =  cursor.getInt(0);
        cursor.close();
        return result;

    }

    public Cursor getAll(){
        sqLiteDatabase = getReadableDatabase();
        String location = context.getSharedPreferences(LogIn.SHARED_FILE,MODE_PRIVATE).getString(LogIn.DISTRICT,"notFound");
        return sqLiteDatabase.rawQuery("Select * from "+DB_TABLE_FARM +" where location = '"+location+"'",null);
    }


}
