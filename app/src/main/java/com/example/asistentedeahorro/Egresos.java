package com.example.asistentedeahorro;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Egresos extends AppCompatActivity {
private EditText monto;
private Spinner concepto, categorias;
private int dia, mes, anio;
private static DatePickerDialog.OnDateSetListener selectorFecha;
private static final int tipoDialogo=0;
private TextView tvFecha;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egresos);
        monto=(EditText) findViewById(R.id.monto);
        concepto=(Spinner)findViewById(R.id.sp_concepto);
        ArrayAdapter<CharSequence> adap=ArrayAdapter.createFromResource(this,R.array.conceptoE,
            android.R.layout.simple_spinner_item);
        concepto.setAdapter(adap);
        categorias=(Spinner)findViewById(R.id.categorias);
        ArrayAdapter<CharSequence> adapCat=ArrayAdapter.createFromResource(this,R.array.categoria,
            android.R.layout.simple_spinner_item);
        categorias.setAdapter(adapCat);
        tvFecha=(TextView)findViewById(R.id.tvFecha);
    Time hoy =new Time(Time.getCurrentTimezone());
    hoy.setToNow();
    dia=hoy.monthDay;
    mes=hoy.month;
    anio=hoy.year;
    mostrar_fecha(dia,mes,anio);
}

    private void mostrar_fecha(int day, int month, int year) {
        tvFecha.setText(dosDig(day)+"/"+dosDig(month+1)+"/"+year);
    }

    private String dosDig(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    public void abrir_calendario(View view){
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dia=day;
                mes=month;
                anio=year;
                mostrar_fecha(dia,mes,anio);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void act_principal(View view){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }
}


