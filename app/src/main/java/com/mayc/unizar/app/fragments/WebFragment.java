package com.mayc.unizar.app.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mayc.unizar.app.R;
import com.mayc.unizar.app.utils.DownloadTask;

/**
 * Fragmento para el contenido principal
 */
public class WebFragment extends Fragment {
    /**
     * Este argumento del fragmento representa el título de cada
     * sección
     */
    public static final String ARG_LAYOUT = "Layout";
    private static String URL = "http://www.guillermocebollero.es/app/";

    public WebFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_layout, container, false);
        // declare the dialog as a member field of your activity
        final ProgressDialog mProgressDialog;

        // instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(view.getContext());
        mProgressDialog.setMessage("A message");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);


        WebView web = (WebView) view.findViewById(R.id.webview);
        web.loadUrl(URL);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".json")) {
                    // execute this when the downloader must be fired
                    final DownloadTask downloadTask = new DownloadTask(view.getContext(), mProgressDialog);
                    downloadTask.execute(url);
                    mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            downloadTask.cancel(true);
                        }
                    });

                } else if (!url.equals(URL)) {
                    Toast.makeText(view.getContext(), "It seems to not be a valid story link.", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
        web.getSettings().setAllowFileAccess(true);

        return view;
    }

}
