package com.example.rebeca.diabetapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
ConexionBD conexionBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button bregis=(Button) findViewById(R.id.bregistrar);
        final EditText edni=(EditText) findViewById(R.id.dni);
        final EditText enombre=(EditText) findViewById(R.id.nombre);
        final EditText epas=(EditText) findViewById(R.id.pass);
        final EditText epeso=(EditText) findViewById(R.id.peso);
        final EditText ealtura=(EditText) findViewById(R.id.altura);

        bregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni=edni.getText().toString();
                String nombre=enombre.getText().toString();
                String pass=epas.getText().toString();
                int peso=Integer.parseInt(epeso.getText().toString());
                int altura=Integer.parseInt(ealtura.getText().toString());
                int im=peso/(altura*altura);
                String pes=String.valueOf(peso);
                String alt=String.valueOf(altura);
                String imc=String.valueOf(im);
                conexionBD=new ConexionBD(getApplicationContext());
                conexionBD.abrir();
                conexionBD.insertarUsuario(dni, nombre, pass, pes, alt, imc);
                conexionBD.cerrar();
                Toast.makeText(getApplicationContext(), "Registrado correctamente", Toast.LENGTH_SHORT).show();
                edni.setText("");
                enombre.setText("");
                epas.setText("");
                epeso.setText("");
                ealtura.setText("");
            }
        });
    }
}
