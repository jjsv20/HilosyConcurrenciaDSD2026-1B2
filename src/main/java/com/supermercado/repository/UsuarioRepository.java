package com.supermercado.repository;

import com.supermercado.connection.ConexionBD;
import com.supermercado.model.UsuarioModel;
import com.supermercado.utils.Colors;

public class UsuarioRepository {

    // Método para registrar un nuevo usuario al sistema
    public void registrarUsuario(UsuarioModel usuario) {
        String sql = "INSERT INTO usuarios (nombre, password, rol) VALUES (?, ?, ?)";
        try (var conexion = ConexionBD.conectar();
                var stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getRol());
            stmt.executeUpdate();
            System.out
                    .println(Colors.GREEN +"Usuario " + usuario.getNombre() + " registrado exitosamente con rol " + usuario.getRol());
        } catch (Exception e) {
            System.out.println("Error al registrar usuario");
            e.printStackTrace();
        }
    }

    // Método para eliminar un usuario del sistema
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (var conexion = ConexionBD.conectar();
                var stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario " + id + " eliminado exitosamente");
            } else {
                System.out.println("No se encontró el usuario para eliminar");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario");
            e.printStackTrace();
        }
    }

    // Método para buscar usuario por nombre
    public UsuarioModel buscarUsuarioPorNombre(String nombre) {
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";
        try (var conexion = ConexionBD.conectar();
                var stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                return usuario;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar usuario");
            e.printStackTrace();
        }
        return null;
    }

    // Método para listar todos los usuarios
    public void listarUsuarios() {
        String sql = "SELECT * FROM usuarios";
        System.out.println("\n===== Lista de Usuarios =====");
        try (var conexion = ConexionBD.conectar();
                var stmt = conexion.prepareStatement(sql);
                var rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id")
                                + " | Nombre: " + rs.getString("nombre")
                                + " | Rol: " + rs.getString("rol"));
            }
        } catch (Exception e) {
            System.out.println("Error al listar usuarios");
            e.printStackTrace();
        }
    }

    //Método para actualizar usuaros
    public void actualizarUsuario(UsuarioModel usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, password = ?, rol = ? WHERE id = ?";
        try (var conexion = ConexionBD.conectar();
                var stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getRol());
            stmt.setInt(4, usuario.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario " + usuario.getId() + " actualizado exitosamente");
            } else {
                System.out.println("No se encontró el usuario para actualizar");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario");
            e.printStackTrace();
        }
    }
}
