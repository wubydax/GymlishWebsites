package com.wubydax.gymlishwebsites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.wubydax.gymlishwebsites.data.WebsiteInfo;

public class WebViewActivity extends AppCompatActivity {
    static final String BUNDLE_KEY = "bundled_info";
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WebsiteInfo websiteInfo;
        if(savedInstanceState != null) {
            websiteInfo = savedInstanceState.getParcelable(BUNDLE_KEY);
        } else {
            if(getIntent() != null) {
                websiteInfo = getIntent().getParcelableExtra(BUNDLE_KEY);
            } else {
                websiteInfo = new WebsiteInfo();
            }
        }
        assert websiteInfo != null;
        mUrl = websiteInfo.url;
        String title = websiteInfo.title;
        setTitle(title);


        initWebView();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        if(isNetworkAvailable()) {
            WebView webView = (WebView) findViewById(R.id.web_view);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(mUrl);
        } else {
            Toast.makeText(this, R.string.no_connection_toast, Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
