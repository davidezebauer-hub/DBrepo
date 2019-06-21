package com.example.asistentedeahorro;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Detalles extends AppCompatActivity {
    private ListView lista;
    ArrayList<String>listaPrueba;
    AdminDB Admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Admin=new AdminDB(this,"BDmovimiento",null,1);
        SQLiteDatabase DB=Admin.getReadableDatabase();
        Cursor cursor= DB.rawQuery("select * from movimientos",null);
        lista=(ListView)findViewById(R.id.lista);
        ArrayList<String> listaPrueba=new ArrayList<>();
        int pos=0;
        if(cursor.moveToFirst()){
            do {
                String str_monto=Float.toString(cursor.getFloat(2));
                String text_fila=cursor.getString(0)+cursor.getString(1)+str_monto+cursor.getString(3);
                listaPrueba.add(text_fila);
               // pos++;

            }while(cursor.moveToNext());
        }
/*        while(cursor.moveToNext()){
            String str_monto=Float.toString(cursor.getFloat(2));
            String text_fila=cursor.getString(0)+cursor.getString(1)+str_monto+cursor.getString(3);
            listaPrueba.add(pos,text_fila);
            pos++;
        }
  */
/*
        for(int i=0;i<15;i++){
            String str_monto=Float.toString(cursor.getFloat(2));
            String text_fila=cursor.getString(0)+cursor.getString(1)+str_monto+cursor.getString(3);
            listaPrueba.add(i,text_fila);
            cursor.moveToNext();
        }*/
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPrueba);
        lista.setAdapter(adaptador);
        DB.close();
    }
}
