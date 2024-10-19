package com.casar.casamento.model;

import jakarta.persistence.*;

@Entity
//@Table(
//        uniqueConstraints = {@UniqueConstraint(columnNames = {"fk_tipoServico", "fk_casamento"})}
//)
public class Servicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_tipoServico", nullable = false)
    private TipoServico tipoServico;

    @ManyToOne
    @JoinColumn(name = "fk_casamento", nullable = false)
    private CasamentoSemContrato casamento;

    @Column(nullable = false)
    private float valor;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

 

    public void setCasamento(CasamentoSemContrato casamento) {
        this.casamento = casamento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Servicos{" +
                "id=" + id +
                ", tipoServico=" + tipoServico +
                ", valor=" + valor +
                '}';
    }
}
