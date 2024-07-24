package com.casar.casamento.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"dia", "fk_local"})}
)
public class Casamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate dia;

    @Column(nullable = false)
    private short quantidadeConvidados;

    @Column(nullable = false)
    private float valorDoLocalDiaCompra;


    public short getQuantidadeConvidados() {
        return quantidadeConvidados;
    }

    public void setQuantidadeConvidados(short quantidadeConvidados) {
        this.quantidadeConvidados = quantidadeConvidados;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public Locais getLocal() {
        return local;
    }

    public void setLocal(Locais local) {
        this.local = local;
    }

    @ManyToOne
    @JoinColumn(name = "fk_local", nullable = false)
    private Locais local;

    @ManyToOne
    @JoinColumn(name = "fk_contratante", nullable = false)
    private Contratante contratante;

    public Casamento(LocalDate dia, short quantidadeConvidados, float valorDoLocalDiaCompra, Locais local, Contratante contratante) {
        this.dia = dia;
        this.quantidadeConvidados = quantidadeConvidados;
        this.valorDoLocalDiaCompra = valorDoLocalDiaCompra;
        this.local = local;
        this.contratante = contratante;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public float getValorDoLocalDiaCompra() {
        return valorDoLocalDiaCompra;
    }

    public void setValorDoLocalDiaCompra(float valorDoLocalDiaCompra) {
        this.valorDoLocalDiaCompra = valorDoLocalDiaCompra;
    }
}
