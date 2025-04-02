package org.example.model;

import java.time.LocalDateTime;

public class Cuenta {
    private Integer id_cuenta;
    private Integer id_usuario;
    private TipoCuenta tipo_cuenta;
    private Double saldo;
    private LocalDateTime fecha_creacion;

    public Cuenta() {}

    public Cuenta(Integer id_cuenta, Integer id_usuario, TipoCuenta tipo_cuenta, Double saldo, LocalDateTime fecha_creacion) {
        this.id_cuenta = id_cuenta;
        this.id_usuario = id_usuario;
        this.tipo_cuenta = tipo_cuenta;
        this.saldo = saldo;
        this.fecha_creacion = fecha_creacion;
    }

    public Integer getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Integer id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public TipoCuenta getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(TipoCuenta tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id_cuenta=" + id_cuenta +
                ", id_usuario=" + id_usuario +
                ", tipo_cuenta=" + tipo_cuenta +
                ", saldo=" + saldo +
                ", fecha_creacion=" + fecha_creacion +
                '}';
    }
}
