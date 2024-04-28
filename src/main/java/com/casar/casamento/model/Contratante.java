package com.casar.casamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Contratante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private LocalDate dataContrato;

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario usuario;

    public Contratante(LocalDate dataContrato, Usuario usuario) {
        this.dataContrato = dataContrato;
        this.usuario = usuario;
    }

    public LocalDate getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(LocalDate dataContrato) {
        this.dataContrato = dataContrato;
    }
}
