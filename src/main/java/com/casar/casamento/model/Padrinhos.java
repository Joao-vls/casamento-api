package com.casar.casamento.model;

import jakarta.persistence.*;

@Entity
public class Padrinhos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "fk_casamento")
    private Casamento casamento;

    public Padrinhos(String nome, Casamento casamento) {
        this.nome = nome;
        this.casamento = casamento;
    }
}
