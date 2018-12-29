package com.example.rebeca.diabetapp;

import android.app.DialogFragment;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class VerControles extends AppCompatActivity {
ConexionBD conexion;
    String dni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_controles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView t=(TextView) findViewById(R.id.Escondido);

        conexion=new ConexionBD(this.getApplicationContext());
        final String []conjunto=getIntent().getExtras().getStringArray("dni");
        dni=conjunto[0];
        mostrarList(dni);
    }
    public void mostrarList(String dni){
        String[] columnasBD = new String[] {"_id","hora", "glucosa", "insulina", "categoria"};
        MatrixCursor cursor = new MatrixCursor(columnasBD);
        Calendar cal = new GregorianCalendar();
        Date date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
        String a=df.format(date);
        conexion.abrir();
        Cursor c=conexion.listarControles(dni);
        int i=0;
        if(c.moveToFirst()){
            do {
                cursor.addRow(new Object[] {i,c.getString(2),"Glucosa:"+c.getString(3),c.getString(4),"Insulina:"+c.getString(7)});
                i++;
                try {

                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }while(c.moveToNext());}

        String[] desdeEstasColumnas = {"hora", "glucosa", "insulina", "categoria"};
        int[] aEstasViews = { R.id.textView_Hora, R.id.textView_glucosa, R.id.textView_insulina, R.id.textView_categoria};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_controles_textos, cursor, desdeEstasColumnas, aEstasViews, 0);
        ListView listado=(ListView) findViewById(R.id.list);
        listado.setAdapter(adapter);

        conexion.cerrar();
    }

}
