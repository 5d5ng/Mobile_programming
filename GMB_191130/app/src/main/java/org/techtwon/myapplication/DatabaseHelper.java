package org.techtwon.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String MY_CARDB_NAME = "db_name";
    public static String  TableName = "client";
    public static int VERSION = 1 ;

    public DatabaseHelper (Context context){
        super(context,MY_CARDB_NAME,null,VERSION);
    }

    private static volatile DatabaseHelper dbHelper; //싱글톤


   public static DatabaseHelper getInstance(Context context) {
        if (dbHelper == null) {
            synchronized (DatabaseHelper.class) {
                if (dbHelper == null) {
                    dbHelper = new DatabaseHelper(context);
                }
            }
        }

        return dbHelper;

    }


    public void onCreate(SQLiteDatabase db){

        println("oncreate 호출됨");
        String sql = "create table if not exists " + TableName+"("
                +"_id integer PRIMARY KEY autoincrement, "
                +" id text unique, "
                +" pw text,"
                +"name text,"
                +"phone text)";
        db.execSQL(sql);
    }



    public void println (String data){
        Log.d("DBHelper",data);
    }


@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
    println("upgrade 호출됨");
    db.execSQL("DROP TABLE IF EXISTS " + TableName);

    onCreate(db);

}

}
