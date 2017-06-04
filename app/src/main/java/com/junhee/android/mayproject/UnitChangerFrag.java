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


    public UnitChangerFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_unit_changer_frag, container, false);
        before = (Spinner) view.findViewById(R.id.spin_before);
        after = (Spinner) view.findViewById(R.id.spin_after);
        input_editor = (EditText) view.findViewById(R.id.edit_input);
        result_txt = (TextView) view.findViewById(R.id.resutl_txt);


        //TODO 예제랑 달라 문제 발생할지도 모름
        beforItem();
        afterItem();


        return view;
    }
    private void beforItem(){
        ArrayAdapter<String> unitAdapter;
        unitAdapter = ArrayAdapter.createFromResource(,R.array.unit_array, android.R.layout.simple_list_item_1);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        before.setAdapter(unitAdapter);
    }

    private void afterItem(){
        ArrayAdapter<String> unitAdapter;
        unitAdapter = ArrayAdapter.createFromResource(this,R.array.unit_array, android.R.layout.simple_list_item_1);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        after.setAdapter(unitAdapter);
    }
}
