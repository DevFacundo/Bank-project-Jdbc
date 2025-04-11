package org.example.service;

import org.example.exception.NoAutorizadoException;
import org.example.model.entities.Credencial;
import org.example.model.entities.Cuenta;
import org.example.model.entities.Usuario;
import org.example.model.enums.TipoPermiso;
import org.example.repository.CredencialDao;
import org.example.repository.CuentaDao;
import org.example.repository.UsuarioDao;

import java.util.List;
import java.util.stream.Collectors;

public class CuentaService {
    private static CredencialDao credencialDao;
    private static UsuarioDao usuarioDao;
    private static CuentaDao cuentaDao;
    private static Usuario usuario;

    public List<Cuenta> listarCuentas(Credencial credencial)
    {
        if (credencial.getPermiso().equals(TipoPermiso.CLIENTE))
        {
            List<Cuenta> cuentas = cuentaDao.getAll();
           List <Cuenta> cuentasPropias =cuentas.stream()
                        .filter(e-> e.getId_usuario().equals(credencial.getId_usuario()))
                        .toList();
           return cuentasPropias;


        }else
        {
          List<Cuenta> cuentas= cuentaDao.getAll();
          return cuentas;
        }
    }

    public

}
