package com.mayc.unizar.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayc.unizar.app.Histories;
import com.mayc.unizar.app.History;
import com.mayc.unizar.app.HistoryInfo;
import com.mayc.unizar.app.R;
import com.mayc.unizar.app.Utils;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.List;

/**
 * Fragmento para el contenido principal
 */
public class ManageStoriesFragment extends Fragment {
    /**
     * Este argumento del fragmento representa el título de cada
     * sección
     */
    public static final String ARG_LAYOUT = "Layout";
    private InfinitePlaceHolderView mLoadMoreView;
    private static String text;

    public ManageStoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.histories_fragment_layout, container, false);
        text= getArguments().getString(ARG_LAYOUT,"default content");
        mLoadMoreView = (InfinitePlaceHolderView)view.findViewById(R.id.loadMoreView);
        /*
         * Carga de los elementos del list view
         */
        List<HistoryInfo> feedList = Utils.loadInfiniteFeeds(getActivity().getApplicationContext());
        Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + Histories.LOAD_VIEW_SET_COUNT);
        for(int i = 0; i < Histories.LOAD_VIEW_SET_COUNT; i++){
            mLoadMoreView.addView(new History(getActivity().getApplicationContext(), feedList.get(i)));
        }
        mLoadMoreView.setLoadMoreResolver(new Histories(mLoadMoreView, feedList));



        return view;
    }


    private void setupView(){


    }

}
