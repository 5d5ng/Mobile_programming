package org.techtwon.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PayActivity extends AppCompatActivity {
    ImageButton Home,Locate,Menu;
    ImageButton Check;
    EditText Cnum,CValid,CPW,LugCnt;
     String cardN;
    String cardV;
    String cardPW;
    String NumberOfLug ;

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

        Cnum  = findViewById(R.id.card_editText);
        CValid = findViewById(R.id.validity_editText);
        CPW = findViewById(R.id.Cpw_editText);
        LugCnt = findViewById(R.id.lug_editText);




        //최하단 버튼
        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardN = Cnum.getText().toString();
                cardV = CValid.getText().toString();
                cardPW = CPW.getText().toString();
                NumberOfLug = LugCnt.getText().toString();


                if(chkValidCard()){
                    println("chk true");
                }
                else
                {
                    println("chk false"+ cardN+" "+ cardV +" "+ cardPW.length()  +" "+  NumberOfLug.length());
                }

                if(DatabaseHelper.filledDate&& chkValidCard() && DatabaseHelper.PickedStore) {
                    Toast.makeText(getApplicationContext(), "결제완료", Toast.LENGTH_LONG).show();
                    DatabaseHelper.ReserveList.add(DatabaseHelper.tempReserve);
                    DatabaseHelper.PickedStore = false;
                    DatabaseHelper.filledDate = false;
                    Intent myintent = new Intent(PayActivity.this, MenuActivity.class);
                    startActivity(myintent);
                }
                else if(DatabaseHelper.isLogin && !DatabaseHelper.filledDate){
                    Toast.makeText(getApplicationContext(), "물품을 맡길 장소를 먼저 선택하세요", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(PayActivity.this, HomeActivity.class);
                    startActivity(myintent);
                }
                else if(DatabaseHelper.isLogin && !DatabaseHelper.PickedStore){
                    Toast.makeText(getApplicationContext(), "물품을 맡길 장소를 먼저 선택하세요", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(PayActivity.this, HomeActivity.class);
                    startActivity(myintent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "다시 시도해주세요", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(PayActivity.this, LoginActivity.class);
                    startActivity(myintent);
                }
            }
        });
    }
    private  boolean chkValidCard(){
        if(cardN.length()>0 && cardV.length() >0 && cardPW.length() >0 && NumberOfLug.length()>0) {

            return true;
        }
        return false;

    }
    public void println (String data){
        Log.d("DBHelper",data);
    }
}
