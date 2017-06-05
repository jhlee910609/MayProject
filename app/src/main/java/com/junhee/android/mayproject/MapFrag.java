package com.junhee.android.mayproject;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFrag extends Fragment implements OnMapReadyCallback {

    LocationManager manager;
    GoogleMap map = null;

    public MapFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // 상위 택티비티의 자원을 사용하기 위해서 Activity를 가져옴
        // 나를 호출한 액티비티 호출
        MainActivity activity = (MainActivity) getActivity();

        manager = activity.getLocationManager();

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // 좌표만 생성
        LatLng home = new LatLng(37.321362, 127.087111);
        // 좌표를 적용
        CameraPosition cp = new CameraPosition.Builder().target((home)).zoom(15).build();
        // 1. 마커를 화면에 그린다
        MarkerOptions marker = new MarkerOptions().position(home);
        marker.title("우리 집");
        map.addMarker(marker);
        // 2. 앱의 중심을 해당 좌표로 이동시킨다. >                   // 좌표 // 줌 레벨
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 10));
    }
    // 현재 프레그먼트가 러닝직전

    @Override
    public void onResume() {
        super.onResume();
        // 마시멜로 이상 버전에서는 런타임 권한 체크여부를 확인해야 함

        // 마시멜로 이상버전에서는 런타임 권한 체크여부를 확인해야 한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // GPS 사용을 위한 권한 획득이 되어 있지 않으면 리스너 등록을 하지 않는다
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        // GPS 리스너 등록
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 위치제공자
                3000, // 변경사항 체크 주기 millisecond
                1,    // 변경사항 체크 거리 meter
                locationListener
        );
    }

    // 현재 프레그먼트가 정지
    @Override
    public void onPause() {
        super.onPause();
        // 마시멜로 이상버전에서는 런타임 권한 체크여부를 확인해야 한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // GPS 사용을 위한 권한 획득이 되어 있지 않으면 리스너를 해제하지 않는다.
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        // 리스너 해제
        manager.removeUpdates(locationListener);
    }

    // GPS를 사용하기 위해서 좌표 리스너를 생성
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // 경도
            double lng = location.getLongitude();
            // 위도
            double lat = location.getLatitude();
            // 고도
            double alt = location.getAltitude();
            // 정확도
            float acc = location.getAccuracy();
            // 위치 제공자
            String provider = location.getProvider();
            // 바뀐 현재 좌표를 넣어줌
            LatLng current = new LatLng(lat, lng);

            // 현재 좌표로 카메라로 이동
            // 구글맵 전역으로 뺌
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 17));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}


