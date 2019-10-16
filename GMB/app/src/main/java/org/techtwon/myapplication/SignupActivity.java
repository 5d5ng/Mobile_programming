package org.techtwon.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
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
                Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(SignupActivity.this,LoginActivity.class);

                startActivity(myintent);
                finish();
            }
        });

        //check 버튼 -> LoginActivity로 이동 하여 다시 로그인하도록 함
        ImageButton singupButton = (ImageButton)findViewById(R.id.singupButton);
        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Membership completed", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Login again", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(SignupActivity.this,LoginActivity.class);

                startActivity(myintent);
                finish();
            }
        });
    }
}
