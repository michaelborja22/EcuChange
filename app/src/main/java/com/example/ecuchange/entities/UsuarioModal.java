package com.example.ecuchange.entities;

public class UsuarioModal {

    private String nombre;
    private String apellido;
    private String correo;
    private String user;
    private String password;
    private String direccion;
    private String telefono;
    private String urlImagen;

    public UsuarioModal(String nombre, String apellido, String correo, String user, String password, String direccion, String telefono,String urlImagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.user = user;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.urlImagen = urlImagen;
    }

    public UsuarioModal(String nombre, String apellido, String correo, String user, String direccion, String urlImagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.user = user;
        this.direccion = direccion;
        this.urlImagen = urlImagen;
    }

    public UsuarioModal(String nombre, String apellido, String correo, String user, String password,String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.user = user;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public UsuarioModal(String password) {
        this.password = password;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
