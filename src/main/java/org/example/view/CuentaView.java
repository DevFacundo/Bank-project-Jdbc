package org.example.view;

import org.example.model.Cuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CuentaView {
    List<Cuenta> cuentas= new ArrayList<>();

    public void MostrarCuentas(){cuentas.forEach(System.out::println);}
}
