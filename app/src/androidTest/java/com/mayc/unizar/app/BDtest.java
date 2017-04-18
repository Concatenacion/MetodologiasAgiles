package com.mayc.unizar.app;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mayc.unizar.app.adapters.DbAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mayc.unizar.app.utils.SQLiteRelacional.KEY_IDINFO;
import static com.mayc.unizar.app.utils.SQLiteRelacional.UltimaTarjeta;
import static org.junit.Assert.*;

/**
 *  Created by Forton on 13/04/2017.
 */

@RunWith(AndroidJUnit4.class)
public class BDtest {

    DbAdapter db;

    @Test
    @Before
    public void onCreate() throws Exception {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        this.db= new DbAdapter(appContext);
        assertEquals("com.mayc.unizar.app", appContext.getPackageName());
    }

    @Test
    public void checkOpen(){
        db.open();
        assertTrue(db.isOpen());
        db.close();
    }

    @Test
    public void checkClose(){
        assertFalse(db.isOpen());
    }

    @Test
    public void checkCountHistories(){
        if(!db.isOpen())
            db.open();

        db.insertHistoria(1,"Prueba1","Terror", "Prueba terrorifica");
        db.insertHistoria(2,"Prueba2","Terror", "Prueba terrorifica");

        assertTrue(db.countHistories()==2);
        db.close();
    }

    @Test
    public void checkCountCards(){
        if(!db.isOpen())
            db.open();

        db.insertHistoria(1,"Prueba1","Terror", "Prueba terrorifica");
        db.insertTarjeta(1,"prueba1", "Foto","Cuerpo mu chulo mu bonico",1,"Vuelta atras",1, "VueltaAtras",1);
        db.insertTarjeta(2,"prueba2", "Foto","Cuerpo mu chulo mu bonico",1,"Vuelta atras",1, "VueltaAtras",1);

        assertTrue(db.countCards()==2);
        db.close();
    }

    @Test
    public void checkGetHistory(){
        if(!db.isOpen())
            db.open();

        db.insertHistoria(1,"Prueba1","Terror", "Prueba terrorifica");

        Cursor cur = db.getHistory(1);
        assertTrue(cur.getInt(cur.getColumnIndex(KEY_IDINFO))==1);
        db.close();
    }

    @Test
    public void checkReturnAllFromHistoria(){
        if(!db.isOpen())
            db.open();

        db.insertHistoria(1,"Prueba1","Terror", "Prueba terrorifica");
        db.insertTarjeta(1,"prueba1", "Foto","Cuerpo mu chulo mu bonico",1,"Vuelta atras",1, "VueltaAtras",1);
        db.insertTarjeta(2,"prueba2", "Foto","Cuerpo mu chulo mu bonico",1,"Vuelta atras",1, "VueltaAtras",1);

        Cursor cur = db.returnAllFromHistoria(1);
        assertTrue(cur.getCount()==2);

        db.close();
    }

    @Test
    public void checkReturnAllHistorias (){
        if(!db.isOpen())
            db.open();

        db.insertHistoria(1,"Prueba1","Terror", "Prueba terrorifica");
        db.insertHistoria(2,"Prueba1","Terror", "Prueba terrorifica");

        Cursor cur = db.returnAllHistorias();
        assertTrue(cur.getCount()==2);
        db.close();
    }

    @Test
    public void checkReturnTarjeta (){
        if(!db.isOpen())
            db.open();

        db.insertHistoria(1,"Prueba1","Terror", "Prueba terrorifica");
        db.insertTarjeta(1,"prueba1", "Foto","Cuerpo mu chulo mu bonico",1,"Vuelta atras",1, "VueltaAtras",1);

        Cursor cur = db.returnTarjeta(1,1);
        assertTrue(cur.getInt(cur.getColumnIndex(KEY_IDINFO))==1);

        db.close();
    }

    @Test
    public void checkUpdateLastCard(){
        if(!db.isOpen())
            db.open();

        db.insertHistoria(1,"Prueba1","Terror", "Prueba terrorifica");
        db.insertTarjeta(1,"prueba1", "Foto","Cuerpo mu chulo mu bonico",1,"Vuelta atras",1, "VueltaAtras",1);
        db.insertTarjeta(2,"prueba1", "Foto","Cuerpo mu chulo mu bonico",1,"Vuelta atras",1, "VueltaAtras",1);

        db.updateLastCard(1,1);

        Cursor cur = db.getHistory(1);
        assertTrue(cur.getInt(cur.getColumnIndex(UltimaTarjeta))==1);

        db.updateLastCard(1,2);
        cur = db.getHistory(1);
        assertTrue(cur.getInt(cur.getColumnIndex(UltimaTarjeta))==2);

        //Assert
        db.close();
    }

}
