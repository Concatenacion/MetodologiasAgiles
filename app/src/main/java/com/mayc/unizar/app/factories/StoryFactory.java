package com.mayc.unizar.app.factories;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.mayc.unizar.app.adapters.DbAdapter;
import com.mayc.unizar.app.types.HistoryInfo;
import com.mayc.unizar.app.views.History;

import static com.mayc.unizar.app.utils.SQLiteRelacional.Cuerpo;
import static com.mayc.unizar.app.utils.SQLiteRelacional.Foto;
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

    public History getHistory(Context ctx, int id){
        HistoryInfo hsinfo = new HistoryInfo();
        Cursor cur = db.getHistory(id);

        if (cur!=null && cur.moveToFirst()){
            Log.d(TAG, "getHistory: Generating Story");
            hsinfo.setTitle(cur.getString(cur.getColumnIndex(Titulo)));
            hsinfo.setImageUrl(cur.getString(cur.getColumnIndex(Foto)));
            hsinfo.setCaption("Caption");
            hsinfo.setBody(cur.getString(cur.getColumnIndex(Cuerpo)));
            hsinfo.setTime(cur.getString(cur.getColumnIndex(Tiempo)));
        }
        return new History(ctx,hsinfo);
    }

}


