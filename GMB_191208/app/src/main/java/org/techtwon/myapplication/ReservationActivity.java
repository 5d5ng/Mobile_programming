package org.techtwon.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReservationActivity extends AppCompatActivity {
    

    ImageButton Home,Locate,Menu;
    TextView cusID;
    ScrollView scrollview;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        //상단 버튼 3개
        Home = findViewById(R.id.homewtButton);
        Locate = findViewById(R.id.locwtButton);
        Menu = findViewById(R.id.menuButton);

        //홈버튼
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(ReservationActivity.this, HomeActivity.class);
                startActivity(myintent);
            }
        });

        //로컬버튼
        Locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Map Page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(ReservationActivity.this,MapActivity.class);
                startActivity(myintent);
            }
        });

        //메뉴버튼 (로그인)
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DatabaseHelper.isLogin) {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(ReservationActivity.this, MenuActivity.class);
                    startActivity(myintent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Page", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(ReservationActivity.this, LoginActivity.class);
                    startActivity(myintent);
                }

            }
        });

//        Intent intent = getIntent(); //get ID from LoginActivity
        String id = DatabaseHelper.nowID;
        cusID = findViewById(R.id.textView_Re);
        cusID.setText(id); // set Text view to ID


//        //스크롤뷰
//        scrollview = (ScrollView)findViewById(R.id.scrollview);
//        listview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                scrollview.requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });

        DatabaseHelper.ReserveList.add(new Store(R.drawable.txtbox,"덩이의 물품 보관소","용소로 19번길","010-8524-1451","2019.12.01 ","2019.12.05", "1"));
        DatabaseHelper.ReserveList.add(new Store(R.drawable.txtbox,"MUX의 물품 보관소","용소로 45","010-2803-7944","2019.11.30 ","2019.12.03", "3"));
        DatabaseHelper.ReserveList.add(new Store(R.drawable.txtbox,"덩이의 물품 보관소","용소로 19번길","010-8524-1451","2019.12.15 ","2019.12.16", "1"));
        DatabaseHelper.ReserveList.add(new Store(R.drawable.txtbox,"레오밍키의 물품 보관소","목화로 474번길","010-7329-0313","2019.12.24 ","2019.12.26", "2"));
        DatabaseHelper.ReserveList.add(new Store(R.drawable.txtbox,"덩이의 물품 보관소","용소로 19번길","010-8524-1451","2019.12.01 ","2019.12.05", "1"));
        DatabaseHelper.ReserveList.add(new Store(R.drawable.txtbox,"MUX의 물품 보관소","용소로 45","010-2803-7944","2019.11.30 ","2019.12.03", "3"));
        DatabaseHelper.ReserveList.add(new Store(R.drawable.txtbox,"덩이의 물품 보관소","용소로 19번길","010-8524-1451","2019.12.15 ","2019.12.16", "1"));
        DatabaseHelper.ReserveList.add(new Store(R.drawable.txtbox,"레오밍키의 물품 보관소","목화로 474번길","010-7329-0313","2019.12.24 ","2019.12.26", "2"));

        // adapter
        MyAdapter adapter = new MyAdapter(
                getApplicationContext(), // 현재화면의 제어권자
                R.layout.activity_list,DatabaseHelper.ReserveList);

        // adapterView - ListView, GridView
        ListView lv = (ListView)findViewById(R.id.listview);
        lv.setAdapter(adapter);
    }

    

    class MyAdapter extends BaseAdapter {
        Context context;
        int layout;
//        ArrayList<Store> al;
        LayoutInflater inf;
        public MyAdapter(Context context, int layout,ArrayList<Store> al) {
            this.context = context;
            this.layout = layout;
//            this.al = al;
            this.inf = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() { // 총 데이터의 개수
            return DatabaseHelper.ReserveList.size();
        }
        @Override
        public Object getItem(int position) { // 해당 행의 데이터
            return DatabaseHelper.ReserveList.get(position);
        }
        @Override
        public long getItemId(int position) { // 해당 행의 유니크한 id
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = inf.inflate(layout, null);

            ImageView iv = (ImageView) convertView.findViewById(R.id.img);
            TextView tv1 = (TextView) convertView.findViewById(R.id.textView1);
            TextView tv2 = (TextView) convertView.findViewById(R.id.textView2);
            TextView tv3 = (TextView) convertView.findViewById(R.id.textView3);
            TextView tv4 = (TextView) convertView.findViewById(R.id.textView4);
            TextView tv5 = (TextView) convertView.findViewById(R.id.textView5);
            TextView tv6 = (TextView) convertView.findViewById(R.id.textView6);

            Store s = DatabaseHelper.ReserveList.get(position);
            iv.setImageResource(s.img);
            tv1.setText(s.name);
            tv2.setText(s.local);
            tv3.setText(s.pnum);
            tv4.setText(s.resdata + "~");
            tv5.setText(s.finddata);
            tv6.setText(s.lugcnt);
            return convertView;
        }
    }
}

