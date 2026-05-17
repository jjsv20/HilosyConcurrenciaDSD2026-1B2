package com.supermercado.model;

import java.util.ArrayList;
import java.util.List;

public class ClienteModel {
    private int id;
    private String nombre;
    private List<ClienteProductoModel> productos = new ArrayList<>();

    public ClienteModel() {
    }

    public ClienteModel(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public List<ClienteProductoModel> getProductos() {
        return productos;
    }

    public void setProductos(List<ClienteProductoModel> productos) {
        this.productos = productos;
    }

    public void agregarProducto(ClienteProductoModel producto) {
        productos.add(producto);
    }
}
