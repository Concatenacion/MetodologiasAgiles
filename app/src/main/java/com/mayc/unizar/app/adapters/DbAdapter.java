package com.mayc.unizar.app.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.mayc.unizar.app.utils.SQLiteRelacional;



public class DbAdapter extends SQLiteRelacional {

    private final Context mCtx;
    private DatabaseHelper mDbHelper;
    private static SQLiteDatabase mDb;
    private static final String TAG = " DBADAPTER";

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public DbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     * initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public boolean isOpen() {
        return mDb.isOpen();
    }

    public void close() {
        mDbHelper.close();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE_HISTORIAS);
            db.execSQL(DATABASE_CREATE_TARJETAS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("DbAdapter", "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS DATABASE_TABLE_HISTORIAS");
            db.execSQL("DROP TABLE IF EXISTS DATABASE_CREATE_TARJETAS");
            onCreate(db);
        }
    }


    public long insertHistoria(int id, String titulo, String genero, String cuerpo) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_IDINFO, id);
        initialValues.put(Titulo, titulo);
        initialValues.put(Genero, genero);
        initialValues.put(Cuerpo, cuerpo);
        try {
            return mDb.insertOrThrow(DATABASE_TABLE_HISTORIAS, null, initialValues);
        } catch (SQLiteConstraintException e) {
            return 0;
        }
    }


    public long insertTarjeta(int id, String Nombre, String Foto, String cuerpo, int derecha, String opcionD,  int izquierda, String opcionI, int historia) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_IDINFO, id);
        initialValues.put(Nombre, Nombre);
        initialValues.put(Foto, Foto);
        initialValues.put(Cuerpo, cuerpo);
        initialValues.put(Derecha, derecha);
        initialValues.put(OpcionDerecha, opcionD);
        initialValues.put(Izquierda,izquierda);
        initialValues.put(OpcionIzquierda, opcionI);
        initialValues.put(HISTORIA,historia);
        try {
            return mDb.insertOrThrow(DATABASE_TABLE_HISTORIAS, null, initialValues);
        } catch (SQLiteConstraintException e) {
            return 0;
        }
    }
    public Cursor returnAllHistorias() {
        return mDb.query(DATABASE_TABLE_HISTORIAS, null,null,null,null,null,null);
    }

    public Cursor returnAllFromHistoria(int id) {
        return mDb.query(DATABASE_TABLE_TARJETAS, null,HISTORIA+ "=" + id,null,null,null,null);
    }


    public Cursor returnTarjeta(int historia, int id){
        return mDb.query(DATABASE_TABLE_TARJETAS, null,HISTORIA+ "=" + historia + " AND " + KEY_IDINFO + "=" + id,null,null,null,null);
    }

    public void updateLastCard(int Historia, int id){
        mDb.execSQL("UPDATE "+DATABASE_TABLE_HISTORIAS+" SET "+UltimaTarjeta+"="+id+" WHERE "+KEY_IDINFO+"="+Historia);
    }
}

