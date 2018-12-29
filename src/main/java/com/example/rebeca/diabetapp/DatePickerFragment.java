package com.example.rebeca.diabetapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rebeca on 27/04/2017.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
        }

public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
    Date fecha = new Date(year,month,day);

    TextView tv= (TextView) getActivity().findViewById(R.id.textoFecha);
    String a=df.format(fecha);
    tv.setText(String.valueOf(a));

        }
}
