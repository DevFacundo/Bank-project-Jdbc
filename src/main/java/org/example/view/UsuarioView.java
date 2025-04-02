package org.example.view;

import org.example.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioView {
    List<Usuario> usuarios = new ArrayList<>();

    public void MostrarUsuarios(){usuarios.forEach(System.out::println);}
}
