package com.supermercado.model;

public class ProductoModel {
    private int id;
    private String nombre;
    private double precio;
    private int tiempo_pago;

    public ProductoModel() {
    }

    public ProductoModel(int id, String nombre, double precio, int tiempo_pago) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tiempo_pago = tiempo_pago;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTiempo_pago() {
        return tiempo_pago;
    }
    public void setTiempo_pago(int tiempo_pago) {
        this.tiempo_pago = tiempo_pago;
    }
}


