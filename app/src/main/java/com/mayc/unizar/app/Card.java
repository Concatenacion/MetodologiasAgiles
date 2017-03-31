package com.mayc.unizar.app;

import android.content.Context;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.*;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.*;

@Layout(R.layout.card_view)
public class Card {

    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    @View(R.id.option1)
    private TextView option1;

    @View(R.id.option2)
    private TextView option2;

    @View(R.id.label_option2)
    private TextView labelOption2;

    @View(R.id.label_option1)
    private TextView labelOption1;

    private Item mItem;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    public Card(Context context, Item item, SwipePlaceHolderView swipeView) {
        mContext = context;
        mItem = item;
        mSwipeView = swipeView;

    }

    @Resolve
    private void onResolved(){
        Glide.with(mContext).load(mItem.getImageUrl()).into(profileImageView);
        nameAgeTxt.setText(mItem.getName());
        locationNameTxt.setText(mItem.getDescription());
        option1.setText(mItem.getOption1());
        option2.setText(mItem.getOption2());
        String aux;

        if(mItem.getOption1().length()<=15){
            aux=mItem.getOption1();
        }else{
            aux=mItem.getOption1().substring(0,12)+"...";
        }
        labelOption1.setText(aux);

        if(mItem.getOption2().length()<=15){
            aux=mItem.getOption2();
        }else{
            aux=mItem.getOption2().substring(0,12)+"...";
        }
        labelOption2.setText(aux);
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        //Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
        mSwipeView.addView(this);
    }

    @SwipeInState
    private void onSwipeInState(){
        //Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        //Log.d("EVENT", "onSwipeOutState");
    }
}