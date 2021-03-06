package org.techtwon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    ImageButton Home,Locate,Menu;
    ImageButton Logout, Reservation, Pay, Help;
    TextView cusID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //상단 버튼 3개
        Home = findViewById(R.id.homewtButton);
        Locate = findViewById(R.id.locwtButton);
        Menu = findViewById(R.id.menuButton);

        //홈버튼
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MenuActivity.this, HomeActivity.class);
                startActivity(myintent);
            }
        });

        //로컬버튼
        Locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Map Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MenuActivity.this,MapActivity.class);
                startActivity(myintent);
            }
        });

        //메뉴버튼 (로그인)
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DatabaseHelper.isLogin) {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(MenuActivity.this, MenuActivity.class);
                    startActivity(myintent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(MenuActivity.this, LoginActivity.class);
                    startActivity(myintent);
                }
            }
        });


//        Intent intent = getIntent(); //get ID from LoginActivity
//        String id = intent.getExtras().getString("ID");
        String id = DatabaseHelper.nowID;
        cusID = findViewById(R.id.TextView);
        cusID.setText(id); // set Text view to ID


        //메뉴 버튼 4개
        Logout =  findViewById(R.id.logoutbut);
        Reservation = findViewById(R.id.reservationbut);
        Pay = findViewById(R.id.paybut);
        Help = findViewById(R.id.helpbut);

        //로그아웃 버튼
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MenuActivity.this,LoginActivity.class);
                DatabaseHelper.isLogin = false;
                startActivity(myintent);
            }
        });

        //예약내역조회 버튼
        Reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MenuActivity.this, ReservationActivity.class);
                startActivity(myintent);
            }
        });

        //결제버튼
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MenuActivity.this, PayActivity.class);
                startActivity(myintent);
            }
        });

        //도움버튼
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MenuActivity.this, HelpActivity.class); //이동페이지 수정 필요
                startActivity(myintent);
            }
        });
    }
}
