package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    String nombre;
    String ID;
    List<Pedido> listaRealizados;

    public Usuario (String id, String nombre) {
        this.nombre = nombre;
        this.ID = id;
        listaRealizados = new ArrayList<Pedido>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pedido> getListaRealizados() {
        return listaRealizados;
    }

    public void setListaRealizados(List<Pedido> listaRealizados) {
        this.listaRealizados = listaRealizados;
    }

    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }

}
