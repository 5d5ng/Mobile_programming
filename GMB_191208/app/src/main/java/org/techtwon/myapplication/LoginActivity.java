package org.techtwon.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ImageButton Home,Locate,Menu;
    ImageButton Login,SignUp;
    DatabaseHelper dbHelper ;
    SQLiteDatabase database ;
    ArrayList<String> idList = new ArrayList<String>();
    ArrayList<String> pwList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //상단 버튼 3개
        Home = findViewById(R.id.homewtButton);
        Locate = findViewById(R.id.locwtButton);
        Menu = findViewById(R.id.menuwtButton);

        //홈버튼
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(myintent);
            }
        });

        //로컬버튼
        Locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Map Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(LoginActivity.this,MapActivity.class);
                startActivity(myintent);
            }
        });

        //메뉴버튼 (미로그인)
        Menu.setOnClickListener(new View.OnClickListener() {// (미로그인시)로그인 화면으로 이동, (로그인된 경우)메뉴화면으로 이동-(미구현)
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(LoginActivity.this,LoginActivity.class);
                startActivity(myintent);
            }
        });

        //로그인 버튼 회원가입 버튼
        Login = (ImageButton)findViewById(R.id.loginbut);
        SignUp = (ImageButton)findViewById(R.id.signupbut);

        //로그인 버튼 눌렀을 경우
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialog(); //대화상자형 로그인
            }
        });

//        회원가입 버튼을 눌렀을 경우, 회원가입 페이지로 이동
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"회원가입화면으로 이동",Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(myintent);
                finish();

            }
        });

    }

    void alertdialog() //대화상자형 로그인
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //대화상자 객체 생성
        LayoutInflater inflater = getLayoutInflater();  //xml파일과 연결을 위해 인플레이터 생성
        View view = inflater.inflate(R.layout.activity_log, null);
        builder.setView(view); //대화상자 나타내기
        final Button submit = (Button) view.findViewById(R.id.buttonSubmit);
        final EditText ID = (EditText) view.findViewById(R.id.edittextID);
        final EditText password = (EditText) view.findViewById(R.id.edittextPassword);

        final AlertDialog dialog = builder.create();
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 String strID = ID.getText().toString();
                 String  strPassword = password.getText().toString();

                dbHelper = DatabaseHelper.getInstance(getApplicationContext());
                database = dbHelper.getReadableDatabase();
                executeQueary();

                if(chkIDPW(idList,pwList,strID,strPassword)) { //기본 아이디 비밀번호를 user로 설정

                    Toast.makeText(getApplicationContext(), strID+"님 로그인 성공!", Toast.LENGTH_LONG).show();
                    DatabaseHelper.isLogin = true;
                    DatabaseHelper.nowID = strID;
                   Intent myintent = new Intent(LoginActivity.this,MenuActivity.class);
                  //  myintent.putExtra("ID",strID);
                    startActivity(myintent);
                    finish();
                }

                else
                    Toast.makeText(getApplicationContext(), "로그인 실패 다시시도해주세요!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
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

    public void executeQueary(){
        println("execute Queary 호출됨");
        Cursor cursor = database.rawQuery("select * from  client",null);
        int recordSize = cursor.getCount();
        println("레코드 수"+recordSize);

        for(int i=0;i < recordSize ;i++){
            cursor.moveToNext();
            String temp_id = cursor.getString(0);
            String temp_pw = cursor.getString(1);
            println("레코드"+temp_id+" "+temp_pw);
            idList.add(temp_id);
            pwList.add(temp_pw);

        }
        cursor.close();
    }

    public void println (String data){
        Log.d("DBHelper",data);
    }
    private boolean chkIDPW(ArrayList<String> idList , ArrayList<String> pwList , String inputID , String inputPW){
        for(String i:idList){
            if(inputID.equals(i)) {
                for (String p : pwList) {
                        if(inputPW.equals(p))
                            return true;
                }
            }
        }
       return false;
    }


}
