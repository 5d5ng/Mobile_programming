package org.techtwon.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
                //Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                //Intent myintent = new Intent(MainActivity.this,LoginActivity.class);

                //startActivity(myintent);
                //finish();
                showAlertDialog();
            }
        });

        Button newpage = (Button)findViewById(R.id.newpage); //메인화면 Tap시 HomeActivity로 이동
        newpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Next Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MainActivity.this,HomeActivity.class);
                TextView textView2 = (TextView)findViewById(R.id.textView2);
                textView2.setText("Touch event handled");
                startActivity(myintent);
                finish();
            }
        });

        TextView GMB = (TextView)findViewById(R.id.GMB);
        GMB.setSelected(true);
    }

    void showAlertDialog() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout =
                (LinearLayout) vi.inflate(R.layout.activity_log, null);

        final EditText id = (EditText) loginLayout.findViewById(R.id.EditText1);
        final EditText pw = (EditText) loginLayout.findViewById(R.id.EditText2);
        final ImageButton login = (ImageButton)findViewById(R.id.imageButton2);

        new AlertDialog.Builder(this)
                //.setTitle("로그인")
                .setView(loginLayout)
                .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                MainActivity.this,
                                "ID : " + id.getText().toString() +
                                        "\nPW : " + pw.getText().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }).show();
    }

}
