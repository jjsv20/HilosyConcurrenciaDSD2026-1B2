package com.supermercado;

import com.supermercado.cola.ColaClientes;
import com.supermercado.hilos.CajaThread;
import com.supermercado.model.*;
import com.supermercado.utils.Colors;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Colors.YELLOW + Colors.BOLD +
                "+======================================+\n" +
                "|      SIMULACION SUPERMERCADO         |\n" +
                "+======================================+" + Colors.RESET);

        // ── Productos ─────────────────────────────────────────────────── //
        ProductoModel leche   = new ProductoModel(1, "Leche",   5_000, 1);
        ProductoModel pan     = new ProductoModel(2, "Pan",     3_000, 1);
        ProductoModel arroz   = new ProductoModel(3, "Arroz",   7_000, 1);
        ProductoModel huevos  = new ProductoModel(4, "Huevos", 12_000, 1);
        ProductoModel jugo    = new ProductoModel(5, "Jugo",    4_000, 1);
        ProductoModel galleta = new ProductoModel(6, "Galleta", 2_000, 1);

        // ── 6 clientes, 2 cajas → siempre hay cola ────────────────────── //
        ClienteModel c1 = new ClienteModel(1, "Manuel");
        c1.agregarProducto(new ClienteProductoModel(leche, 2));
        c1.agregarProducto(new ClienteProductoModel(pan,   3));

        ClienteModel c2 = new ClienteModel(2, "Sofia");
        c2.agregarProducto(new ClienteProductoModel(arroz,  1));
        c2.agregarProducto(new ClienteProductoModel(huevos, 2));

        ClienteModel c3 = new ClienteModel(3, "Andres");
        c3.agregarProducto(new ClienteProductoModel(pan,   5));
        c3.agregarProducto(new ClienteProductoModel(leche, 1));

        ClienteModel c4 = new ClienteModel(4, "Laura");
        c4.agregarProducto(new ClienteProductoModel(jugo,    3));
        c4.agregarProducto(new ClienteProductoModel(galleta, 2));

        ClienteModel c5 = new ClienteModel(5, "Carlos");
        c5.agregarProducto(new ClienteProductoModel(huevos, 1));
        c5.agregarProducto(new ClienteProductoModel(arroz,  2));

        ClienteModel c6 = new ClienteModel(6, "Valeria");
        c6.agregarProducto(new ClienteProductoModel(leche,   1));
        c6.agregarProducto(new ClienteProductoModel(galleta, 4));

        ColaClientes cola = new ColaClientes();
        cola.agregarCliente(c1);
        cola.agregarCliente(c2);
        cola.agregarCliente(c3);
        cola.agregarCliente(c4);
        cola.agregarCliente(c5);
        cola.agregarCliente(c6);
        cola.cerrar();

        // Mostrar cola inicial UNA sola vez
        System.out.println(Colors.YELLOW + "Cola inicial cargada:" + Colors.RESET);
        ColaClientes.imprimirCola(cola.obtenerNombres());

        CajaThread caja1 = new CajaThread(new CajaModel(1, "ANA"),   cola);
        CajaThread caja2 = new CajaThread(new CajaModel(2, "MARIA"), cola);

        long inicio = System.currentTimeMillis();
        caja1.start();
        caja2.start();
        caja1.join();
        caja2.join();
        long fin = System.currentTimeMillis();

        System.out.println(Colors.GREEN + Colors.BOLD +
                "+======================================+\n" +
                "|       SIMULACION FINALIZADA          |\n" +
                "|  TIEMPO TOTAL: " + ((fin - inicio) / 1000) + "s" +
                "                     |\n" +
                "+======================================+" + Colors.RESET);
    }
}
