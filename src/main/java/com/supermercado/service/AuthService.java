package com.supermercado.service;

import com.supermercado.model.UsuarioModel;
import com.supermercado.repository.UsuarioRepository;

public class AuthService {
    
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void registrarUsuario(String nombre, String password, String rol) {
        
        String hashedPassword = com.supermercado.utils.PasswordUtils.hashPassword(password);
       
        UsuarioModel nuevoUsuario = new UsuarioModel();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setPassword(hashedPassword);
        nuevoUsuario.setRol(rol);
        usuarioRepository.registrarUsuario(nuevoUsuario);
    }

    public void actualizarUsuario(int id, String nombre, String password, String rol) {
        String hashedPassword = com.supermercado.utils.PasswordUtils.hashPassword(password);
        UsuarioModel usuarioActualizado = new UsuarioModel();
        usuarioActualizado.setId(id);
        usuarioActualizado.setNombre(nombre);
        usuarioActualizado.setPassword(hashedPassword);
        usuarioActualizado.setRol(rol);
        usuarioRepository.actualizarUsuario(usuarioActualizado);
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.eliminarUsuario(id);
    }

    public void listarUsuarios() {
        usuarioRepository.listarUsuarios();
    }

    public boolean login(String nombre, String password) {
        UsuarioModel usuario = usuarioRepository.buscarUsuarioPorNombre(nombre);
        if (usuario != null) {
            return com.supermercado.utils.PasswordUtils.verifyPassword(password, usuario.getPassword());
        }
        return false;
    }
}
