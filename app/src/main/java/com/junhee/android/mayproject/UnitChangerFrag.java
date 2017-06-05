package com.junhee.android.mayproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnitChangerFrag extends Fragment {
    private Spinner before, after;
    private TextView result_txt;
    private EditText input_editor;
    private String units[] = {"mm", "cm", "m", "km"};
    View view;


    public UnitChangerFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_unit_changer_frag, container, false);
        before = (Spinner) view.findViewById(R.id.spin_before);
        after = (Spinner) view.findViewById(R.id.spin_after);
        input_editor = (EditText) view.findViewById(R.id.edit_input);
        result_txt = (TextView) view.findViewById(R.id.resutl_txt);





        //TODO 예제랑 달라 문제 발생할지도 모름
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, units);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        before.setAdapter(adapter1);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, units);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        after.setAdapter(adapter2);

        return view;

        // 1. before에서 아이템 선택
        // ====== before.setOnItemSelectedListener(); ======
        // 1.1 이때 선택한 아이템의 포지션 저장

        // ====== after.setOnItemSelectedListener(); =======
        // 2. after 아이템
        // 2,1 이때 선택한 아이템의 포지션 저장

        // ===== doCalculate (); ======
        // 3. 계산 로직 작성
        // 3.1 before.getselecteditempos과 after.getselecteditempos 비교
        // 3.2  == 그대로 출력
        // 3.3 > 곱하기로
        // 3.4 * 로

        // 3. textView에 띄운다



/*
        before.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        beforePos = 0;
                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        after.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        String strInput=input_editor.getText().toString();
        int tempInput = Integer.parseInt(strInput);


      **/





    }
}

