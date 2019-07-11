package com.example.asistentedeahorro;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class AdaptadorTabla extends BaseAdapter {
    ArrayList<ListDetalles> itemLista =new ArrayList<ListDetalles>();
    Context                 context;
    public AdaptadorTabla(Context c,ArrayList<ListDetalles>arrayDetalles){
        context= c;
        arrayDetalles=itemLista;
    }

    @Override
    public int getCount(){return itemLista.size();}
    @Override
    public Object getItem(int position){return itemLista.get(position);}
    @Override
    public long getItemId(int position){return position;}
    @Override
    public View getView(int position, View arg1, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate(R.layout.items_tabla,parent,false);
        TextView lblFecha=(TextView)itemView.findViewById(R.id.lblFecha);
        TextView lblTipo=(TextView)itemView.findViewById(R.id.lblTipo);
        TextView lblMonto=(TextView)itemView.findViewById(R.id.lblMonto);
        lblFecha.setText(""+itemLista.get(position).getList_fecha());
        lblTipo.setText(itemLista.get(position).getList_tipo());
        lblMonto.setText(itemLista.get(position).getList_monto().toString());
        itemView.setBackgroundColor(Color.argb(255,250,250,250));
        return itemView;
    }
}
