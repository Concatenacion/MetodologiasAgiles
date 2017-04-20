package com.mayc.unizar.app.factories;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.mayc.unizar.app.adapters.DbAdapter;
import com.mayc.unizar.app.types.HistoryInfo;
import com.mayc.unizar.app.types.Item;
import com.mayc.unizar.app.views.History;

import static com.mayc.unizar.app.utils.SQLiteRelacional.Cuerpo;
import static com.mayc.unizar.app.utils.SQLiteRelacional.Foto;
import static com.mayc.unizar.app.utils.SQLiteRelacional.KEY_IDINFO;
import static com.mayc.unizar.app.utils.SQLiteRelacional.Tiempo;
import static com.mayc.unizar.app.utils.SQLiteRelacional.Titulo;

/**
 * Created by Forton on 15/04/2017.
 */

public class StoryFactory {


    public DbAdapter db;
    private static final String TAG = "StoryFactory";
    public StoryFactory(DbAdapter db){
        this.db=db;
    }

    public HistoryInfo getHistory(Context ctx, int id){

        Cursor cur = db.getHistory(id);

        if (cur!=null && cur.moveToFirst()){
            Log.d(TAG, "getHistory: Generating Story");
            return  new HistoryInfo(cur.getInt(cur.getColumnIndex(KEY_IDINFO)),
                    cur.getString(cur.getColumnIndex(Titulo)),cur.getString(cur.getColumnIndex(Foto)),"" +
                    "Caption",cur.getString(cur.getColumnIndex(Cuerpo)),cur.getString(cur.getColumnIndex(Tiempo)));
        }
        Log.w(TAG, "getHistory: Returning null");
        return new HistoryInfo(-1);
    }

    public HistoryInfo[] allHistories(){
        Cursor c = db.returnAllHistorias();
        HistoryInfo [] list = new HistoryInfo[c.getCount()];
        c.moveToFirst();
        int i=0;
        if(list.length!=0) {
            c.moveToFirst();
            do {
                list[i] = new HistoryInfo(c.getInt(0), c.getString(1), c.getString(3), c.getString(6), "Caption", c.getString(4));
                i++;
            } while (c.moveToNext());
        }
        return list;
    }


}


