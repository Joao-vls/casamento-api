package com.casar.casamento.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class CasamentoSemContratoDTO {

    @JsonProperty("noivos")
    @NotEmpty(message = "Os noivos sao obrigatório")
    private List<String> noivos = new ArrayList<>();

    @JsonProperty("padrinhos")
    private List<String> padrinhos = new ArrayList<>();

    @JsonProperty("local")
    @NotNull(message = "O ID do local é obrigatório")
    private int localId;

    @JsonProperty("usuario")
    @NotNull(message = "O ID do usuário é obrigatório")
    private int usuarioId;

    @JsonProperty("dia")
    private LocalDate dia;

    @JsonProperty("quantidadeConvidados")
    @NotNull(message = "A quantidade de convidados é obrigatória")
    private short quantidadeConvidados;

    @JsonProperty("valorDoLocalDiaCompra")
    @NotNull(message = "O valor do local no dia da compra é obrigatório")
    private float valorDoLocalDiaCompra;

    // Getters e setters
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

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
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

    @Override
    public String toString() {
        return "CasamentoSemContratoDTO{" +
                "noivos=" + noivos +
                ", padrinhos=" + padrinhos +
                ", localId=" + localId +
                ", usuarioId=" + usuarioId +
                ", dia=" + dia +
                ", quantidadeConvidados=" + quantidadeConvidados +
                ", valorDoLocalDiaCompra=" + valorDoLocalDiaCompra +
                '}';
    }
}

