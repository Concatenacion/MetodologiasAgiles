package com.mayc.unizar.app.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayc.unizar.app.adapters.DbAdapter;
import com.mayc.unizar.app.factories.ItemFactory;
import com.mayc.unizar.app.utils.GameEngine;
import com.mayc.unizar.app.utils.JsonUtils;
import com.mayc.unizar.app.views.Card;
import com.mayc.unizar.app.types.Item;
import com.mayc.unizar.app.R;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

/**
 * Fragmento para el contenido principal
 */
public class CardFragment extends Fragment {
    /**
     * Este argumento del fragmento representa el título de cada
     * sección
     */
    public static final String ARG_LAYOUT = "Layout";
    private static final String TAG = "CardFragment";
    private SwipePlaceHolderView mSwipeView;
    private ItemFactory factory;
    private Context mContext;

    public CardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cards_fragment_layout, container, false);
        mSwipeView = (SwipePlaceHolderView) view.findViewById(R.id.swipeView);
        mContext = view.getContext();
        DbAdapter mDb = new DbAdapter(view.getContext());
        if(mDb.countHistories()==0){
            /*Insert Demo Story here */
            Log.d(TAG, "onCreateView: inserting demo story on database");
            JsonUtils.saveToDB(view.getContext(),"profiles.json");
        }

        mSwipeView.getBuilder()
                .setDisplayViewCount(1)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.card_msg_green_view)
                        .setSwipeOutMsgLayoutId(R.layout.card_msg_red_view));
        factory = new ItemFactory(mDb);
        GameEngine engine = new GameEngine(mDb,view.getContext(),mSwipeView,1);
        //mSwipeView.addView(new Card(view.getContext(),factory.getCard(1,1),mSwipeView));
/*
        for(Item item : JsonUtils.loadProfiles(view.getContext())){
            mSwipeView.addView(new Card(mContext, item, mSwipeView));
        }
*/
        view.findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        view.findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });



        return view;
    }

}
