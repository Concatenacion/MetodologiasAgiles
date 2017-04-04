package com.mayc.unizar.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

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

    public WebFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_layout, container, false);
        WebView web = (WebView) view.findViewById(R.id.webview);
        web.loadUrl("https://github.com/Concatenacion/MetodologiasAgiles");
        TextView url = (TextView) view.findViewById(R.id.url);
        url.setText("https://github.com/Concatenacion/MetodologiasAgiles");
        return view;
    }

}
