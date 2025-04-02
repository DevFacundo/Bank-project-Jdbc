package org.example.dao;

import org.example.config.DBConnection;
import org.example.model.Credencial;
import org.example.model.TipoPermiso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CredencialDao {

    public CredencialDao() {}

    public void agregarCredencial(Credencial credencial) {
        String query = "insert into credenciales(id_usuario, username, password, permiso) values (?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);)
        {
            stmt.setInt(1, credencial.getId_usuario());
            stmt.setString(2, credencial.getUsername());
            stmt.setString(3, credencial.getPassword());
            stmt.setString(4, credencial.getPermiso().name());
            stmt.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Credencial> getCredenciales() {
        List<Credencial> credenciales = new ArrayList<>();
        String query = "select * from credenciales";
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery(query))
         {
          while (rs.next()) {
              Credencial credencial = new Credencial();
              credencial.setId_usuario(rs.getInt("id_usuario"));
              credencial.setUsername(rs.getString("username"));
              credencial.setPassword(rs.getString("password"));
              credencial.setPermiso(TipoPermiso.valueOf(rs.getString("permiso")));
              credenciales.add(credencial);
          }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return credenciales;
    }

    public void eliminarCredencial(Credencial credencial) {
        String query = "delete from credenciales where id_usuario = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query))
        {
            stmt.setInt(1, credencial.getId_usuario());
            stmt.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void actualizarCredencial(Credencial credencial) {
        String query = "update credenciales set username = ?, password = ?, permiso = ? where id_usuario = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query))
        {
            stmt.setString(1, credencial.getUsername());
            stmt.setString(2, credencial.getPassword());
            stmt.setString(3, credencial.getPermiso().name());
            stmt.setInt(4, credencial.getId_usuario());
            stmt.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public Credencial obtenerCredencialPorId (int id)
    {
     String query = "select * from credenciales where id_usuario = ?";
     try(Connection connection = DBConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query))
     {
         stmt.setInt(1, id);
         try (ResultSet rs = stmt.executeQuery()) {
             if (rs.next()) {
                 Credencial credencial = new Credencial();
                 credencial.setId_usuario(rs.getInt("id_usuario"));
                 credencial.setUsername(rs.getString("username"));
                 credencial.setPassword(rs.getString("password"));
                 credencial.setPermiso(TipoPermiso.valueOf(rs.getString("permiso")));
                 return credencial;
             }
         }

     }catch (SQLException e)
     {
         e.printStackTrace();
     }
     return null;
    }
}
