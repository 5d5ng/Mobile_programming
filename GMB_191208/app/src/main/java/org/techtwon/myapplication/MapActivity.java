package org.techtwon.myapplication;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    DatabaseHelper dbHelper ;
    SQLiteDatabase database ;

    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
//        dbHelper = DatabaseHelper.getInstance(getApplicationContext());
//        database = dbHelper.getReadableDatabase();

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


                if(DatabaseHelper.isLogin) {
                    DatabaseHelper.PickedStore = true; //매장 선택 확인
                    Toast.makeText(getApplicationContext(), "결제 페이지로 이동", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(MapActivity.this, PayActivity.class);
                    startActivity(myintent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "로그인 후 시도하세요.", Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(MapActivity.this, LoginActivity.class);
                    startActivity(myintent);
                }
            }
        });

        // SupportMapFragment을 통해 레이아웃에 만든 fragment의 ID를 참조하고 구글맵을 호출한다.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //getMapAsync must be called on the main thread.

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // 구글 맵 객체를 불러온다.
        mMap = googleMap;
        ArrayList<MarkerOptions> storeList = new ArrayList<MarkerOptions>();
        // 학교에 대한 위치 설정
        LatLng school = new LatLng(35.134428, 129.103109);

        // 구글 맵에 표시할 마커에 대한 옵션 설정
        MarkerOptions makerOptions1 = new MarkerOptions();
        makerOptions1
                .position(school)
                .title("IT 물품 보관소")
                .snippet("30일, 100000원")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        MarkerOptions makerOptions2 = new MarkerOptions();
        makerOptions2
                .position(new LatLng(35.136901, 129.103704))
                .title("덩이의 물품 보관소")
                .snippet("1일, 2000원")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        MarkerOptions makerOptions3 = new MarkerOptions();
        makerOptions3
                .position(new LatLng(35.131207, 129.104286))
                .title("MUX의 물품 보관소")
                .snippet("3일, 5000원")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        MarkerOptions makerOptions4 = new MarkerOptions();
        makerOptions4
                .position(new LatLng(35.134847, 129.103292))
                .title("레오밍키의 물품 보관소")
                .snippet("1일, 10000원")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

        // 마커를 생성한다.
        mMap.addMarker(makerOptions1);
        mMap.addMarker(makerOptions2);
        mMap.addMarker(makerOptions3);
        mMap.addMarker(makerOptions4);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(school, 14));
    }


}
