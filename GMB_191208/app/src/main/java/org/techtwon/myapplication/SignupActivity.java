package org.techtwon.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    EditText edtID, edtPW, edtName, edtPnum;
    ArrayList<String> idList = new ArrayList<String>();
    ArrayList<String> pwList = new ArrayList<String>();
    String tablename="client";
    DatabaseHelper dbHelper ;
    SQLiteDatabase database ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dbHelper = DatabaseHelper.getInstance(getApplicationContext());
        database = dbHelper.getWritableDatabase();
        dbHelper.onCreate(database); // 테이블 생성
     //   dbHelper.onUpgrade(database,2,3);//테이블 갈아엎을때 사용
        //상단 버튼 3개 인텐트
        //홈
        ImageButton homewtButton = (ImageButton)findViewById(R.id.homewtButton); // 홈 화면으로 이동
        homewtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Main Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(myintent);
                finish();
            }
        });

        //지도
        ImageButton locwtButton = (ImageButton)findViewById(R.id.locwtButton); // 지도 화면으로 이동
        locwtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Map Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(SignupActivity.this,MapActivity.class);
                startActivity(myintent);
                finish();
            }
        });

        //메뉴
        ImageButton menuwtButton = (ImageButton)findViewById(R.id.menuwtButton); // (미로그인시)로그인 화면으로 이동, (로그인된 경우)메뉴화면으로 이동
        menuwtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DatabaseHelper.isLogin) {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(SignupActivity.this, MenuActivity.class);
                    startActivity(myintent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(myintent);
                }
                finish();
            }
        });

        //database 작업

        edtID = (EditText) findViewById(R.id.id_editText) ;
        edtPW = (EditText) findViewById(R.id.pw_editText);
       edtName = (EditText)findViewById(R.id.name_editText);
       edtPnum = (EditText)findViewById(R.id.phone_editText);

        //check 버튼 -> LoginActivity로 이동 하여 다시 로그인하도록 함
        ImageButton singupButton = (ImageButton)findViewById(R.id.singupButton);
        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent;
            String id = edtID.getText().toString();
            String pw = edtPW.getText().toString();
            String name = edtName.getText().toString();
            String pnum = edtPnum.getText().toString();
                executeQueary();
                if(!hasSameID(idList,id)) { //동일한 아이디가 없는 경우
                    insertRecord(id, pw, name, pnum);
                    Toast.makeText(getApplicationContext(), id+"님 !회원가입된 아이디,비밀번호로 로그인 해주세요", Toast.LENGTH_LONG).show();
                     myintent = new Intent(SignupActivity.this,LoginActivity.class);
                }
                else {
                    Toast.makeText(getApplicationContext(), name+"님 동일한 아이디가 존재합니다. 다시 시도해주세요", Toast.LENGTH_LONG).show();
                    myintent = new Intent(SignupActivity.this,SignupActivity.class);
                }

                startActivity(myintent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.menu1:
                Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
                ActivityCompat.finishAffinity(this);
                System.runFinalizersOnExit(true);
                System.exit(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private  void insertRecord(String id,String pw,String name, String pnum){
        println("insertRecord 호출");
        if(database == null){
            println("데이터를 먼저 생성하라");
            return;
        }
        if(tablename == null){
            println("테이블을 먼저 생성하라");
            return;
        }
        String sql="insert into "+tablename+"(id,pw,name,phone)"
                +"values"
                +"('"+ id +"' ,'" + pw+"' , '"+name+"' , '"+pnum+"')";
        database.execSQL(sql);

        println("레코드 추가");
    }
    public void executeQueary(){
        println("execute Queary 호출됨");
        Cursor cursor = database.rawQuery("select * from  client",null);
        int recordSize = cursor.getCount();
        println("레코드 수"+recordSize);

        for(int i=0;i < recordSize ;i++){
            cursor.moveToNext();
            String temp_id = cursor.getString(1);
            String temp_pw = cursor.getString(2);
            println("ID:"+temp_id+" 비번:"+temp_pw);
            idList.add(temp_id);
            pwList.add(temp_pw);

        }
        cursor.close();
    }
    public void println (String data){
        Log.d("DBHelper",data);
    }

    private boolean hasSameID(ArrayList<String> idList, String inputID ){
        for(String i:idList){
            if(inputID.equals(i)) {
               return  true;
            }
        }
        return false;
    }

}
