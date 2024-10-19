package com.casar.casamento.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_servico")
public class TipoServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String nome;

    @Column(length = 150)
    private String descricao;

    @Column(nullable = false)
    private float valor;


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "TipoServico{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
