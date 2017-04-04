package com.mayc.unizar.app.fragments;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.mayc.unizar.app.Card;
import com.mayc.unizar.app.Item;
import com.mayc.unizar.app.R;
import com.mayc.unizar.app.Utils;
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
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    public CardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cards_fragment_layout, container, false);
        mSwipeView = (SwipePlaceHolderView) view.findViewById(R.id.swipeView);
        mContext = view.getContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(1)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.card_msg_green_view)
                        .setSwipeOutMsgLayoutId(R.layout.card_msg_red_view));



        for(Item item : Utils.loadProfiles(view.getContext())){
            mSwipeView.addView(new Card(mContext, item, mSwipeView));
        }

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
