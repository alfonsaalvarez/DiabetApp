package com.example.rebeca.diabetapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rebeca on 27/04/2017.
 */
public class HourPickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
        final Calendar c= Calendar.getInstance();
        int hora=c.get(Calendar.HOUR_OF_DAY);
        int minutos=c.get(Calendar.MINUTE);

        // Create a new instance of DatePickerDialog and return it

        return new TimePickerDialog(getActivity(),this, hora, minutos, android.text.format.DateFormat.is24HourFormat(getActivity()));
        }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Time t = new Time(hourOfDay, minute, 0);
        String hm=df.format(t);
        TextView tv= (TextView) getActivity().findViewById(R.id.textoHora);
        tv.setText(hm);
    }
}
