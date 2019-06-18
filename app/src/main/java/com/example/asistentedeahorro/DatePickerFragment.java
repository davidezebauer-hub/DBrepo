package com.example.asistentedeahorro;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c=Calendar.getInstance();
        int anio=c.get(Calendar.YEAR);
        int mes=c.get(Calendar.MONTH)+1;
        int dia=c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,anio,mes,dia);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
