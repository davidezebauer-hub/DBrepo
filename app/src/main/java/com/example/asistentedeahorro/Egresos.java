package com.example.asistentedeahorro;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
//    Calendar calendario=Calendar.getInstance();
  //  dia= calendario.get(Calendar.DAY_OF_MONTH);
    //mes= calendario.get(Calendar.MONTH)+1;
    //anio= calendario.get(Calendar.YEAR);
    //selecFecha();
/*    selectorFecha=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dia=dayOfMonth;
            mes=month;
            anio=year;
           selecFecha();

        }
    };*/
}

/*    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
            return new DatePickerDialog(this,selectorFecha,anio,mes,dia);

        }
        return null;
    }*/

/*    public void selecFecha(){
        tvFecha.setText(dia+"/"+mes+"/"+anio);
    }*/
 /*   public void mostrarCalendario(View control){
    showDialog(tipoDialogo);
    }*/
    public void act_principal(View view){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }
}


