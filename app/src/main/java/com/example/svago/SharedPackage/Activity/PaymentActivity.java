package com.example.svago.SharedPackage.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.svago.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import im.delight.android.webview.AdvancedWebView;

public class PaymentActivity extends AppCompatActivity implements AdvancedWebView.Listener {


    private String ShowOrHideWebViewInitialUse = "show";
    private String stringURL;

    @BindView(R.id.progressBar1)
    ProgressBar mSpinner;
    @BindView(R.id.webview)
    AdvancedWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        getIntentData();


        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.getSettings().setDomStorageEnabled(true);

        mWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        mWebView.loadUrl(stringURL);

        mSpinner.setVisibility(View.GONE);


        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                /*if (url.contains("true"))
                {
                    getSuccessfull();
                }
                if (url.contains("false")){
                    getFaild();
                }*/

                Toast.makeText(PaymentActivity.this, url, Toast.LENGTH_SHORT).show();

                return false;

            }
        });

    }

    private void getIntentData() {
        Bundle bundle=getIntent().getExtras();
        if (!bundle.isEmpty()){
            stringURL=bundle.getString("URL");
        }
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        if (ShowOrHideWebViewInitialUse.equals("show")) {
            mWebView.setVisibility(mWebView.INVISIBLE);
        }
    }

    @Override
    public void onPageFinished(String url) {
        ShowOrHideWebViewInitialUse = "hide";
        mSpinner.setVisibility(View.GONE);
        mWebView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mWebView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        super.onDestroy();
    }

}
