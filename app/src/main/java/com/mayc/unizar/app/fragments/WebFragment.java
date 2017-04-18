package com.mayc.unizar.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.mayc.unizar.app.R;

/**
 * Fragmento para el contenido principal
 */
public class WebFragment extends Fragment {
    /**
     * Este argumento del fragmento representa el título de cada
     * sección
     */
    public static final String ARG_LAYOUT = "Layout";
    private static final String URL = "http://www.guillermocebollero.es/app/";
    public WebFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_layout, container, false);
        WebView web = (WebView) view.findViewById(R.id.webview);
        web.loadUrl(URL);
        web.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                if(url.endsWith(".json")) {
                    Toast.makeText(view.getContext(), "OK", Toast.LENGTH_SHORT).show();
                }else if(!url.equals(URL)){
                    Toast.makeText(view.getContext(), "It seems to not be a valid story link.", Toast.LENGTH_LONG).show();
                    Toast.makeText(view.getContext(),url,Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
        return view;
    }

}
