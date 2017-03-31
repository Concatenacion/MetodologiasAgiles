package com.mayc.unizar.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mayc.unizar.app.R;

import org.w3c.dom.Text;

/**
 * Fragmento para el contenido principal
 */
public class DefaultFragment extends Fragment {
    /**
     * Este argumento del fragmento representa el título de cada
     * sección
     */
    public static final String ARG_LAYOUT = "Layout";
    private static String text;

    public DefaultFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        text= getArguments().getString(ARG_LAYOUT,"default content");
        TextView t = (TextView)getActivity().findViewById(R.id.default_fragment_textview);
        t.setText(text);
        return view;
    }

}
