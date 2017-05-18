package com.mayc.unizar.app.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayc.unizar.app.MenuActivity;
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
    public static final String ARG_STORY_ID = "Story";
    private static final String TAG = "CardFragment";
    private SwipePlaceHolderView mSwipeView;
    private ItemFactory factory;
    private Context mContext;
    private GameEngine engine;
    private DbAdapter mDb;

    private int storyID;

    public CardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cards_fragment_layout, container, false);
        //get story id
        this.storyID=getArguments().getInt(ARG_STORY_ID,1);
        mSwipeView = (SwipePlaceHolderView) view.findViewById(R.id.swipeView);
        mContext = view.getContext();
        mDb = new DbAdapter(view.getContext());
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
        //check if exits save decisions
        if(mDb.countLastDecisions()!=0){
            //show dialog
            //mDb.removeFinales( this.storyID );
            Bundle b = new Bundle();
            b.putInt(CardFragment.ARG_STORY_ID,this.storyID );
            b.putString("STORY_NAME","No name");
            b.putSerializable( "Choices" ,new ItemFactory(mDb).lastChoices( this.storyID ) );
            ChoicesDialogFragment dialogFragment = new ChoicesDialogFragment ();
            dialogFragment.setArguments( b );
            dialogFragment.show( MenuActivity.fm, "Last choices");
            //last chooice
            engine = new GameEngine(mDb,view.getContext(),mSwipeView,this.storyID,true);
        }else{
            //from start
            engine = new GameEngine(mDb,view.getContext(),mSwipeView,this.storyID);
        }






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

    @Override
    public void onDestroy() {
       //remove  last choices from DB
        mDb.removeFinales( this.storyID );
        //save last histories into DB
        mDb.insertFinales(1, this.storyID,this.engine.getLastCardsId() );
        Log.d( "DEBUG", "Se han insertado las historias correctamente" );
        mDb.close();
        super.onDestroy();
    }
}
