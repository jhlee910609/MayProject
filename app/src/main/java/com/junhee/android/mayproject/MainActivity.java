package com.junhee.android.mayproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager pager;
    Fragment gallery, cal, webViewer, unitChanger, map;
    List<Fragment> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
