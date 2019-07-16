package com.example.asistentedeahorro;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Detalles extends AppCompatActivity {
    ListView lista;
    ArrayList<ListDetalles> arrayDetalles;
    AdaptadorTabla adapter;
    //ArrayList<String>listaPrueba;
    private ListView tit_tabla1;
    private TableLayout tabla2;
 //   private TableLayout tabla1;
    AdminDB Admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Admin=new AdminDB(this,"BDmovimiento",null,1);
        SQLiteDatabase DB=Admin.getReadableDatabase();
 /*       Cursor cursor= DB.rawQuery("select * from movimientos",null);
        lista=(ListView)findViewById(R.id.lista);
        ArrayList<String> listaPrueba=new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                String str_monto=Float.toString(cursor.getFloat(2));
                String text_fila=cursor.getString(0)+cursor.getString(1)+
                        str_monto+cursor.getString(3);
                listaPrueba.add(text_fila);
              }while(cursor.moveToNext());
        }
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPrueba);
        lista.setAdapter(adaptador);*/

        tit_tabla1=(ListView)findViewById(R.id.tit_tabla1);

//Listado
        String consulta="select * from movimientos";
        Cursor cursor= DB.rawQuery(consulta,null);
        lista=(ListView)findViewById(R.id.lista);
        arrayDetalles=new ArrayList<ListDetalles>();
        if(cursor.moveToFirst()){
            do {
                String fecha=cursor.getString(1);
                String tipo=cursor.getString(3);
                String monto=Float.toString(cursor.getFloat(2));
                //ListDetalles detalles=new ListDetalles(cursor.getString(1),cursor.getString(3),cursor.getFloat(2));
                ListDetalles detalles=new ListDetalles(fecha,tipo,monto);
                arrayDetalles.add(detalles);
            }while(cursor.moveToNext());
        }
        adapter=new AdaptadorTabla(getApplicationContext(),arrayDetalles);
        lista.setAdapter(adapter);
        DB.close();

        //tabla2
        tabla2 = (TableLayout)findViewById(R.id.tabla2);
        Admin=new AdminDB(this,"BDmovimiento",null,1);
        DB=Admin.getReadableDatabase();
        String lista_cat[]=this.getResources().getStringArray(R.array.categoria);
/*        int fil=0;
        Float total=0f;
        for(int i=0;i<lista_cat.length;i++){
            consulta="select (sum(monto)) as sumaTotal from movimientos where categoria = "+lista_cat[i];
            cursor= DB.rawQuery(consulta,null);

            if(cursor.moveToFirst()){
                total=-(cursor.getFloat(cursor.getColumnIndex("sumaTotal")));
                TableRow fila=new TableRow(this);
                fila.setId(100+fil);
                TextView tv_col1=new TextView(this);
                tv_col1.setId(200+fil);
                tv_col1.setText(lista_cat[i]+":");
                tv_col1.setPadding(100,10,10,0);
                tv_col1.setWidth(500);
                TextView tv_col2=new TextView(this);
                tv_col2.setText("$ "+String.format("%.2f",total));
                tv_col2.setPadding(10,10,10,0);
                fila.addView(tv_col1);
                fila.addView(tv_col2);
                tabla2.addView(fila);
                fil++;
            }
        }*/


        //fila Alquiler;
        consulta="select (sum(monto)) as sumaTotal from movimientos where categoria = 'Alquiler'";
        cursor= DB.rawQuery(consulta,null);

        int fil=0;
        if(cursor.moveToFirst()){
            //resultado=true;
            float total=-(cursor.getFloat(cursor.getColumnIndex("sumaTotal")));
            TableRow fila=new TableRow(this);
            fila.setId(100+fil);
            TextView tv_col1=new TextView(this);
            tv_col1.setId(200+fil);
            tv_col1.setText("Alquiler:");
            tv_col1.setPadding(100,10,10,0);
            tv_col1.setWidth(500);
            TextView tv_col2=new TextView(this);
            tv_col2.setText("$ "+String.format("%.2f",total));
            tv_col2.setPadding(10,10,10,0);
            fila.addView(tv_col1);
            fila.addView(tv_col2);
            tabla2.addView(fila);
            fil++;
        }
        //fila Mercado
        consulta="select (sum(monto)) as sumaTotal from movimientos where categoria = 'Mercado'";
        cursor= DB.rawQuery(consulta,null);
        if(cursor.moveToFirst()){
            //resultado=true;
            float total=-(cursor.getFloat(cursor.getColumnIndex("sumaTotal")));
            TableRow fila=new TableRow(this);
            fila.setId(100+fil);
            TextView tv_col1=new TextView(this);
            tv_col1.setId(200+fil);
            tv_col1.setText("Mercado:");
            tv_col1.setPadding(100,10,10,0);
            tv_col1.setWidth(500);
            TextView tv_col2=new TextView(this);
            tv_col2.setText("$ "+String.format("%.2f",total));
            tv_col2.setPadding(10,10,10,0);
            fila.addView(tv_col1);
            fila.addView(tv_col2);
            tabla2.addView(fila);
            fil++;
        }
        //fila Transporte
        consulta="select (sum(monto)) as sumaTotal from movimientos where categoria = 'Transporte'";
        cursor= DB.rawQuery(consulta,null);
        if(cursor.moveToFirst()){
            //resultado=true;
            float total=-(cursor.getFloat(cursor.getColumnIndex("sumaTotal")));
            TableRow fila=new TableRow(this);
            fila.setId(100+fil);
            TextView tv_col1=new TextView(this);
            tv_col1.setId(200+fil);
            tv_col1.setText("Transporte:");
            tv_col1.setPadding(100,10,10,0);
            tv_col1.setWidth(500);
            TextView tv_col2=new TextView(this);
            tv_col2.setText("$ "+String.format("%.2f",total));
            tv_col2.setPadding(10,10,10,0);
            fila.addView(tv_col1);
            fila.addView(tv_col2);
            tabla2.addView(fila);
            fil++;
        }
        //fila Impuestos
        consulta="select (sum(monto)) as sumaTotal from movimientos where categoria = 'Impuestos'";
        cursor= DB.rawQuery(consulta,null);
        if(cursor.moveToFirst()){
            //resultado=true;
            float total=-(cursor.getFloat(cursor.getColumnIndex("sumaTotal")));
            TableRow fila=new TableRow(this);
            fila.setId(100+fil);
            TextView tv_col1=new TextView(this);
            tv_col1.setId(200+fil);
            tv_col1.setText("Impuestos:");
            tv_col1.setPadding(100,10,10,0);
            tv_col1.setWidth(500);
            TextView tv_col2=new TextView(this);
            tv_col2.setText("$ "+String.format("%.2f",total));
            tv_col2.setPadding(10,10,10,0);
            fila.addView(tv_col1);
            fila.addView(tv_col2);
            tabla2.addView(fila);
            fil++;
        }
        //fila Servicios
        consulta="select (sum(monto)) as sumaTotal from movimientos where categoria = 'Servicios'";
        cursor= DB.rawQuery(consulta,null);
        if(cursor.moveToFirst()){
            //resultado=true;
            float total=-(cursor.getFloat(cursor.getColumnIndex("sumaTotal")));
            TableRow fila=new TableRow(this);
            fila.setId(100+fil);
            TextView tv_col1=new TextView(this);
            tv_col1.setId(200+fil);
            tv_col1.setText("Servicios:");
            tv_col1.setPadding(100,10,10,0);
            tv_col1.setWidth(500);
            TextView tv_col2=new TextView(this);
            tv_col2.setText("$ "+String.format("%.2f",total));
            tv_col2.setPadding(10,10,10,0);
            fila.addView(tv_col1);
            fila.addView(tv_col2);
            tabla2.addView(fila);
            fil++;
        }
        //fila Esparcimientos
        consulta="select (sum(monto)) as sumaTotal from movimientos where categoria = 'Esparcimientos'";
        cursor= DB.rawQuery(consulta,null);
        if(cursor.moveToFirst()){
            //resultado=true;
            float total=-(cursor.getFloat(cursor.getColumnIndex("sumaTotal")));
            TableRow fila=new TableRow(this);
            fila.setId(100+fil);
            TextView tv_col1=new TextView(this);
            tv_col1.setId(200+fil);
            tv_col1.setText("Esparcimientos:");
            tv_col1.setPadding(100,10,10,0);
            tv_col1.setWidth(500);
            TextView tv_col2=new TextView(this);
            tv_col2.setText("$ "+String.format("%.2f",total));
            tv_col2.setPadding(10,10,10,0);
            fila.addView(tv_col1);
            fila.addView(tv_col2);
            tabla2.addView(fila);
            fil++;
        }
        //fila Otros
        consulta="select (sum(monto)) as sumaTotal from movimientos where categoria = 'Otros'";
        cursor= DB.rawQuery(consulta,null);
        if(cursor.moveToFirst()){
            //resultado=true;
            float total=-(cursor.getFloat(cursor.getColumnIndex("sumaTotal")));
            TableRow fila=new TableRow(this);
            fila.setId(100+fil);
            TextView tv_col1=new TextView(this);
            tv_col1.setId(200+fil);
            tv_col1.setText("Otros:");
            tv_col1.setPadding(100,10,10,0);
            tv_col1.setWidth(500);
            TextView tv_col2=new TextView(this);
            tv_col2.setText("$ "+String.format("%.2f",total));
            tv_col2.setPadding(10,10,10,0);
            fila.addView(tv_col1);
            fila.addView(tv_col2);
            tabla2.addView(fila);
            fil++;
        }

        DB.close();

    }
    public void volver(View view){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
