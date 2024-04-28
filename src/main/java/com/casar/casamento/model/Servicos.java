package com.casar.casamento.model;

import jakarta.persistence.*;

@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"fk_tipoServico", "fk_casamento"})}
)
public class Servicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_tipoServico", nullable = false)
    private TipoServico tipoServico;

    @ManyToOne
    @JoinColumn(name = "fk_casamento", nullable = false)
    private Casamento casamento;

    @Column(nullable = false)
    private float valor;
}
