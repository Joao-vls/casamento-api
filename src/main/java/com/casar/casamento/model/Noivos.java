package com.casar.casamento.model;

import jakarta.persistence.*;

@Entity
public class Noivos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(nullable = false)
    private int idadeDiaCasamento;

    @ManyToOne
    @JoinColumn(name = "fk_contratante", nullable = false)
    private Contratante contratante;

    public Noivos(int id, String nome, int idadeDiaCasamento, Contratante contratante) {
        this.nome = nome;
        this.idadeDiaCasamento = idadeDiaCasamento;
        this.contratante = contratante;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
