package com.casar.casamento.dto;

import com.casar.casamento.model.Contratante;
import com.casar.casamento.model.Noivos;

import java.util.List;

public class UsuarioDTO {
    private Integer id;
    private String email;
    private String nome;
    private String token; // Campo para o token JWT
    private Object role; // Pode ser Contratante, Noivos, ou null

    public UsuarioDTO(Object role, String token, String nome, String email, int id) {
        this.role = role;
        this.token = token;
        this.nome = nome;
        this.email = email;
        this.id = id;
    }

    public UsuarioDTO(String email, String nome, Integer id) {
        this.email = email;
        this.id = id;
        this.nome = nome;
    }

    public UsuarioDTO(String email, String nome, List<String> userRoles, String token) {
        this.role = userRoles;
        this.nome = nome;
        this.email = email;
        this.token = token;

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }
}
