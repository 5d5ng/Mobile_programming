package org.techtwon.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String MY_CARDB_NAME = "db_name";
    public static String ClientTableName = "client";
    public static String ReservedTableName = "Reserved";
    public static String StoreTableName = "Store";


    public static Store tempReserve ;
    public static ArrayList<Store> ReserveList = new ArrayList<Store>();
    public static int VERSION = 1 ;
    public static boolean isLogin =false;
    public static boolean PickedStore = false;
    public static boolean filledDate = false;

    public static String nowID = "";

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
        String sql = "create table if not exists " + ClientTableName +"("
                +" id nvarchar(30) PRIMARY KEY, "
                +" pw nvarchar(30),"
                +"name nvarchar(30),"
                +"phone nvarchar(30))";

        String sql2 = "create table if not exists " + ReservedTableName +"("
//                +"_id integer PRIMARY KEY autoincrement, "
                +" id text , "
                +" hostname text,"
                +" address text,"
                +" phone text,"
                +" startDate text,"
                +"endDate text)";

        String sql3 = "create table if not exists " + StoreTableName +"("
//                +"_id integer PRIMARY KEY autoincrement, "
                +" hostname text , "
                +" fee text,"
                +" address text,"
                +" phone text,"
                +" startDate text,"
                +"endDate text)";

        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);

    }



    public void println (String data){
        Log.d("DBHelper",data);
    }


@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
    println("upgrade 호출됨");
    db.execSQL("DROP TABLE IF EXISTS " + ClientTableName);
    db.execSQL("DROP TABLE IF EXISTS " + ReservedTableName);
    db.execSQL("DROP TABLE IF EXISTS " + StoreTableName);
    onCreate(db);

}

public void insertStore(){

}



}
class Store { //자바빈
    int img; // 이미지
    String name = ""; //가게이름
    String local = "";//가게 위치
    String pnum = ""; //가게전화번호
    String resdata = ""; //예약날짜
    String finddata = ""; //찾는날짜
    String lugcnt; //캐리어 수

    public Store(int img, String name, String local, String pnum, String resdata, String finddata, String lugcnt) {
        this.img = img;
        this.name = name;
        this.local = local;
        this.pnum = pnum;
        this.resdata = resdata;
        this.finddata = finddata;
        this.lugcnt = lugcnt;
    }
    public Store() {} // 생성자
}
