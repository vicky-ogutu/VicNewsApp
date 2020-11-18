package com.example.vicnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class content extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        String url = getIntent().getExtras().getString("url", "");

        WebView myWebView = (WebView) findViewById(R.id.content1);
        myWebView.loadUrl(url);

        /*TextView contentText = findViewById(R.id.text);
        contentText.setText(url);*/

    }
}