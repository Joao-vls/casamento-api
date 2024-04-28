package com.casar.casamento.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private float valorDoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipo;

    @Column(nullable = false)
    private int desconto;

    @ManyToOne
    @JoinColumn(name = "fk_contratante", nullable = false)
    private Contratante contratante;

    @ManyToOne
    @JoinColumn(name = "fk_casamento", nullable = false)
    private Casamento casamento;

    @Column(nullable = false)
    private LocalDate dataPagamento;

}
