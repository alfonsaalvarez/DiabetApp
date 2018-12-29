package com.example.rebeca.diabetapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ActivityMain extends AppCompatActivity{
    ConexionBD conectorBD;
    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String [] conjunto=getIntent().getExtras().getStringArray("valores");
        barChart=(BarChart) findViewById(R.id.grafica);

        conectorBD=new ConexionBD(this.getApplicationContext());
        conectorBD.abrir();
        Cursor c=conectorBD.listarGrafica(conjunto[0]);
        ArrayList<BarEntry> bar=new ArrayList<>();
        ArrayList<String> dias=new ArrayList<>();
        int i=0;

        if(c.moveToFirst()) {
            do{
                float f=Float.parseFloat(c.getString(3));
                bar.add(new BarEntry(f,i));
                dias.add("");
                i++;
            }while(c.moveToNext());}
        BarDataSet barDataSet=new BarDataSet(bar, "Glucosa");
        BarData barData=new BarData(dias,barDataSet);
        barChart.setData(barData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setDescription("");

        final String dni=conjunto[0];
        final String nombre=conjunto[1];
        final String peso=conjunto[3];
        final String altura=conjunto[4];
        final String imc=conjunto[5];



        Button boton2=(Button)findViewById(R.id.buttonVer) ;
        Button boton1 = (Button)findViewById(R.id.buttonNew);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamar(conjunto);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerControles(conjunto);
            }
        });
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        //String nombre=getIntent().getExtras().getString("string");
        //Toast toas= Toast.makeText(this, "Conectado "+nombre, Toast.LENGTH_SHORT );
    }
    public void VerControles(String [] conjunto){
        Intent i =new Intent(this, VerControles.class);
        i.putExtra("dni", conjunto);
        startActivity(i);
    }
    public void llamar(String[] conjunto){
        Intent i=new Intent(this, NuevoControl.class);
        i.putExtra("eldni", conjunto);
        startActivity(i);
    }



}
