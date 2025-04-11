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
import java.util.*;
import java.util.stream.Collectors;

public class UsuarioService {
    private static UsuarioDao usuarioDao = new UsuarioDao();
    private static CredencialDao credencialDao = new CredencialDao();
    private static CuentaDao cuentaDao = new CuentaDao();
    private static Scanner scanner = new Scanner(System.in);

    public List<Usuario> listarUsuarios(Credencial credencial) throws NoAutorizadoException
    {
        if (credencial.getPermiso().equals(TipoPermiso.CLIENTE))
        {
            throw new NoAutorizadoException("\nNo tienes permisos para listar usuarios");
        }
        List<Usuario> usuarios =  usuarios=usuarioDao.getAll();
        if (usuarios.isEmpty())
        {
            System.out.println("Usuarios no encontrados");
        }
            return usuarios;
    }

    public Usuario buscarPorDniOEmail(Credencial credencial, String StringABuscar) throws NoAutorizadoException
    {
        if (credencial.getPermiso().equals(TipoPermiso.CLIENTE))
        {
            throw new NoAutorizadoException("\nNo tienes permisos para buscar usuarios");
        }

        List<Usuario> usuarios = usuarioDao.getAll();

        return usuarios.stream()
                .filter(u -> u.getDni().equals(StringABuscar)|| u.getEmail().equalsIgnoreCase(StringABuscar))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
    }


///  MODIFICAR USUARIO Y LAS SIGUIENTES SON PARA CADA TIPO DE PERMSISO
    public void modificarUsuario(Credencial credencial) throws NoAutorizadoException
    {
       if (credencial.getPermiso().equals(TipoPermiso.CLIENTE))
       {
           modificarCliente(credencial);
       }
       else if (credencial.getPermiso().equals(TipoPermiso.GESTOR))
       {
            modificarComoGestor(credencial);
       }
       else
       {
           modificarComoAdmin(credencial);
       }
    }
    public void modificarCliente(Credencial credencial)
    {
        Optional<Usuario> cliente = usuarioDao.getById(credencial.getId_usuario());
        if (cliente.isPresent())
        {
            Usuario usuarioCliente = cliente.get();

            datosAModificar(usuarioCliente);

            usuarioDao.update(usuarioCliente);
        } else
        {
            System.out.println("Usuario no encontrado");
        }
    }
    public void modificarComoAdmin (Credencial credencial)
    {

            List<Usuario> usuarios = usuarioDao.getAll();
            System.out.println("Lista de Usuarios: \n"+usuarios);

            System.out.println("Ingrese el Id del usuario a modificar: ");
            int id = scanner.nextInt();scanner.nextLine();

            Optional<Usuario> usuario = usuarioDao.getById(id);

            if (usuario.isPresent())
            {
                datosAModificar(usuario.get());
                usuarioDao.update(usuario.get());
            }
            else
            {
                System.out.println("Usuario no encontrado");
            }
        }
    public void modificarComoGestor(Credencial credencial)
    {
        List<Usuario> clientes = listarClientes();
        List<Integer> idClientes = clientes.stream()
                        .map(Usuario::getId_usuario)
                        .toList();

        System.out.println("Lista de clientes");
        clientes.forEach(System.out::println);

        System.out.println("Id del cliente a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (idClientes.contains(id))
        {
            Optional<Usuario> user = usuarioDao.getById(id);

            if (user.isPresent())
            {
                datosAModificar(user.get());

                usuarioDao.update(user.get());
            }
            else System.out.println("Usuario no encontrado");

        } else throw new NoAutorizadoException("\nNo estas autorizado para cambiar datos de usuarios que no sean clientes");

    }
    // PIDE LOS DATOS AL USUARIO
    public void datosAModificar(Usuario usuario)
    {
        int opt;
        do {
            System.out.println("Datos del usuario");
            System.out.println(usuario);
            System.out.println("\nQue quiere modificar: ");
            System.out.println("[1]-Nombre");
            System.out.println("[2]-Apellido");
            System.out.println("[3]-Email");
            System.out.println("-----------");
            System.out.println("[0]-Finalizar");
            System.out.print("Seleccione opcion: ");
            opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt)
            {
                case 1:
                    System.out.println("Nombre: ");
                    String nombre = scanner.nextLine();
                    usuario.setNombre(nombre);
                    break;
                case 2:
                    System.out.println("Apellido: ");
                    String apellido = scanner.nextLine();
                    usuario.setApellido(apellido);
                    break;
                case 3:
                    System.out.println("Email: ");
                    String email = scanner.nextLine();
                    usuario.setEmail(email);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }

        }while (opt != 0);
    }


    public void eliminarUsuario(Credencial credencial) throws NoAutorizadoException
    {
        if (credencial.getPermiso().equals(TipoPermiso.GESTOR))
        {


        } else if (credencial.getPermiso().equals(TipoPermiso.ADMINISTRADOR))
        {
            eliminarCualquierUsuario(credencial);
        } else
        {
            throw new NoAutorizadoException("No tienes permisos para eliminar usuarios");
        }
    }

    public void eliminarCliente (Credencial credencial)
    {
        List<Usuario> clientes = listarClientes();

        System.out.println("Lista de clientes: \n"+clientes);
        System.out.println("Id del cliente a modificar: ");
        int id = scanner.nextInt();scanner.nextLine();

        Optional<Usuario> cliente = usuarioDao.getById(id);

        if (cliente.isPresent())
        {
            usuarioDao.update(cliente.get());
        }
        else
        {
            System.out.println("Usuario no encontrado");
        }
    }
    public void eliminarCualquierUsuario(Credencial credencial)
    {
        List<Usuario> usuarios = usuarioDao.getAll();
        System.out.println("Lista de usuarios: \n"+usuarios);
        System.out.println("Ingrese el Id del usuario a elliminar: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Optional<Usuario> usuario = usuarioDao.getById(id);
        if (usuario.isPresent())
        {
            usuarioDao.delete(usuario.get());
        }else
        {
            System.out.println("Usuario no encontrado");
        }
    }
    public List<Usuario> listarClientes ()
    {
        List<Credencial> credenciales = credencialDao.getAll();
        List<Usuario> usuarios = usuarioDao.getAll();

        List<Integer> idClientes = credenciales.stream()
                .filter(e -> e.getPermiso().equals(TipoPermiso.CLIENTE))
                .map(Credencial::getId_usuario)
                .toList();

        List<Usuario> clientes = usuarios.stream()
                .filter(e-> idClientes.contains(e.getId_usuario()))
                .toList();

        return clientes;
    }


}
