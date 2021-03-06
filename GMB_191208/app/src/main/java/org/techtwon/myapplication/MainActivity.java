package org.techtwon.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //재생스위치
        final MediaPlayer musicPlayer ;
        musicPlayer = MediaPlayer.create(this,R.raw.background);
        final Switch myswitch = (Switch) findViewById(R.id.switch1);
        myswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myswitch.isChecked() ==  true){
                    musicPlayer.start();
                }
                else{
                    musicPlayer.stop();
                }
            }
        });



        ImageButton homewtButton = (ImageButton)findViewById(R.id.homewtButton); // 홈 화면으로 이동
        homewtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(myintent);
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
            }
        });

        //메뉴
        ImageButton menuwtButton = (ImageButton)findViewById(R.id.menuwtButton); // (미로그인시)로그인 화면으로 이동, (로그인된 경우)메뉴화면으로 이동
        menuwtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(DatabaseHelper.isLogin) {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(myintent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(myintent);
                }
            }
        });

        //큰 화면
        Button newpage = (Button)findViewById(R.id.newpage); //메인화면 Tap시 HomeActivity로 이동
        newpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Next Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MainActivity.this,HomeActivity.class);
                TextView textView2 = (TextView)findViewById(R.id.textView2);
                textView2.setText("Touch event handled");
                startActivity(myintent);


            }
        });

        TextView GMB = (TextView)findViewById(R.id.GMB);
        GMB.setSelected(true);





    }

    //옵션메뉴뉴
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
            case R.id.menuVideo:
                Intent myintent = new Intent(this, VideoActivity.class);
                startActivity(myintent);
        }
        return super.onOptionsItemSelected(item);
    }
}


