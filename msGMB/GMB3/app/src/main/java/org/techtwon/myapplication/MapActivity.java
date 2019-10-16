package org.techtwon.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //상단 버튼 3개 인텐트
        //홈
        ImageButton homeButton = (ImageButton)findViewById(R.id.homeButton); // 홈 화면으로 이동
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Main Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MapActivity.this, MainActivity.class);
                startActivity(myintent);
                finish();
            }
        });

        //지도
        ImageButton locButton = (ImageButton)findViewById(R.id.locButton); // 지도 화면으로 이동
        locButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Map Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MapActivity.this,MapActivity.class);
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
                Intent myintent = new Intent(MapActivity.this,LoginActivity.class);

                startActivity(myintent);
                finish();
            }
        });
    }
}
