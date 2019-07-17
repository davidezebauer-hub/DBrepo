package com.example.asistentedeahorro;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.Toast;


public class Ingresos extends AppCompatActivity {
private TextView monto,fecha_selec;
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
    public void registrar(View view){
        if(!monto.getText().toString().isEmpty()){
            AdminDB admin=new AdminDB(this,"BDmovimiento",null,1);
            SQLiteDatabase BD=admin.getWritableDatabase();
            String fecha_DB=fecha_selec.getText().toString();
            float monto_DB=Float.parseFloat(monto.getText().toString());
            monto_DB=Math.round(monto_DB*100)/100f;
            String concepto_DB=concepto.getSelectedItem().toString();
            String categoria_DB="";

            ContentValues registro=new ContentValues();
            registro.put("fecha", fecha_DB);
            registro.put("monto",monto_DB);
            registro.put("concepto",concepto_DB);
            registro.put("categoria",categoria_DB);
            BD.insert("movimientos",null,registro);
            monto.setText("");
            Toast.makeText(this,"El registro se realizó correctamente",Toast.LENGTH_LONG).show();
            BD.close();

        }else{
            Toast.makeText(this,"Debe completar el campo Monto",Toast.LENGTH_LONG).show();
        }
    }
}
