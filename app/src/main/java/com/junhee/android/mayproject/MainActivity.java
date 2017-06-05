package com.junhee.android.mayproject;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager pager;
    Fragment gallery, cal, webViewer, unitChanger, map;
    List<Fragment> datas;
    LocationManager manager;

    public LocationManager getLocationManager() {
        return manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Context.를 달아주는 이유는 현재 컨텍스트의.LocationSERVIE를 명시해줘야하기 때문에

        // 프레그먼트 생성주기가 변화하지만 매니져는 액티비티에서 계속 생성되어 있기 때문에 새로운 프로그먼트들이
        // 해당 매니져를 사용하는데에 전혀 지장이 없음
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 1. 탭 구성
        pager = (ViewPager) findViewById(R.id.pager);
        tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("갤러리"));
        tab.addTab(tab.newTab().setText("계산기"));
        tab.addTab(tab.newTab().setText("웹뷰어"));
        tab.addTab(tab.newTab().setText("단위 변환기"));
        tab.addTab(tab.newTab().setText("구글맵"));

        // 2. 프레그먼트 생성
        gallery = new GalleryFrag();
        cal = new CalFrag();
        webViewer = new WebViewFrag();
        unitChanger = new UnitChangerFrag();
        map = new MapFrag();


        // 3. 뷰 페이저에 나타낼 데이터 구성 및 연결
        datas = new ArrayList<>();
        datas.add(gallery);
        datas.add(cal);
        datas.add(webViewer);
        datas.add(unitChanger);
        datas.add(map);

        // 4. 페이저 생성
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), datas);
        pager.setAdapter(adapter);


        // 5. 페이지 및 탭 넘길 때, 같이 넘어가도록 설정
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));


        // 퍼미션을 위한 버전체크
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermission();
        }
    }

    // 위치제공자 사용을 위한 권한처리
    private final int REQ_PERMISSION = 100;
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission(){
        //1 권한체크 - 특정권한이 있는지 시스템에 물어본다
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

        }else{
            // 2. 권한이 없으면 사용자에 권한을 달라고 요청
            String permissions[] = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permissions ,REQ_PERMISSION); // -> 권한을 요구하는 팝업이 사용자 화면에 노출된다
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQ_PERMISSION){
            // 3.1 사용자가 승인을 했음
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else{
                cancel();
            }
        }
    }

    public void cancel(){
        Toast.makeText(this, "권한요청을 승인하셔야 GPS를 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
}


// 페이저 어댑터 setting
class PagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> datas;

    public PagerAdapter(FragmentManager fm, List<Fragment> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
