package com.example.rebeca.diabetapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Rebeca on 20/04/2017.
 */
public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    String sqlCrearTabla= "CREATE TABLE Usuario(dni TEXT, nombre TEXT, password TEXT, peso TEXT, altura TEXT, imc TEXT)";
    String sqlCrearTabla2="CREATE TABLE Control(dni TEXT, fecha TEXT, hora TEXT, glucosa TEXT, categoria TEXT, carb TEXT, ejercicio TEXT, insulina TEXT, comentarios TEXT)";
    public UsuariosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(sqlCrearTabla);
            db.execSQL(sqlCrearTabla2);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE IF EXISTS Usuario");
            db.execSQL(sqlCrearTabla);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //synchronized SQLiteDatabase getReadableDatabase()
}
