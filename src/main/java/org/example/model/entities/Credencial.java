package org.example.model.entities;

import org.example.model.enums.TipoPermiso;

public class Credencial {
    private Integer id_credencial;
    private Integer id_usuario;
    private String username;
    private String password;
    private TipoPermiso permiso;

    public Credencial() {
    }

    public Credencial(Integer id_usuario, String username, String password) {
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
        this.permiso = TipoPermiso.CLIENTE;
    }

    public Credencial(Integer id_credencial, Integer id_usuario, String username, String password, TipoPermiso permiso) {
        this.id_credencial = id_credencial;
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
        this.permiso = permiso;
    }

    public Integer getId_credencial() {
        return id_credencial;
    }

    public void setId_credencial(Integer id_credencial) {
        this.id_credencial = id_credencial;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoPermiso getPermiso() {
        return permiso;
    }

    public void setPermiso(TipoPermiso permiso) {
        this.permiso = permiso;
    }

    @Override
    public String toString() {
        return "Credencial{" +
                "id_credencial=" + id_credencial +
                ", id_usuario=" + id_usuario +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", permiso=" + permiso +
                '}';
    }
}
