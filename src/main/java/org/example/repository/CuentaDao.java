package org.example.repository;

import org.example.config.DBConnection;
import org.example.interfaces.IRepository;
import org.example.model.entities.Cuenta;
import org.example.model.enums.TipoCuenta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CuentaDao implements IRepository<Cuenta> {
    public int addNew (Cuenta cuenta)
    {
        String query = "insert into cuentas (id_usuario, tipo, saldo) values (?,?,?)";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);)
        {
            stmt.setInt(1,cuenta.getId_usuario());
            stmt.setString(2,cuenta.getTipo_cuenta().name());
            stmt.setDouble(3,cuenta.getSaldo());
            stmt.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return cuenta.getId_usuario();
    }

    public List<Cuenta> getAll ()
    {
        List<Cuenta> cuentas = new ArrayList<>();
        String query = "select * from cuentas";

        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                Cuenta cuenta = new Cuenta();
                cuenta.setId_cuenta(rs.getInt("id_cuenta"));
                cuenta.setId_usuario(rs.getInt("id_usuario"));
                cuenta.setTipo_cuenta(TipoCuenta.valueOf(rs.getString("tipo")));
                cuenta.setFecha_creacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
                cuentas.add(cuenta);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return cuentas;
    }

    public void update(Cuenta cuenta)
    {
       String query = "update cuentas set tipo = ?,saldo = ? where id_usuario = ?";

       try (   Connection connection = DBConnection.getConnection();
               PreparedStatement stmt = connection.prepareStatement(query);)
       {
           stmt.setString(1,cuenta.getTipo_cuenta().name());
           stmt.setDouble(2,cuenta.getSaldo());
           stmt.setInt(3,cuenta.getId_usuario());
           stmt.executeUpdate();

       }catch (SQLException e)
       {
           e.printStackTrace();
       }
    }
    public void delete (Cuenta cuenta)
    {
        String query = "delete from cuentas where id_usuario = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);)
        {
            stmt.setInt(1,cuenta.getId_usuario());
            stmt.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
                    }
    }

    public Optional<Cuenta> getById(int id)
    {
        String query = "select * from cuentas where id_usuario = ?";
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);)
        {
            stmt.setInt(1,id);
            try(ResultSet rs = stmt.executeQuery();)
            {
                Cuenta cuenta = new Cuenta();
                cuenta.setId_cuenta(rs.getInt("id_cuenta"));
                cuenta.setId_usuario(rs.getInt("id_usuario"));
                cuenta.setTipo_cuenta(TipoCuenta.valueOf(rs.getString("tipo")));
                cuenta.setSaldo(rs.getDouble("saldo"));
                return Optional.of(cuenta);

            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
