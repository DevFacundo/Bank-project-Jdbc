package org.example.model.entities;

import java.time.LocalDateTime;

public class Usuario {
    private Integer id_usuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDateTime fecha_creacion;

    public Usuario() {
    }

    public Usuario(Integer id_usuario, String nombre, String apellido, String dni, String email, LocalDateTime fecha_creacion) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fecha_creacion = fecha_creacion;
    }

    public Usuario(String nombre, String apellido, String dni, String email, LocalDateTime fecha_creacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fecha_creacion = fecha_creacion;
    }

    public Usuario(Integer id_usuario, String nombre, String apellido, String dni, String email) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fecha_creacion = fecha_creacion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                '}';
    }
}
