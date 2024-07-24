package com.casar.casamento.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
public class CasamentoSemContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate dia;

    @Column(nullable = false)
    private List<String> noivos;

    @Column(nullable = true)
    private List<String> padrinhos;

    @Column(nullable = false)
    private short quantidadeConvidados;

    @Column(nullable = false)
    private float valorDoLocalDiaCompra;

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_local", nullable = false)
    private Locais local;

    public CasamentoSemContrato(LocalDate dia, List<String> noivos, List<String> padrinhos, short quantidadeConvidados, float valorDoLocalDiaCompra, Usuario usuario, Locais local) {
        this.dia = dia;
        this.noivos = noivos;
        this.padrinhos = padrinhos;
        this.quantidadeConvidados = quantidadeConvidados;
        this.valorDoLocalDiaCompra = valorDoLocalDiaCompra;
        this.usuario = usuario;
        this.local = local;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public List<String> getNoivos() {
        return noivos;
    }

    public void setNoivos(List<String> noivos) {
        this.noivos = noivos;
    }

    public List<String> getPadrinhos() {
        return padrinhos;
    }

    public void setPadrinhos(List<String> padrinhos) {
        this.padrinhos = padrinhos;
    }

    public short getQuantidadeConvidados() {
        return quantidadeConvidados;
    }

    public void setQuantidadeConvidados(short quantidadeConvidados) {
        this.quantidadeConvidados = quantidadeConvidados;
    }

    public float getValorDoLocalDiaCompra() {
        return valorDoLocalDiaCompra;
    }

    public void setValorDoLocalDiaCompra(float valorDoLocalDiaCompra) {
        this.valorDoLocalDiaCompra = valorDoLocalDiaCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Locais getLocal() {
        return local;
    }

    public void setLocal(Locais local) {
        this.local = local;
    }
}
