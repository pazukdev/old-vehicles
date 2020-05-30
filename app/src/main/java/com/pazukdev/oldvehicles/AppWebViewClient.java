package com.pazukdev.oldvehicles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author Siarhei Sviarkaltsau
 */
public class AppWebViewClient extends WebViewClient {

    public static final String OLD_VEHICLES = "www.old-vehicles.com";

    private ProgressDialog dialog;

    private boolean isInnerUrl(final String url) {
        return Uri.parse(url).getHost().endsWith(OLD_VEHICLES);
    }

    @Override
    public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
        if(isInnerUrl(url)) {
            return false;
        }

        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }

//    @RequiresApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(final WebView view, final WebResourceRequest request) {
        return shouldOverrideUrlLoading(view, request.getUrl().toString());
    }

    @Override
    public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        dialog = new ProgressDialog(view.getContext());
        dialog.setMessage(view.getResources().getString(R.string.connecting) + "...");
        dialog.setTitle(view.getResources().getString(R.string.waiting));
        dialog.setIndeterminate(false);
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onPageFinished(final WebView view, final String url) {
        super.onPageFinished(view, url);

        dialog.dismiss();
    }

}
