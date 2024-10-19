package com.casar.casamento.model;

import jakarta.persistence.*;

@Entity
public class Noivos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario usuario;


    @Column(nullable = false)
    private int idadeDiaCasamento;

    @ManyToOne
    @JoinColumn(name = "fk_contratante", nullable = false)
    private Contratante contratante;

    public Noivos(int id, Usuario usuario, int idadeDiaCasamento, Contratante contratante) {
        this.usuario=usuario;
        this.idadeDiaCasamento = idadeDiaCasamento;
        this.contratante = contratante;
    }

    public int getId() {
        return id;
    }



    public int getIdadeDiaCasamento() {
        return idadeDiaCasamento;
    }

    public void setIdadeDiaCasamento(int idadeDiaCasamento) {
        this.idadeDiaCasamento = idadeDiaCasamento;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
