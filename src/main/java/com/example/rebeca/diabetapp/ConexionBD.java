package com.example.rebeca.diabetapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rebeca on 27/04/2017.
 */
public class ConexionBD {
    static final String NOMBRE_BD = "BBDDG";
    private UsuariosSQLiteHelper dbHelper;
    private SQLiteDatabase db;


    /*Constructor*/
    public ConexionBD(Context ctx) {
        dbHelper = new UsuariosSQLiteHelper(ctx, NOMBRE_BD, null, 1);
    }

    /*Abre la conexión con la base de datos*/
    public ConexionBD abrir() throws  SQLException{
        db = dbHelper.getWritableDatabase();
        return this;
    }


    /*Cierra la conexión con la base de datos*/
    public void cerrar() {
        if (db != null) db.close();
    }
    public void insertarUsuario(String dni,String nombre, String password, String peso, String altura, String imc )
    {
        String consultaSQL= "INSERT INTO Usuario VALUES('"+dni+"','"+nombre+"','"+password+"','"+peso+"','"+altura+"','"+imc+"')";
        db.execSQL(consultaSQL);
    }
    public void insertarControl(String dni, String fecha, String hora, String glucosa,String categoria, String carb, String ejercicio,String insulina, String comentarios){
        String consultaSQL="INSERT INTO Control VALUES('"+dni+"','"+fecha+"','"+hora+"','"+glucosa+"','"+categoria+"','"+carb+"','"+ejercicio+"','"+insulina+"','"+comentarios+"')";
        db.execSQL(consultaSQL);
    }
    /*devuelve todos los contactos*/
    public Cursor listarUsuario()
    {
        return db.rawQuery("SELECT * FROM Usuario",null);
    }
    public Cursor listarControles(String dni){
        return  db.rawQuery("SELECT * FROM Control WHERE dni='"+dni+"' ORDER BY hora", null);
    }
    public Cursor listarGrafica(String dni){
        return  db.rawQuery("SELECT * FROM Control WHERE dni='"+dni+"' ORDER BY fecha, hora", null);
    }

    public Cursor SiExiste(String dni, String password){
        return db.rawQuery("SELECT * FROM Usuario WHERE dni='"+dni+"' and password='"+password+"'", null);
    }
}

