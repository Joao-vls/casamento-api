package com.casar.casamento.dto;

public class UsuarioDTO {
    private String email;
    private String nome;
    private boolean noivo;
    private boolean contratante;


    public UsuarioDTO( String email, String nome, boolean noivo, boolean contratante) {

        this.email = email;
        this.nome = nome;
        this.noivo = noivo;
        this.contratante = contratante;
    }


    public boolean isNoivo() {
        return noivo;
    }

    public void setNoivo(boolean noivo) {
        this.noivo = noivo;
    }

    public boolean isContratante() {
        return contratante;
    }

    public void setContratante(boolean contratante) {
        this.contratante = contratante;
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


}
