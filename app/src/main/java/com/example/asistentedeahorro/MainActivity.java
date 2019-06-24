package com.example.asistentedeahorro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;


class MainActivity extends AppCompatActivity {
    private TextView saldo_pesos;
    private TextView saldoFecha;
    private TextView total_i;
    private TextView total_e;
    private TextView total_TC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saldoFecha =(TextView) findViewById(R.id.saldoFecha);
        saldo_pesos=(TextView) findViewById(R.id.saldo_pesos);
        total_i = (TextView) findViewById(R.id.totIngresos);
        total_e = (TextView) findViewById(R.id.totEgresos);
        total_TC = (TextView) findViewById(R.id.totTC);
        Time hoy =new Time(Time.getCurrentTimezone());
        hoy.setToNow();
        int dia=hoy.monthDay;
        int mes=(hoy.month+1);
        String mesString="";
        switch (mes)
        {
            case 1:  mesString = "Enero";
                break;
            case 2:  mesString = "Febrero";
                break;
            case 3:  mesString = "Marzo";
                break;
            case 4:  mesString = "Abril";
                break;
            case 5:  mesString = "Mayo";
                break;
            case 6:  mesString = "Junio";
                break;
            case 7:  mesString = "Julio";
                break;
            case 8:  mesString = "Agosto";
                break;
            case 9:  mesString = "Septiembre";
                break;
            case 10: mesString = "Octubre";
                break;
            case 11:  mesString = "Noviembre";
                break;
            case 12:  mesString = "Diciembre";
                break;
        }
        String fechaActual=dia+" de "+mesString;
        saldoFecha.setText("Saldo al "+fechaActual);
        //ARMANDO CONSULTA EN BD PARA OBTENER LOS VALORES A LLENAR EN LOS CAMPOS SALDOPESOS,ING,EG,GTC
 //Llenar total en pesos
        AdminDB admin=new AdminDB(this,"BDmovimiento",null,1);
        SQLiteDatabase BD=admin.getWritableDatabase();
        String consulta="select (sum(monto)) as sumaTotal from movimientos where concepto != 'Crédito'";
        Cursor cursor = BD.rawQuery(consulta,null);
        if (cursor.moveToFirst()){
            float saldo_total=cursor.getFloat(cursor.getColumnIndex("sumaTotal"));
            String str_saldoTotal=Float.toString(saldo_total);
            saldo_pesos.setText("$"+str_saldoTotal);
        }
//Llenar total ingresos
        consulta="select (sum(monto)) as sumIngresos from movimientos where monto > 0";
        cursor = BD.rawQuery(consulta,null);
        if (cursor.moveToFirst()){
            float totIngresos=cursor.getFloat(cursor.getColumnIndex("sumIngresos"));
            String str_totIng=Float.toString(totIngresos);
            total_i.setText("$"+str_totIng);
        }
//Llenar total egresos
        consulta="select (sum(monto)) as sumEgresos from movimientos where (monto < 0) & (concepto != 'Crédito')";
        cursor = BD.rawQuery(consulta,null);
        if (cursor.moveToFirst()){
            float totEgresos=-(cursor.getFloat(cursor.getColumnIndex("sumEgresos")));
            String str_totEg=Float.toString(totEgresos);
            total_e.setText("$"+str_totEg);
        }
//Llenar total TC
        consulta="select (sum(monto)) as sumTC from movimientos where (monto < 0) & (concepto = 'Crédito')";
        cursor = BD.rawQuery(consulta,null);
        if (cursor.moveToFirst()){
            float totTC=-(cursor.getFloat(cursor.getColumnIndex("sumTC")));
            String str_totTC=Float.toString(totTC);
            total_TC.setText("$"+str_totTC);
        }

        BD.close();
    }
    public void act_ingresos(View view){
        Intent i=new Intent(this, Ingresos.class);
        startActivity(i);
    }
    public void act_egresos(View view){
        Intent i=new Intent(this, Egresos.class);
        startActivity(i);
    }
    public void act_detalles(View view){
        Intent i=new Intent(this, Detalles.class);
        startActivity(i);
    }
}
