package org.techtwon.myapplication;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                Intent myintent = new Intent(MapActivity.this, HomeActivity.class);
                startActivity(myintent);
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
            }
        });

        //메뉴
        ImageButton menuwtButton = (ImageButton)findViewById(R.id.menuwtButton); // (미로그인시)로그인 화면으로 이동, (로그인된 경우)메뉴화면으로 이동
        menuwtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DatabaseHelper.isLogin) {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(MapActivity.this, MenuActivity.class);
                    startActivity(myintent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(MapActivity.this, LoginActivity.class);
                    startActivity(myintent);
                }
            }
        });

        //결제 진행 버튼, 로그인 된경우와 안된경우 구분 하여 진행
        ImageButton Pay = (ImageButton)findViewById(R.id.rpaybut);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Pay Page", Toast.LENGTH_LONG).show();
                //Intent myintent = new Intent(MapActivity.this,PayActivity.class); //로그인 된 경우
                Toast.makeText(getApplicationContext(), "로그인 후 가능합니다", Toast.LENGTH_LONG).show(); //로그인 안된경우
                Intent myintent = new Intent(MapActivity.this,LoginActivity.class);
                startActivity(myintent);
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
}
