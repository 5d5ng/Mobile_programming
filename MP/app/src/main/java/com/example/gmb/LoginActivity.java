package com.example.gmb;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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


        ID = findViewById(R.id.EditText1);
        PW = findViewById(R.id.EditText2);

        //로그인 버튼 회원가입 버튼
        Login = (ImageButton)findViewById(R.id.imageButton2);
        SignUp = (ImageButton)findViewById(R.id.imageButton3);

        //로그인 버튼 눌렀을 경우
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputID = ID.getText().toString();
                inputPW = PW.getText().toString();
                if(inputID.equals("user") && inputPW.equals("user"))
                Toast.makeText(getApplicationContext(),inputID+"님 로그인 성공",Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_LONG).show();
            }
        });
//        회원가입 버튼을 눌렀을 경우
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"회원가입화면으로 이동",Toast.LENGTH_LONG).show();

            }
        });

    }


}
