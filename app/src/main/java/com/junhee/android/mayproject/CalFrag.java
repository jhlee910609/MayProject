package com.junhee.android.mayproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalFrag extends Fragment implements View.OnClickListener {

    private Button one, two, three, four, five, six, seven, eight, nine, zero, clear, equal, divine, multi, minus, plus;
    private TextView txt_output, txt_preview;


    public CalFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cal, container, false);
        txt_output = (TextView) view.findViewById(R.id.output);
        txt_preview = (TextView) view.findViewById(R.id.preview);
        // TODO View 정리하는 법.
        view.findViewById(R.id.btnOne).setOnClickListener(this);
        view.findViewById(R.id.btnTwo).setOnClickListener(this);
        view.findViewById(R.id.btnThree).setOnClickListener(this);
        view.findViewById(R.id.btnFour).setOnClickListener(this);
        view.findViewById(R.id.btnFive).setOnClickListener(this);
        view.findViewById(R.id.btnSix).setOnClickListener(this);
        view.findViewById(R.id.btnSeven).setOnClickListener(this);
        view.findViewById(R.id.btnEight).setOnClickListener(this);
        view.findViewById(R.id.btnNine).setOnClickListener(this);
        view.findViewById(R.id.btnZero).setOnClickListener(this);
        view.findViewById(R.id.btnEqual).setOnClickListener(this);
        view.findViewById(R.id.btnMinus).setOnClickListener(this);
        view.findViewById(R.id.btnPlus).setOnClickListener(this);
        view.findViewById(R.id.btnMulti).setOnClickListener(this);
        view.findViewById(R.id.btnDiv).setOnClickListener(this);
        view.findViewById(R.id.btnClear).setOnClickListener(this);
        // 결과창 초기화
        init();

        return view;
    }

    public void init() {
        txt_output.setText("");
        txt_preview.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                setPreview(1);
                break;

            case R.id.btnTwo:
                setPreview(2);
                break;

            case R.id.btnThree:
                setPreview(3);
                break;

            case R.id.btnFour:
                setPreview(4);
                break;

            case R.id.btnFive:
                setPreview(5);
                break;

            case R.id.btnSix:
                setPreview(6);
                break;

            case R.id.btnSeven:
                setPreview(7);
                break;

            case R.id.btnEight:
                setPreview(8);
                break;

            case R.id.btnNine:
                setPreview(9);
                break;

            case R.id.btnZero:
                setPreview(0);
                break;

            case R.id.btnDiv:
                setPreview("/");
                break;

            case R.id.btnPlus:
                setPreview("+");
                break;

            case R.id.btnMinus:
                setPreview("-");
                break;

            case R.id.btnMulti:
                setPreview("*");
                break;

            case R.id.btnClear:
                clear();
                break;

            case R.id.btnEqual:
                result();
                break;
        }
    }

    public void result() {
        String current = txt_preview.getText().toString();
        txt_output.setText(calculate(current));
    }

    private void setPreview(int num) {
        String current = txt_preview.getText().toString();
        txt_preview.setText(current + num);
    }

    private void setPreview(String str) {
        String current = txt_preview.getText().toString();
        txt_preview.setText(current + str);
    }

    private String calculate(String preview) {
        // 정규식을 활용하여 프리뷰 창에 있는 계산식들을 다 잘라냄
        String splited[] = preview.split("(?<=[*//*+-])|(?=[*//*+-])");
        ArrayList<String> datas = new ArrayList<>();
        for (String temp : splited) datas.add(temp);
        for (int i = 0; i < datas.size(); ++i) {
            String temp = datas.get(i);
            int resultTemp = 0;
            if (temp.equals("*") || temp.equals("/")) {
                int before = Integer.parseInt(datas.get(i - 1));
                int after = Integer.parseInt(datas.get(i + 1));
                if (temp.equals("*"))
                    resultTemp = before * after;
                else
                    resultTemp = before / after;
                // 결과값 저장
                // TODO 내 맘대로 함
                datas.set(i, resultTemp + "");
                datas.remove(i + 1);
                datas.remove(i - 1);
                i--;
            }
        }

        for (int i = 0; i < datas.size(); i++) {
            String temp = datas.get(i);
            int resultTemp = 0;
            if (temp.equals("+") || temp.equals("-")) {
                int before = Integer.parseInt(datas.get(i - 1));
                int after = Integer.parseInt(datas.get(i + 1));
                if (temp.equals("+"))
                    resultTemp = before + after;
                else
                    resultTemp = before - after;
                datas.add(i, resultTemp + "");
                datas.remove(i + 1);
                datas.remove(i - 1);
                i--;
            }
        }
        return datas.get(0);
    }

    private void clear() {
        txt_preview.setText("0");
        txt_output.setText("0");
        Toast.makeText(getView().getContext(), "초기화 되었습니다.", Toast.LENGTH_SHORT).show();
    }

}
