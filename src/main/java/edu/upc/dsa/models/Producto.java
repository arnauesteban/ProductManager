package edu.upc.dsa.models;

public class Producto implements Comparable<Producto>{

    String nombre;
    float precio;
    int cantidad;
    int vendidos;

    public Producto(String nombre, float precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.vendidos = 0;
    }

    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.precio = 0;
        this.cantidad = cantidad;
        this.vendidos = 0;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getVendidos() { return vendidos; }

    public void setVendidos(int vendidos) { this.vendidos = vendidos; }

    //orden natural : por precio
    public int compareTo(Producto o) {
        return (int)(this.precio - o.getPrecio());
    }
}
