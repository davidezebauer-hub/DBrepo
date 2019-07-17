package com.example.asistentedeahorro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Detalles extends AppCompatActivity {
    private TableLayout tabla1;
    private TableLayout tabla2;
    AdminDB Admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Admin=new AdminDB(this,"BDmovimiento",null,1);
        SQLiteDatabase DB=Admin.getReadableDatabase();

//Tabla1

        String consulta="select * from movimientos";
        Cursor cursor= DB.rawQuery(consulta,null);
        //tabla1
        tabla1=(TableLayout)findViewById(R.id.tabla1);
        int cont_fila=0;
        if(cursor.moveToFirst()){
            do{
                TableRow fila_t1=new TableRow(this);
                fila_t1.setId(100+cont_fila);
                TextView tv_col1=new TextView(this);
                tv_col1.setId(500+cont_fila);
                tv_col1.setText(cursor.getString(1));
                tv_col1.setPadding(5,5,5,5);
                tv_col1.setWidth(200);
                tv_col1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tv_col1.setBackground(this.getResources().getDrawable(R.drawable.marcotabla));
                TextView tv_col2=new TextView(this);
                tv_col2.setId(600+cont_fila);
                tv_col2.setText(cursor.getString(3));
                tv_col2.setPadding(5,5,5,5);
                tv_col2.setWidth(250);
                tv_col2.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                tv_col2.setBackground(this.getResources().getDrawable(R.drawable.marcotabla));
                TextView tv_col3=new TextView(this);
                tv_col3.setId(700+cont_fila);
                Float monto=cursor.getFloat(2);
                if(monto<0){
                    monto=-monto;
                    tv_col3.setTextColor(Color.RED);
                }else{
                    tv_col3.setTextColor(Color.GREEN);
                }
                String str_monto="$ "+String.format("%.2f",monto);
                tv_col3.setText(str_monto);
                tv_col3.setPadding(5,5,5,5);
                tv_col3.setWidth(200);
                tv_col3.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                tv_col3.setBackground(this.getResources().getDrawable(R.drawable.marcotabla));

                fila_t1.addView(tv_col1);
                fila_t1.addView(tv_col2);
                fila_t1.addView(tv_col3);

                tabla1.addView(fila_t1);
                cont_fila++;
            }while(cursor.moveToNext());

        }
        DB.close();
        //tabla2
        tabla2 = (TableLayout)findViewById(R.id.tabla2);
        Admin=new AdminDB(this,"BDmovimiento",null,1);
        DB=Admin.getReadableDatabase();
        String lista_cat[]=this.getResources().getStringArray(R.array.categoria);
        int fil=0;
        Float total;

        for(int i=0;i<lista_cat.length;i++){
            consulta="select (sum(monto)) as sumaTotal from movimientos where categoria = "+"'"+lista_cat[i]+"'";
            cursor= DB.rawQuery(consulta,null);
            if(cursor.moveToFirst()){
                total=-(cursor.getFloat(cursor.getColumnIndex("sumaTotal")));
                TableRow fila_t2=new TableRow(this);
                fila_t2.setId(200+fil);
                TextView tv_col1=new TextView(this);
                tv_col1.setId(300+fil);
                tv_col1.setText(lista_cat[i]+":");
                tv_col1.setPadding(5,5,5,5);
                tv_col1.setWidth(400);
                tv_col1.setBackground(this.getResources().getDrawable(R.drawable.marcotabla));
                TextView tv_col2=new TextView(this);
                tv_col1.setId(400+fil);
                tv_col2.setText("$ "+String.format("%.2f",total));
                tv_col2.setPadding(5,5,5,5);
                tv_col2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                tv_col2.setWidth(250);
                tv_col2.setBackground(this.getResources().getDrawable(R.drawable.marcotabla));
                fila_t2.addView(tv_col1);
                fila_t2.addView(tv_col2);
                tabla2.addView(fila_t2);
                fil++;
            }

        }

        DB.close();

    }
    public void volver(View view){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
