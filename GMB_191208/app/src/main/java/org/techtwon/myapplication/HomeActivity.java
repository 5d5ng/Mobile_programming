package org.techtwon.myapplication;

import android.content.Intent;
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

public class HomeActivity extends AppCompatActivity {
    EditText Local,DropDay,PickDay,HwmanyLug;
    int start = -1,end = -2;
    String L ;
    String DropD ;
    String PickD;
    String Hmany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //상단 버튼 3개 인텐트
        //홈
        ImageButton homeButton = (ImageButton)findViewById(R.id.homeButton); // 홈 화면으로 이동
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Main Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(myintent);
            }
        });

        //지도
        ImageButton locwtButton = (ImageButton)findViewById(R.id.locwtButton); // 지도 화면으로 이동
        locwtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Map Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(HomeActivity.this,MapActivity.class);
                startActivity(myintent);
            }
        });

        //메뉴
        ImageButton menuwtButton = (ImageButton)findViewById(R.id.menuwtButton); // (미로그인시)로그인 화면으로 이동, (로그인된 경우)메뉴화면으로 이동-(미구현)
        menuwtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DatabaseHelper.isLogin) {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(HomeActivity.this, MenuActivity.class);
                    startActivity(myintent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(myintent);
                }
            }
        });

        Local = findViewById(R.id.TextView);
        DropDay = findViewById(R.id.drop_editText);
        PickDay = findViewById(R.id.pick_editText);
        HwmanyLug = findViewById(R.id.day_editText);











        ImageButton Search = (ImageButton)findViewById(R.id.searchbut);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L = Local.getText().toString();
                DropD = DropDay.getText().toString();
                PickD = PickDay.getText().toString();
                Hmany = HwmanyLug.getText().toString();
                String storeName = "레오,밍키의 물품 보관소";
                String storePnum = "010-7329-0313";
                DatabaseHelper.tempReserve = new Store(R.drawable.txtbox,storeName,L,storePnum,DropD,PickD,Hmany);

                if(!DatabaseHelper.isLogin) {
                    Toast.makeText(getApplicationContext(), "로그인 후 시도하세요", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(myintent);
                }
                else {
                    if (L.length()>0 && Hmany.length()>0 && DropD.length() == 8 && PickD.length() == 8 && Integer.parseInt(DropD) <= Integer.parseInt(PickD)) {
                        Toast.makeText(getApplicationContext(), "예약가능 한 곳", Toast.LENGTH_LONG).show();
                        DatabaseHelper.filledDate = true;
                        Intent myintent = new Intent(HomeActivity.this, MapActivity.class);
                        startActivity(myintent);
                    } else {
                        Toast.makeText(getApplicationContext(), "다시 입력하세요", Toast.LENGTH_LONG).show();
                        Intent myintent = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(myintent);
                    }
                }

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
