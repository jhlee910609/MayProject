package com.junhee.android.mayproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnitChangerFrag extends Fragment {
    private Spinner before, after;
    TextView result_txt;
    EditText input_editor;
    private String units[] = {"mm", "cm", "m", "km"};
    double beforeUnit = 0;
    double afterUnit = 0;
    View view;
    Button btnDoCal;


    public UnitChangerFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_unit_changer_frag, container, false);
        init();


        //TODO 예제랑 달라 문제 발생할지도 모름
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, units);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        before.setAdapter(adapter1);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, units);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        after.setAdapter(adapter2);

        selectedListener();
        result_txt.setText(calculate() + "");
        return view;
    }

    // 단위 변환 계산을 실행한다.
    private double calculate() {
        int tempInput = Integer.parseInt(input_editor.getText().toString());
        double result = beforeUnit * tempInput / afterUnit;
        return result;
    }

    // 변수와 뷰를 연결하는 초기화를 한다.
    public void init() {
        before = (Spinner) view.findViewById(R.id.spin_before);
        after = (Spinner) view.findViewById(R.id.spin_after);
        input_editor = (EditText) view.findViewById(R.id.edit_input);
        result_txt = (TextView) view.findViewById(R.id.resutl_txt);
        btnDoCal = (Button) view.findViewById(R.id.btnDoCal);
    }

    // 스피너의 .setOnItemSelectedListener 를 관리한다
    public void selectedListener() {

        before.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        beforeUnit = 1;
                        break;

                    case 1:
                        beforeUnit = 0.1;
                        break;

                    case 2:
                        beforeUnit = 0.001;
                        break;

                    case 3:
                        beforeUnit = 0.0000001;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "단위를 지정해주세요.", Toast.LENGTH_SHORT).show();

            }
        });

        after.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        afterUnit = 1;
                        break;

                    case 1:
                        afterUnit = 10;
                        break;

                    case 2:
                        afterUnit = 1000;
                        break;

                    case 3:
                        afterUnit = 1000000;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "단위를 지정해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

