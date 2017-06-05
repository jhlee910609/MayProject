package com.junhee.android.mayproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.junhee.android.mayproject.R.id.map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFrag extends Fragment implements OnMapReadyCallback {


    public MapFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // 좌표만 생성
        LatLng home = new LatLng(37.321362, 127.087111);
        // 좌표를 적용
        CameraPosition cp = new CameraPosition.Builder().target((home)).zoom(15).build();
        // 1. 마커를 화면에 그린다
        MarkerOptions marker = new MarkerOptions().position(home);
        marker.title("우리 집");
        googleMap.addMarker(marker);
        // 2. 앱의 중심을 해당 좌표로 이동시킨다. >                   // 좌표 // 줌 레벨
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 10));
    }
}
