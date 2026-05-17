package com.supermercado.cola;

import java.util.LinkedList;
import java.util.Queue;

import com.supermercado.model.ClienteModel;
import com.supermercado.utils.Colors;
import com.supermercado.utils.ConsoleLock;

public class ColaClientes {

    private final Queue<ClienteModel> clientes = new LinkedList<>();
    private boolean cerrada = false;

    // Agregar sin mostrar nada — la cola inicial se muestra desde Main
    public synchronized void agregarCliente(ClienteModel c) {
        if (cerrada) throw new IllegalStateException("La cola ya fue cerrada.");
        clientes.add(c);
        notifyAll();
    }

    public synchronized void cerrar() {
        cerrada = true;
        notifyAll();
    }

    public synchronized ClienteModel atenderCliente() {
        while (clientes.isEmpty() && !cerrada) {
            try { wait(); }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        if (clientes.isEmpty()) return null;

        ClienteModel c = clientes.poll();
        return c;
    }

    public synchronized String[] obtenerNombres() {
        return clientes.stream()
                .map(ClienteModel::getNombre)
                .toArray(String[]::new);
    }

    public static void imprimirCola(String[] nombres) {
        synchronized (ConsoleLock.LOCK) {
            System.out.println(Colors.YELLOW + "+======== COLA DE ESPERA =========+");
            if (nombres.length == 0) {
                System.out.println(Colors.YELLOW + "  [ VACIA ]");
            } else {
                for (int i = 0; i < nombres.length; i++) {
                    System.out.println(Colors.YELLOW + "  " + (i + 1) + ". " + nombres[i]);
                }
            }
            System.out.println(Colors.YELLOW + "+=================================+" + Colors.RESET);
        }
    }
}
