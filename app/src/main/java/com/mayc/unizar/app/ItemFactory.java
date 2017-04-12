package com.mayc.unizar.app;


import android.database.Cursor;

import com.mayc.unizar.app.adapters.DbAdapter;
import com.mayc.unizar.app.types.Item;

/**
 * Created by pfort on 06/04/2017.
 */

public class ItemFactory {

    public DbAdapter db;


    public Item getCard(int id, int Historia){
       Cursor cur = db.returnTarjeta(Historia, id);
        if (cur.moveToFirst()){
            return new Item(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getString(3), cur.getString(4), cur.getInt(5), cur.getString(6), cur.getInt(7));
        }
        return new Item(-1);
   }



   public Item[] allCards(int Historia){
       Cursor c = db.returnAllFromHistoria(Historia);
       Item [] list = new Item[c.getCount()];
       int i=0;
       do {
           list[i] = new Item(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getInt(5), c.getString(6), c.getInt(7));
           i++;
       } while(c.moveToNext());
       return list;
   }

}
