package org.example.repository;

import org.example.config.DBConnection;
import org.example.interfaces.IRepository;
import org.example.model.entities.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDao implements IRepository<Usuario> {

    public int addNew(Usuario usuario) {
        String query = "insert into usuarios (nombre,apellido,dni,email) values (?,?,?,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getDni());
            stmt.setString(4, usuario.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public List<Usuario> getAll() {
        String query = "select * from usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
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

    public void update(Usuario usuario) {
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

    public void delete(Usuario usuario) {
        String query = "delete from usuarios where id_usuario = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usuario.getId_usuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Usuario> getById(int id) {
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
