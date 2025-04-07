package org.example.service;

import org.example.exception.NoAutorizadoException;
import org.example.model.entities.Credencial;
import org.example.repository.CredencialDao;

import java.sql.SQLException;
import java.util.Optional;

public class CredencialService {
   private static CredencialDao credencialDao = new CredencialDao();

    public Credencial getCredentialByUserPass(String user, String pass) throws NoAutorizadoException
    {
        Optional<Credencial> optionalCredencial = credencialDao.getCredentialByUserPass(user, pass);
        if(optionalCredencial.isPresent()){
            return optionalCredencial.get();
        }
        throw new NoAutorizadoException("Usuario no autorizado");
    }

    public void AddNewCredencial(Credencial credencial)
    {

    }
}
