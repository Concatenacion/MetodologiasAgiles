package com.mayc.unizar.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayc.unizar.app.adapters.DbAdapter;
import com.mayc.unizar.app.factories.ItemFactory;
import com.mayc.unizar.app.factories.StoryFactory;
import com.mayc.unizar.app.utils.JsonUtils;
import com.mayc.unizar.app.views.Histories;
import com.mayc.unizar.app.views.History;
import com.mayc.unizar.app.types.HistoryInfo;
import com.mayc.unizar.app.R;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.Arrays;
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

    private StoryFactory factory;

    public ManageStoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.histories_fragment_layout, container, false);
        /*
         * Carga de los elementos del list view
         */
        mLoadMoreView = (InfinitePlaceHolderView)view.findViewById(R.id.loadMoreView);
        //obtener informacion de la base de datos
        DbAdapter mDb = new DbAdapter(view.getContext());
        if(mDb.countHistories()==0){
            Log.d("DEBUG", "No dispones de historias.");
            text="No dispones de historias";
        }
        else {
            factory = new StoryFactory(mDb);
            //List<HistoryInfo> feedList = JsonUtils.loadInfiniteFeeds(getActivity().getApplicationContext());
            List<HistoryInfo> feedList = Arrays.asList(factory.allHistories());
            Log.d("DEBUG", "Numero de historias: "+feedList.size());
            Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + Histories.LOAD_VIEW_SET_COUNT);
            for (int i = 0; i < Histories.LOAD_VIEW_SET_COUNT && i < (feedList.size() - 1) ; i++) {
                mLoadMoreView.addView(new History(getActivity().getApplicationContext(), feedList.get(i)));
            }
            mLoadMoreView.setLoadMoreResolver(new Histories(mLoadMoreView, feedList));
        }



        return view;
    }




}
