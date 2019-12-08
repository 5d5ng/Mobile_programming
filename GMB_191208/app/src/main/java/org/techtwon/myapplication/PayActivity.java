package org.techtwon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PayActivity extends AppCompatActivity {
    ImageButton Home,Locate,Menu;
    ImageButton Check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        //상단 버튼 3개
        Home = findViewById(R.id.homewtButton);
        Locate = findViewById(R.id.locwtButton);
        Menu = findViewById(R.id.menuwtButton);

        //결제완료버튼
        Check = findViewById(R.id.checkbut);

        //홈버튼
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(PayActivity.this, HomeActivity.class);
                startActivity(myintent);
            }
        });

        //로컬버튼
        Locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Map Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(PayActivity.this,MapActivity.class);
                startActivity(myintent);
            }
        });

        //메뉴버튼 (로그인)
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DatabaseHelper.isLogin) {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(PayActivity.this, MenuActivity.class);
                    startActivity(myintent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(PayActivity.this, LoginActivity.class);
                    startActivity(myintent);
                }
            }
        });

        //최하단 버튼
        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "결제 정상 완료", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(PayActivity.this,ReservationActivity.class);
                startActivity(myintent);
            }
        });
    }
}
