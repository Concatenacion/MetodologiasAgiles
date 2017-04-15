package com.mayc.unizar.app.utils;

import android.content.Context;

import com.mayc.unizar.app.adapters.DbAdapter;
import com.mayc.unizar.app.factories.ItemFactory;
import com.mayc.unizar.app.types.Item;
import com.mayc.unizar.app.views.Card;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.swipe.SwipeView;

/**
 * Created by novales35 on 13/04/17.
 */

public class GameEngine {
    private static ItemFactory itemFactory;
    private DbAdapter mDb;
    private Context ctx;
    private SwipePlaceHolderView mSwipePlaceHolderView;

    private Item currentCard;
    private int storyID;
    public GameEngine(DbAdapter mDb,Context ctx,SwipePlaceHolderView mSwipePlaceHolderView,int storyID){
        this.mDb=mDb;
        this.itemFactory= new ItemFactory(mDb);
        this.storyID=storyID;
        this.currentCard=itemFactory.getCard(1,storyID);
        this.ctx=ctx;
        this.mSwipePlaceHolderView=mSwipePlaceHolderView;
        mSwipePlaceHolderView.addView(getCurrentCard());
    }

    public Card getCurrentCard(){
        return new Card(ctx,currentCard,mSwipePlaceHolderView,this);
    }
    public void selectLeftOption(){
        currentCard=itemFactory.getCard(currentCard.getNextOption1(),storyID);
        mSwipePlaceHolderView.addView(new Card(ctx,currentCard,mSwipePlaceHolderView,this));
    }
    public void selectRigthOption(){
        currentCard=itemFactory.getCard(currentCard.getNextOption2(),storyID);
        mSwipePlaceHolderView.addView(new Card(ctx,currentCard,mSwipePlaceHolderView,this));
    }

}
