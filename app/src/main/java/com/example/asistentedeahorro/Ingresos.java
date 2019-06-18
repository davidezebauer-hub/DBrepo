package com.example.asistentedeahorro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

public class Ingresos extends AppCompatActivity {
private TextView monto;
private Spinner concepto;
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
    }
    public void abrir_calendario(View view){
        DatePickerFragment newFragment=new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    public void act_principal(View view){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
