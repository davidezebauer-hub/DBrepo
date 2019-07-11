package com.example.asistentedeahorro;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.ArrayList;

public class Detalles extends AppCompatActivity {
    ListView lista;
    ArrayList<ListDetalles> arrayDetalles;
    AdaptadorTabla adapter;
    //ArrayList<String>listaPrueba;
    private ListView tit_tabla1;
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

/*        tit_tabla1=(ListView)findViewById(R.id.tit_tabla1);
        ArrayList<String> lista_tit_tabla1=new ArrayList<>();
        lista_tit_tabla1.add("Fecha");
        lista_tit_tabla1.add("Tipo");
        lista_tit_tabla1.add("Monto");
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista_tit_tabla1);
        tit_tabla1.setAdapter(adaptador);*/

//Listado
        Cursor cursor= DB.rawQuery("select * from movimientos",null);
        lista=(ListView)findViewById(R.id.lista);
        arrayDetalles=new ArrayList<ListDetalles>();
        if(cursor.moveToFirst()){
            do {
                ListDetalles detalles=new ListDetalles(cursor.getString(1),cursor.getString(3),cursor.getFloat(2));
                arrayDetalles.add(detalles);
            }while(cursor.moveToNext());
        }
        adapter=new AdaptadorTabla(getApplicationContext(),arrayDetalles);
        lista.setAdapter(adapter);
        DB.close();
    }
    public void agregar_fila(){

    }
    public void crear_encabezado(){

    }
}
