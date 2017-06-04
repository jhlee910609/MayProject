package com.junhee.android.mayproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFrag extends Fragment implements View.OnClickListener {

    private WebView webView;
    private Button btnGo, btnBack, btnFront;
    private EditText editText;


    public WebViewFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);

        btnBack = (Button) view.findViewById(R.id.btnBack);
        btnGo = (Button) view.findViewById(R.id.btnGo);
        btnFront = (Button) view.findViewById(R.id.btnFront);
        editText = (EditText) view.findViewById(R.id.editText);
        webView = (WebView) view.findViewById(R.id.webView);

        // ----------------- [ WebView 관련 셋팅해주기] ------------------
        // 1. 스크립트 허용 = true
        webView.getSettings().setJavaScriptEnabled(true);
        // 2. 웹뷰 클라이언트를 지정 (해당 작업을 하지 않으면 내장 브라우저가 팝엄됨)
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        // 3. uri 호출하기
        String temp = editText.getText().toString();
        loadUrl(temp);
        // ----- making clickListener on the buttons -------
        btnFront.setOnClickListener(this);
        btnGo.setOnClickListener(this);
        btnFront.setOnClickListener(this);

        return view;
    }

    private void loadUrl(String url) {
        // 문자열 앞에 프로톨인 'http://' 문자열이 없다면 자동으로 붙혀줌
        if (!url.startsWith("http")) {
            url = "http://" + url;
            webView.loadUrl(url);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
            // 뒤로 갈 곳 있는지 체크
                if(webView.canGoBack()){
                    webView.goBack();
                }
                break;

            case R.id.btnFront:
                if(webView.canGoForward()){
                    webView.goForward();
                }
                break;

            case R.id.btnGo:
                String url = editText.getText().toString();
                if(!"".equals(url)){
                    if(url.matches("^(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
                        loadUrl(url);
                    } else {
                        Toast.makeText(v.getContext(), "url을 잘못 입력하셨습니다.", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
}
