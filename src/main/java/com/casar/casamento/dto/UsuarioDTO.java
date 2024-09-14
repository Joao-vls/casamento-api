package com.casar.casamento.dto;

import com.casar.casamento.model.Contratante;
import com.casar.casamento.model.Noivos;
public class UsuarioDTO {
    private String email;
    private String nome;
    private String token; // Campo para o token JWT
    private Object role; // Pode ser Contratante, Noivos, ou null

    // Construtor e getters/setters...

    public UsuarioDTO(String email, String nome, Object role, String token) {
        this.email = email;
        this.nome = nome;
        this.role = role;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Outros getters e setters...
}
