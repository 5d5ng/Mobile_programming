package org.techtwon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ImageButton Home,Locate,Menu;
    ImageButton Login,SignUp;
    EditText ID,PW;
    String  inputID,inputPW ,checkMessage;
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
                String strEmail = ID.getText().toString();
                String strPassword = password.getText().toString();
                if(strEmail.equals("user") && strPassword.equals("user")) { //기본 아이디 비밀번호를 user로 설정
                    Toast.makeText(getApplicationContext(), strEmail+"님 로그인 성공!", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(LoginActivity.this,MenuActivity.class);
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
            case R.id.menu2:
                Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
                ActivityCompat.finishAffinity(this);
                System.runFinalizersOnExit(true);
                System.exit(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
