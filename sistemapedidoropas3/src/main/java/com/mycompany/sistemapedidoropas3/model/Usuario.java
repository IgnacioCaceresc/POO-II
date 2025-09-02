package com.mycompany.sistemapedidoropas3.model;

public class Usuario {

    private String usuarioId;
    private String nombre;
    private String email;

    public Usuario(String userId, String name, String email) {
        this.usuarioId = userId;
        this.nombre = name;
        this.email = email;
    }

    public String getUserId() {
        return usuarioId;
    }

    public String getName() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}
