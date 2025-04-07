package org.example;


import org.example.model.entities.Credencial;
import org.example.model.enums.TipoPermiso;
import org.example.repository.CredencialDao;
import org.example.view.MainMenu;

public class Main {
    public static void main(String[] args)
    {
         MainMenu mm = new MainMenu();
         mm.loginMenu();
    }
}