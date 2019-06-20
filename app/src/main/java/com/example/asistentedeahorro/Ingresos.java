package com.example.asistentedeahorro;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.DatePicker;

public class Ingresos extends AppCompatActivity {
private TextView monto,fecha_selec;
private Spinner concepto;
private DatePicker datePicker;
private int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos);

        monto=(TextView)findViewById(R.id.monto);

        concepto=(Spinner)findViewById(R.id.sp_concepto);
        ArrayAdapter<CharSequence>adap=ArrayAdapter.createFromResource(this,R.array.conceptoI,
                android.R.layout.simple_spinner_item);
        concepto.setAdapter(adap);

        fecha_selec=(TextView)findViewById(R.id.fecha_selec);
        Time hoy =new Time(Time.getCurrentTimezone());
        hoy.setToNow();
        dia=hoy.monthDay;
        mes=hoy.month;
        anio=hoy.year;
        mostrar_fecha(dia,mes,anio);

    }

    public void mostrar_fecha(int day, int month, int year) {
        fecha_selec.setText(dosDig(day)+"/"+dosDig(month+1)+"/"+year);
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
