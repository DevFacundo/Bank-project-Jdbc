package org.example.view;

import org.example.model.Credencial;

import java.util.ArrayList;
import java.util.List;

public class CredencialView {
    List<Credencial> credenciales = new ArrayList<>();

    public void MostrarCredenciales(){credenciales.forEach(System.out::println);}
}
