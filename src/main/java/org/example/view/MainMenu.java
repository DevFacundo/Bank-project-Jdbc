package org.example.view;

import org.example.exception.NoAutorizadoException;
import org.example.model.entities.Credencial;
import org.example.model.entities.Cuenta;
import org.example.model.entities.Usuario;
import org.example.repository.CredencialDao;
import org.example.repository.CuentaDao;
import org.example.repository.UsuarioDao;
import org.example.service.CredencialService;
import org.example.service.CuentaService;
import org.example.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static CredencialService credencialService = new CredencialService();
    private static UsuarioService usuarioService = new UsuarioService();
    private static CuentaService cuentaService = new CuentaService();


    public void menu(Credencial credencial) {
        int option;
        do {

            System.out.println(": " + credencial.getUsername());
            System.out.println("-------Menu Principal------");
            System.out.println("[1]-Listar usuarios");
            System.out.println("[2]-Buscar usuario");
            System.out.println("[3]-Modificar usuario");
            System.out.println("[4]-Eliminar usuario");
            System.out.println("[5]-Listar las cuentas por Usuario");
            System.out.println("[6]-Obtener Saldo Total de un usuario");
            System.out.println("[7]-Realizar deposito a una cuenta");
            System.out.println("[8]-Transferencia entre cuentas");
            System.out.println("[9]-Cantidad de usuarios por Permiso");
            System.out.println("[10]-Obtener Cuentas por Tipo");
            System.out.println("[11]-Usuario con Mayor Saldo");
            System.out.println("[12]-Listar Usuarios por Saldo");
            System.out.println("-----------------------------");
            System.out.println("[0]-Cerrar Sesion");
            System.out.print("SELECCIONE UNA OPCION[1-12] o 0 PARA CERRAR SESION: ");
            option = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (option) {
                    case 0:
                        System.out.println("Cerrando Sesion");
                        break;
                    case 1:
                            List<Usuario> usuarios = usuarioService.listarUsuarios(credencial);
                            usuarios.forEach(System.out::println);
                        break;
                    case 2:
                            buscadorDeUsuario(credencial)
                                    .ifPresentOrElse(System.out::println,
                                    () -> System.out.println("Usuario no encontrado"));;
                        break;
                    case 3:
                            buscadorDeUsuario(credencial);
                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:

                        break;
                    case 10:

                        break;
                    case 11:

                        break;
                    case 12:

                        break;
                    case 13:

                        break;
                }


            } catch (NoAutorizadoException e) {
                System.out.println("No estas autorizado para hacer esta accion" + e.getMessage());
            }

        }while (option != 0);
    }

    public void loginMenu() {
        int option;
        while (true) {
            System.out.println("-------Bienvenido--------");
            System.out.println("[1]- Login");
            System.out.println("[2]- Registrarse");
            System.out.println("-----------------");
            System.out.println("[0]- Salir");
            System.out.print("ELIJA UNA OPCION : ");

            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    Credencial credencial = login();
                    menu(credencial);
                    break;
                case 2:
                    registrarse();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
                case 0:
                    System.out.println("Saliendo.....");
                    scanner.close();
                    break;
            }
        }

    }

    public Credencial login() {
        while (true) {
            System.out.print("Por favor ingrese su usuario: ");
            String user = scanner.nextLine();
            System.out.print("Por favor ingrese el password: ");
            String pass = scanner.nextLine();
            try
            {
                return credencialService.getCredentialByUserPass(user, pass);
            } catch (NoAutorizadoException e) {
                System.out.println("Usuario o contraseña incorrectos");
            }
        }
    }

    public void registrarse() {
        UsuarioDao usuarioDao = new UsuarioDao();
        CredencialDao credencialDao = new CredencialDao();
        CuentaDao cuentaDao = new CuentaDao();

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese dni: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();
        Usuario usuario = new Usuario(0, nombre, apellido, dni, email);

        int id = usuarioDao.addNew(usuario);

        if (id != -1) {
            Credencial credencial = new Credencial(id, nombre, apellido);
            credencialDao.addNew(credencial);

            Cuenta cuenta = new Cuenta(id, 0.0);
            cuentaDao.addNew(cuenta);

            System.out.println("Usuario agregado exitosamente");
        } else {
            System.out.println("Error al agregar usuario");
        }

    }

    public Optional<Usuario> buscadorDeUsuario(Credencial credencial)
    {
        int opt;
        System.out.println("Que buscais?");
        System.out.println("[1]- Buscar usuario por DNI");
        System.out.println("[2]- Buscar ususario por Email");
        System.out.println("[0]- Atras");
        System.out.println("--------------");
        System.out.print("Seleccione una opcion: ");
        opt = scanner.nextInt();
        scanner.nextLine();
        switch (opt)
        {
            case 1:
                System.out.println("Introduzca el DNI: ");
                String dni = scanner.nextLine();
                return usuarioService.buscarPorDniOEmail(credencial,dni);
            case 2:
                System.out.println("Introduzca el Email: ");
                String email = scanner.nextLine();
                return usuarioService.buscarPorDniOEmail(credencial,email);
            case 0:
                return Optional.empty();
            default:
                System.out.println("Opcion no valida");
                return Optional.empty();
        }
    }
}



