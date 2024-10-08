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

    @ManyToOne
    @JoinColumn(name = "fk_casamentoSemContrato", nullable = false)
    private CasamentoSemContrato casamentoSemContrato;

    @Column(nullable = false)
    private LocalDate dia;

    @ManyToOne
    @JoinColumn(name = "fk_local", nullable = false)
    private Locais local;

    @ManyToOne
    @JoinColumn(name = "fk_contratante", nullable = false)
    private Contratante contratante;

    public Casamento(CasamentoSemContrato casamentoSemContrato, LocalDate dia, Locais local, Contratante contratante) {
        this.casamentoSemContrato = casamentoSemContrato;
        this.dia = dia;
        this.local = local;
        this.contratante = contratante;
    }

    public CasamentoSemContrato getCasamentoSemContrato() {
        return casamentoSemContrato;
    }

    public void setCasamentoSemContrato(CasamentoSemContrato casamentoSemContrato) {
        this.casamentoSemContrato = casamentoSemContrato;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public Locais getLocal() {
        return local;
    }

    public void setLocal(Locais local) {
        this.local = local;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }
}
