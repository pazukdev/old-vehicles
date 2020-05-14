package com.pazukdev.oldvehicles;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

        view = findViewById(R.id.webview);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setDomStorageEnabled(true);
        if (savedInstanceState == null) {
            view.loadUrl(URL);
        }
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        view.reload();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
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
