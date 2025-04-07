package org.example.service;

import org.example.exception.NoAutorizadoException;
import org.example.model.entities.Credencial;
import org.example.model.entities.Cuenta;
import org.example.model.entities.Usuario;
import org.example.model.enums.TipoPermiso;
import org.example.repository.CredencialDao;
import org.example.repository.CuentaDao;
import org.example.repository.UsuarioDao;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UsuarioService {
    private static UsuarioDao usuarioDao = new UsuarioDao();
    private static CredencialDao credencialDao = new CredencialDao();
    private static CuentaDao cuentaDao = new CuentaDao();
    private static Scanner scanner = new Scanner(System.in);

    public List<Usuario> listarUsuarios(Credencial credencial) throws NoAutorizadoException
    {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        if (credencial.getPermiso().equals(TipoPermiso.CLIENTE))
        {
            throw new NoAutorizadoException("\nNo tienes permisos para listar usuarios");
        }
        usuarios=usuarioDao.getAll();
        if (usuarios.isEmpty())
        {
            System.out.println("Usuarios no encontrados");
        }
            return usuarios;
    }

    public Optional<Usuario> buscarPorDniOEmail(Credencial credencial, String StringABuscar) throws NoAutorizadoException
    {
        if (credencial.getPermiso().equals(TipoPermiso.CLIENTE))
        {
            throw new NoAutorizadoException("\nNo tienes permisos para buscar usuarios");
        }

        List<Usuario> usuarios = usuarioDao.getAll();
        return usuarios.stream()
                .filter(u -> u.getDni().equals(StringABuscar)|| u.getEmail().equalsIgnoreCase(StringABuscar))
                .findFirst();
    }
    public void modificarUsuario(Credencial credencial)
    {
       if (credencial.getPermiso().equals(TipoPermiso.CLIENTE))
       {

       }
       else if (credencial.getPermiso().equals(TipoPermiso.GESTOR))
       {

       }else
       {

       }

    }

}
