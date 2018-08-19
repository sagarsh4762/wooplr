package com.wooplr.brandwith.brandwith;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private TextView emptyTextView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-7913641157138911~6217586857");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        emptyTextView = (TextView) findViewById(R.id.empty_view);
        webView = (WebView) findViewById(R.id.webview);
        ConnectivityManager connMgr = (ConnectivityManager)

                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);


        // Get details on the currently active default data network

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        // If there is a network connection, fetch data

        if (networkInfo != null && networkInfo.isConnected()) {
            webView.loadUrl("http://brandwith.wooplr.com");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setVerticalScrollBarEnabled(true);
            webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            webView.getSettings().setAppCacheEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            webSettings.setUseWideViewPort(true);
            webSettings.setSavePassword(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webSettings.setSaveFormData(true);
            webSettings.setEnableSmoothTransition(true);
            webView.setWebViewClient(new WebViewClient());
        } else {

            emptyTextView.setText("No Internet Connection");
            webView.setVisibility(View.GONE);

        }
    }

}


