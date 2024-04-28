package com.casar.casamento.model;

import jakarta.persistence.*;

@Entity
public class UsuarioNoivos {

    @Id
    int id;

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_noivo", nullable = false)
    private Noivos noivo;

    public UsuarioNoivos(Usuario usuario, Noivos noivo) {
        this.usuario = usuario;
        this.noivo = noivo;
        this.id = this.noivo.getId()+this.usuario.getId();
    }
}
