package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<Producto> listaProductos;
    private Usuario usuario;

    public Pedido(Usuario usuario) {
        this.listaProductos = new ArrayList<Producto>();
        this.usuario = usuario;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void anadirProducto(Producto producto)
    {
        listaProductos.add(producto);
    }
}
