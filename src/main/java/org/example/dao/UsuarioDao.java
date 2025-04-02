package org.example.dao;

import org.example.config.DBConnection;
import org.example.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDao {

    public void agregarUsuario(Usuario usuario) {
        String query = "insert into usuarios (nombre,apellido,dni,email) values (?,?,?,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getDni());
            stmt.setString(4, usuario.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> obtenerUsuarios() {
        String query = "select * from usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("email"),
                        rs.getTimestamp("fecha_creacion").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String query = "update usuarios set nombre = ?,apellido = ?,dni = ?,email = ? where id_usuario = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getDni());
            stmt.setString(4, usuario.getEmail());
            stmt.setInt(5, usuario.getId_usuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(Integer id_usuario) {
        String query = "delete from usuarios where id_usuario = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id_usuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Usuario> obtenerUsuarioPorId(int id) {
        String query = "select * from usuarios where id_usuario = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setDni(rs.getString("dni"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setFecha_creacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
                    return Optional.of(usuario);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
