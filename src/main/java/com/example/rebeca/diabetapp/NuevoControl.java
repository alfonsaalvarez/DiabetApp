package com.example.rebeca.diabetapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class NuevoControl extends AppCompatActivity {
ConexionBD conexionBD;
    String dni;
    String []conjunto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conjunto = getIntent().getExtras().getStringArray("eldni");
        dni = conjunto[0];
        setContentView(R.layout.activity_nuevo_control);
        Button bfecha = (Button) findViewById(R.id.fecha);
        Button bhora = (Button) findViewById(R.id.hora);
        Button bguardar = (Button) findViewById(R.id.buttonGuardar);
        final TextView tfecha = (TextView) findViewById(R.id.textoFecha);
        final TextView thora = (TextView) findViewById(R.id.textoHora);
        final EditText tglucosa = (EditText) findViewById(R.id.gluc);
        final EditText tcoment = (EditText) findViewById(R.id.coment);
        final EditText tcarb = (EditText) findViewById(R.id.carb);


        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoria, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.ejercicio, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        final Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.insulina, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);


        final int vista = bfecha.getBottom();

        bfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        bhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HourPickerFragment newHour = new HourPickerFragment();
                newHour.show(getFragmentManager(), "timePicker");

            }
        });
        bguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = String.valueOf(tfecha.getText());
                String hora = String.valueOf(thora.getText());
                String coment = String.valueOf(tcoment.getText());
                String carb = String.valueOf(tcarb.getText());
                String gluc = String.valueOf(tglucosa.getText());
                String categoria = spinner.getSelectedItem().toString();
                String ejercicio = spinner2.getSelectedItem().toString();
                String insulina = spinner3.getSelectedItem().toString();
                Log.d("prueba", fecha + hora + coment + carb + gluc + categoria + ejercicio);
                try {
                    conexionBD = new ConexionBD(getApplicationContext());
                    conexionBD.abrir();
                    conexionBD.insertarControl(dni, fecha, hora, gluc, categoria, carb, ejercicio, insulina, coment);
                    conexionBD.cerrar();
                } catch (Exception e) {
                    Log.d("aaaaa", e.getMessage());
                }

                tfecha.setText("");
                thora.setText("");
                tcoment.setText("");
                tcarb.setText("");
                tglucosa.setText("");
                Toast t = null;
                t.makeText(getApplicationContext(), "Añadida correctamente", Toast.LENGTH_SHORT);


            }
        });


    }
}
