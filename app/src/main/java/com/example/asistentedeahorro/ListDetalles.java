package com.example.asistentedeahorro;

class ListDetalles {

    public ListDetalles(String list_fecha, String list_tipo, String list_monto){
        this.list_fecha=list_fecha;
        this.list_tipo=list_tipo;
        this.list_monto=list_monto;

    }
    public String getList_fecha() {
        return list_fecha;
    }

    public void setList_fecha(String list_fecha) {
        this.list_fecha = list_fecha;
    }

    public String getList_tipo() {
        return list_tipo;
    }

    public void setList_tipo(String list_tipo) {
        this.list_tipo = list_tipo;
    }

    public String getList_monto() {
        return list_monto;
    }

    public void setList_monto(String list_monto) {
        this.list_monto = list_monto;
    }
    String list_fecha;
    String list_tipo;
    String  list_monto;

}

