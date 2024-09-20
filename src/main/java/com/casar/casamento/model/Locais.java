package com.casar.casamento.model;


import jakarta.persistence.*;

@Entity
public class Locais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String municipio;

    @Column(length = 2, nullable = false)
    private String uf;

    @Column(nullable = false)
    private float valor;

    @Column(nullable = false)
    private short quantidadeMaxPessoas;

    @Column(length = 50, nullable = false)
    private String rua;

    @Column(length = 50, nullable = false)
    private String bairro;

    @Column(length = 10, nullable = false)
    private String numero;

    @Column(length = 40)
    private String complemento;

    @Column(length = 400)
    private String descricao;

    public String getMunicipio() {
        return municipio.toUpperCase();
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Integer getId() {
        return id;
    }
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public short getQuantidadeMaxPessoas() {
        return quantidadeMaxPessoas;
    }

    public void setQuantidadeMaxPessoas(short quantidadeMaxPessoas) {
        this.quantidadeMaxPessoas = quantidadeMaxPessoas;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Locais{" +
                "municipio='" + municipio + '\'' +
                ", uf='" + uf + '\'' +
                ", valor=" + valor +
                ", quantidadeMaxPessoas=" + quantidadeMaxPessoas +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
