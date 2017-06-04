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
    Fragment gallery, cal, webViewer, unitChanger;
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


        // 2. 프레그먼트 생성
        gallery = new GalleryFrag();
        cal = new CalFrag();
        webViewer = new WebViewFrag();
        unitChanger = new UnitChangerFrag();


        // 3. 데이터 구성
        datas = new ArrayList<>();
        datas.add(gallery);
        datas.add(cal);
        datas.add(webViewer);
        datas.add(unitChanger);

        // 3. 페이저 생성
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), datas);
        pager.setAdapter(adapter);

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
