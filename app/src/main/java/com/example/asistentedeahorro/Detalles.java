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
        ContentValues reg=new ContentValues();
        lista=(ListView)findViewById(R.id.lista);
        listaPrueba=new ArrayList<String>();
        int pos=0;
        //cursor.moveToFirst();
        while(cursor.moveToNext()){
            listaPrueba.add(pos,cursor.getString(1)+"-");
                pos++;
        }
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPrueba);
        lista.setAdapter(adaptador);
        DB.close();
    }
}
