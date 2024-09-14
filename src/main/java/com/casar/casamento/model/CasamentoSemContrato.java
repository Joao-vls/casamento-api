package com.casar.casamento.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "casamento_sem_contrato")
public class CasamentoSemContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate dia;

    @ManyToOne
    @JoinColumn(name = "fk_local", nullable = false)
    private Locais local;

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private short quantidadeConvidados;

    @Column(nullable = false)
    private float valorDoLocalDiaCompra;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
}
