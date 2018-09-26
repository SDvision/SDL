package audioapk.com.example.android.farmertofarmer.Processes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class FarmWorldDatabase extends SQLiteOpenHelper{

    static private String DB_NAME,
            DB_TABLE_FARM = "worldfarm";

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public FarmWorldDatabase(Context context, String DB_NAME) {
        super(context, DB_NAME, null ,1);
        this.context = context;
        this.DB_NAME = DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DB_TABLE_FARM+" (_id integer primary key autoincrement ,title text ,process text, image_resource integer, land text, date text)");
        Toast.makeText(context,"Table created",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}


    /**
     * @param process process database name made with username+_id
     */
    public void insetFarm(String title,String process,int img,String land,String date){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("insert into "+DB_TABLE_FARM+" (title,process,image_resource,land,date) values ('"+title+"','"+process+"',"+img+",'"+land+"','"+date+"')");
    }


    public Cursor getAll(){
        sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("Select * from "+DB_TABLE_FARM,null);
    }

    public boolean Checkiffarmisfinished(String fieldValue) {

        sqLiteDatabase = getReadableDatabase();
        String Query = "Select * from " + DB_TABLE_FARM + " where process = " + fieldValue;
        Cursor cursor = sqLiteDatabase.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


}
