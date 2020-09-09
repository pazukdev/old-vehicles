package com.pazukdev.oldvehicles;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/**
 * @author Siarhei Sviarkaltsau
 */
public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://www.old-vehicles.com";

    private WebView view;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                final AdView adView = findViewById(R.id.adView);
                final AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
            }
        });

        view = findViewById(R.id.webview);
        view.setBackgroundColor(Color.TRANSPARENT);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setDomStorageEnabled(true);
        view.setWebViewClient(new AppWebViewClient());
        view.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        if (savedInstanceState == null) {
            view.loadUrl(URL);
        }
    }

    @Override
    public void onBackPressed() {
        if(view.canGoBack()) {
            view.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        view.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        view.restoreState(savedInstanceState);
    }

}
