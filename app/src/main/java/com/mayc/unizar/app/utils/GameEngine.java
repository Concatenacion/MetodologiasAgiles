package com.mayc.unizar.app.utils;

import android.content.Context;
import android.util.Log;

import com.mayc.unizar.app.adapters.DbAdapter;
import com.mayc.unizar.app.factories.ItemFactory;
import com.mayc.unizar.app.types.Item;
import com.mayc.unizar.app.views.Card;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.swipe.SwipeView;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by novales35 on 13/04/17.
 */

public class GameEngine {
    private static ItemFactory itemFactory;
    private DbAdapter mDb;
    private Context ctx;
    private SwipePlaceHolderView mSwipePlaceHolderView;
    private LimitedSizeQueue<Item> lastStories;
    private final int nLastStories=3;
    private boolean changeHistoy;

    private Item currentCard;
    private int storyID;
    public GameEngine(DbAdapter mDb,Context ctx,SwipePlaceHolderView mSwipePlaceHolderView,int storyID){
        this.mDb=mDb;
        this.changeHistoy=false;
        this.itemFactory= new ItemFactory(mDb);
        this.lastStories = new  LimitedSizeQueue(nLastStories);
        this.storyID=storyID;
        this.currentCard=itemFactory.getCard(1,storyID);
        this.ctx=ctx;
        this.mSwipePlaceHolderView=mSwipePlaceHolderView;

        mSwipePlaceHolderView.addView(getCurrentCard());
    }

    public GameEngine(DbAdapter mDb,Context ctx,SwipePlaceHolderView mSwipePlaceHolderView,int storyID,boolean loadLastHisories){
        this.mDb=mDb;
        this.changeHistoy=false;
        this.itemFactory= new ItemFactory(mDb);
        this.lastStories = new  LimitedSizeQueue(nLastStories);
        this.storyID=storyID;
        if(loadLastHisories){
           Item[] last= this.itemFactory.lastChoices( storyID );
            for(int i=0;i<(last.length-1);i++) this.lastStories.add(last[i]);
            this.currentCard=itemFactory.getCard( last[last.length-1].getId(),storyID );

        }else {
            this.currentCard = itemFactory.getCard( 1, storyID );
        }
        this.ctx=ctx;
        this.mSwipePlaceHolderView=mSwipePlaceHolderView;

        mSwipePlaceHolderView.addView(getCurrentCard());
    }



    public Card getCurrentCard(){
        return new Card(ctx,currentCard,mSwipePlaceHolderView,this);
    }
    public void selectLeftOption(){
        //sale this card in queue
        this.lastStories.add( currentCard );
        changeHistoy=true;
        currentCard=itemFactory.getCard(currentCard.getNextOption1(),storyID);
        mSwipePlaceHolderView.addView(new Card(ctx,currentCard,mSwipePlaceHolderView,this));
    }
    public void selectRigthOption(){
        this.lastStories.add( currentCard );
        changeHistoy=true;
        currentCard=itemFactory.getCard(currentCard.getNextOption2(),storyID);
        mSwipePlaceHolderView.addView(new Card(ctx,currentCard,mSwipePlaceHolderView,this));
    }


    /**
     *
     * return the nLastCards
     */
    public Item[] getLastCards(){
        Item[] lastCards=new Item[nLastStories];
        for (int i=0;i< lastCards.length;i++){
            lastCards[i]=this.lastStories.pop();
        }
        return lastCards;
    }

    /**
     *
     * return the nLastCards ids
     */
    public Integer[] getLastCardsId(){
       if(changeHistoy) this.lastStories.add( currentCard );
        ArrayList<Integer> lastCards=new ArrayList<Integer>(  );
        while(!this.lastStories.isEmpty()){
            Log.d("DEBUG","SAVING: "+this.lastStories.getOldest().getDescription());
            if(this.lastStories.getOldest()!=null)
                lastCards.add( this.lastStories.pop().getId());
            else this.lastStories.pop();
        }
        return lastCards.toArray(new Integer[lastCards.size()]);
    }


}
