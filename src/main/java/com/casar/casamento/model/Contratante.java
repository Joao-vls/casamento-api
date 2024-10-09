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

    @Override
    public String toString() {
        return "Contratante{" +
                "id=" + id +
                ", dataContrato=" + dataContrato +
                ", usuario=" + usuario +
                '}';
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario usuario;

    public Contratante(LocalDate dataContrato, Usuario usuario) {
        this.dataContrato = dataContrato;
        this.usuario = usuario;
    }
    public Contratante(){

    }

    public LocalDate getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(LocalDate dataContrato) {
        this.dataContrato = dataContrato;
    }
}
