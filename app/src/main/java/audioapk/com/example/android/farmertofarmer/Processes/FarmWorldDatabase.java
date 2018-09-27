package audioapk.com.example.android.farmertofarmer.Processes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FarmWorldDatabase extends SQLiteOpenHelper{

    final static private String DB_NAME = "farm_world_database",
            DB_TABLE_FARM = "worldfarm";

    public static final int NOT_FOUND = -275452;

    private SQLiteDatabase sqLiteDatabase;

    public FarmWorldDatabase(Context context) {
        super(context, DB_NAME, null ,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DB_TABLE_FARM+" (_id integer primary key autoincrement ,title text ,process text, image_resource integer, land text, date text, profit integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}


    /**
     * @param process process database name made with username+_id
     */
    public void insetFarm(String title,String process,int img,String land,String date,int profit){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("insert into "+DB_TABLE_FARM+" (title,process,image_resource,land,date,profit) values ('"+title+"','"+process+"',"+img+",'"+land+"','"+date+"','"+profit+"')");
    }


    public Cursor getAll(){
        sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("Select * from "+DB_TABLE_FARM,null);
    }

    public int CheckIfFarmIsFinished(String fieldValue) {

        sqLiteDatabase = getReadableDatabase();
        String Query = "Select * from " + DB_TABLE_FARM + " where process = '" + fieldValue+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(Query, null);

        if(cursor.getCount() <= 0){
            cursor.close();
            return NOT_FOUND;
        }
        cursor.moveToNext();
        int profit = cursor.getInt(6);
        cursor.close();
        return profit;
    }



    public Cursor findFarms(String title) {

        sqLiteDatabase = getReadableDatabase();
        String Query = "Select * from " + DB_TABLE_FARM + " where title = '" + title+"'";
        return sqLiteDatabase.rawQuery(Query, null);
    }

}
