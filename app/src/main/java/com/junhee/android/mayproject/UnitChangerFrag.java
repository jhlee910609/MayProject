package com.junhee.android.mayproject;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
    Button btnClean;


    public UnitChangerFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_unit_changer_frag, container, false);
        init();
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });


        //TODO 예제랑 달라 문제 발생할지도 모름
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, units);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        before.setAdapter(adapter1);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, units);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        after.setAdapter(adapter2);

        // ==================== [실험 로직 =====================

        selectedListener();
        textWatcher();
        // result_txt.setText(calculate() + "");
        return view;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void textWatcher() {

        input_editor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // double 기본 표기가 지수 표현식으로 되서 10진수로 바꿔주는 작업을 한 번 더 거침
                int temp = Integer.parseInt(s.toString());
                result_txt.setText((temp * beforeUnit / afterUnit) + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    // 단위 변환 계산을 실행한다.
    private double calculate() {
        int tempInput = Integer.parseInt(input_editor.getText().toString());
        double result = beforeUnit * tempInput / afterUnit;
        return result;
    }

    // 변수와 뷰를 연결하는 초기화를 하는 메소드.
    public void init() {
        before = (Spinner) view.findViewById(R.id.spin_before);
        after = (Spinner) view.findViewById(R.id.spin_after);
        input_editor = (EditText) view.findViewById(R.id.edit_input);
        result_txt = (TextView) view.findViewById(R.id.resutl_txt);
        btnClean = (Button) view.findViewById(R.id.btnClean);
    }

    private void clear() {
        // TODO editText 사용 시, xml 상의 input type 확인 잘할 것
        // 맞지 않으면 에러 발생
        input_editor.setText(0 + "");
    }

    // 스피너의 .setOnItemSelectedListener 관리하는 메서드
    private void selectedListener() {

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

