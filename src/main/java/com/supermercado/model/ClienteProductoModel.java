package com.supermercado.model;

public class ClienteProductoModel {

    private ProductoModel producto;
    private int cantidad;

    public ClienteProductoModel(ProductoModel producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}