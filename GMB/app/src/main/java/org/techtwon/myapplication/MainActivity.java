package org.techtwon.myapplication;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

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
                alertdialog(); //대화상자형 로그인

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
    void alertdialog()

    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //대화상자 객체 생성
        LayoutInflater inflater = getLayoutInflater();  //xml파일과 연결을 위해 인플레이터 생성
        View view = inflater.inflate(R.layout.activity_log, null);
        builder.setView(view); //대화상자 나타내기
        final Button submit = (Button) view.findViewById(R.id.buttonSubmit);
        final EditText ID = (EditText) view.findViewById(R.id.edittextID);
        final EditText password = (EditText) view.findViewById(R.id.edittextPassword);

        final AlertDialog dialog = builder.create();
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strEmail = ID.getText().toString();
                String strPassword = password.getText().toString();
                if(strEmail.equals("user") && strPassword.equals("user")) { //기본 아이디 비밀번호를 user로 설정
                    Toast.makeText(getApplicationContext(), strEmail+"님 로그인 성공!", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "로그인 실패 다시시도해주세요!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();

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

            case R.id.menu2:
                Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
                ActivityCompat.finishAffinity(this);
                System.runFinalizersOnExit(true);
                System.exit(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


