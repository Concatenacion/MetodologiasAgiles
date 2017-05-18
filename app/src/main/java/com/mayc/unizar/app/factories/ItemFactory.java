package com.mayc.unizar.app.factories;


import android.database.Cursor;
import android.util.Log;

import com.mayc.unizar.app.adapters.DbAdapter;
import com.mayc.unizar.app.utils.SQLiteRelacional;
import com.mayc.unizar.app.types.Item;

import static com.mayc.unizar.app.utils.SQLiteRelacional.Cuerpo;
import static com.mayc.unizar.app.utils.SQLiteRelacional.Derecha;
import static com.mayc.unizar.app.utils.SQLiteRelacional.Foto;
import static com.mayc.unizar.app.utils.SQLiteRelacional.Historia;
import static com.mayc.unizar.app.utils.SQLiteRelacional.Izquierda;
import static com.mayc.unizar.app.utils.SQLiteRelacional.KEY_IDINFO;
import static com.mayc.unizar.app.utils.SQLiteRelacional.Nombre;
import static com.mayc.unizar.app.utils.SQLiteRelacional.OpcionDerecha;
import static com.mayc.unizar.app.utils.SQLiteRelacional.OpcionIzquierda;

/**
 * Created by pfort on 06/04/2017.
 */

public class ItemFactory {

    public DbAdapter db;
    private static final String TAG = "ItemFactory";
    public ItemFactory(DbAdapter db){
        this.db=db;
    }


    public Item getCard(int id, int historia){
        Log.d(TAG, "getCard: Trying to read card:"+id+" from story:"+historia);
       Cursor cur = db.returnTarjeta(historia, id);
        if (cur!=null && cur.moveToFirst()){
            Log.d(TAG, "getCard: Generating card");
            return new Item(cur.getInt(cur.getColumnIndex(KEY_IDINFO)), cur.getString(cur.getColumnIndex(Nombre)), cur.getString(cur.getColumnIndex(Foto)), cur.getString(cur.getColumnIndex(Cuerpo)), cur.getString(cur.getColumnIndex(OpcionIzquierda)), cur.getInt(cur.getColumnIndex(Izquierda)), cur.getString(cur.getColumnIndex(OpcionDerecha)), cur.getInt(cur.getColumnIndex(Derecha)));
        }
        Log.w(TAG, "getCard: Returning null");
        return new Item(-1);
   }



   public Item[] allCards(int historia){
       Cursor c = db.returnAllFromHistoria(historia);
       Item [] list = new Item[c.getCount()];
       c.moveToFirst();
       int i=0;
       do {
           list[i] = new Item(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getInt(5), c.getString(6), c.getInt(7));
           i++;
       } while(c.moveToNext());
       return list;
   }

   public Item[] lastChoices(int historia){
       Cursor c = db.returnAllFinalesId(historia);
       Item [] list = new Item[c.getCount()];
       c.moveToFirst();
       int i=0;
       do {
           list[i] =  list[i] = getCard(c.getInt( c.getColumnIndex( "UltimaTarjeta" ) ),historia);
           Log.d( "DEBUG","Last choices: "+list[i].getDescription() +"Tarjet id: "+String.valueOf( c.getInt( c.getColumnIndex( "UltimaTarjeta" ))) );
           i++;
       } while(c.moveToNext());
       return list;
   }

}
