package com.supermercado.hilos;

import com.supermercado.cola.ColaClientes;
import com.supermercado.model.*;
import com.supermercado.utils.Colors;
import com.supermercado.utils.ConsoleLock;

public class CajaThread extends Thread {

    private final CajaModel    caja;
    private final ColaClientes cola;

    public CajaThread(CajaModel caja, ColaClientes cola) {
        this.caja = caja;
        this.cola = cola;
        setName("Caja-" + caja.getNombre());
    }

    @Override
    public void run() {

        while (true) {

            ClienteModel cliente = cola.atenderCliente();

            if (cliente == null) {
                synchronized (ConsoleLock.LOCK) {
                    System.out.println(Colors.BLUE + "[CAJA " + caja.getNombre() + "] Sin mas clientes. Cerrando." + Colors.RESET);
                }
                break;
            }

            // Anuncio: caja toma cliente
            synchronized (ConsoleLock.LOCK) {
                System.out.println(Colors.CYAN + "+=========================================+");
                System.out.println(Colors.CYAN + "  CAJA " + caja.getNombre() + " => Atendiendo a: " + cliente.getNombre());
                System.out.println(Colors.CYAN + "+=========================================+" + Colors.RESET);
            }

            // Cola actualizada inmediatamente después del anuncio
            ColaClientes.imprimirCola(cola.obtenerNombres());

            // Procesar productos
            double total       = 0;
            int    tiempoTotal = 0;

            for (ClienteProductoModel cp : cliente.getProductos()) {
                ProductoModel p      = cp.getProducto();
                int           cant   = cp.getCantidad();
                double        sub    = p.getPrecio() * cant;
                int           demora = p.getTiempo_pago() * cant;

                total       += sub;
                tiempoTotal += demora;

                try { Thread.sleep(demora * 1000L); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                synchronized (ConsoleLock.LOCK) {
                    System.out.println(Colors.WHITE + "  [CAJA " + pad(caja.getNombre(), 5) + "]" + " Escaneando: " + pad(p.getNombre(), 10) + " x" + cant + "  =>  $" + String.format("%,.0f", sub) + Colors.RESET);
                }
            }

            // Resumen
            synchronized (ConsoleLock.LOCK) {
                System.out.println(Colors.GREEN + "  +-----------------------------------------+");
                System.out.println(Colors.GREEN + "  | Caja: " + pad(caja.getNombre(), 5) + "   Cliente: " + pad(cliente.getNombre(), 16) + "  |"); System.out.println(Colors.GREEN + "  | Total:  $" + pad(String.format("%,.0f", total), 12) + "                |");
                System.out.println(Colors.GREEN + "  | Tiempo:  " + tiempoTotal + "s" + "                               |");
                System.out.println(Colors.GREEN + "  +-----------------------------------------+" + Colors.RESET);
            }
        }
    }

    private static String pad(String s, int n) {
        if (s.length() >= n) return s;
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < n) sb.append(' ');
        return sb.toString();
    }
}
