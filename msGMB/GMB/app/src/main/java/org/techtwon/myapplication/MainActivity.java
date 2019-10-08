package org.techtwon.myapplication;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //상단 버튼 3개 인텐트
        //홈
        ImageButton homewtButton = (ImageButton)findViewById(R.id.homewtButton); // 홈 화면으로 이동
        homewtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MainActivity.this, HomeActivity.class);
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
                Intent myintent = new Intent(MainActivity.this,MapActivity.class);
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
                Intent myintent = new Intent(MainActivity.this,LoginActivity.class);

                startActivity(myintent);
                finish();
            }
        });

        Button newpage = (Button)findViewById(R.id.newpage); //메인화면 Tap시 HomeActivity로 이동
        newpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Next Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MainActivity.this,HomeActivity.class);

                startActivity(myintent);
                finish();
            }
        });

        TextView GMB = (TextView)findViewById(R.id.GMB);
        GMB.setSelected(true);
    }

}
