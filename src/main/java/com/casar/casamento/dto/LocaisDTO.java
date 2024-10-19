package com.casar.casamento.dto;

import com.casar.casamento.model.Locais;

import java.util.List;
import java.util.stream.Collectors;

public class LocaisDTO {

    private Integer id;
    private String municipio;
    private String uf;
    private String rua;
    private String bairro;
    private String numero;
    private short quantidadeMaxPessoas;
    private float valor;
    private List<String> imageUrls;

    public LocaisDTO(Locais local) {
        this.id = local.getId();
        this.municipio = local.getMunicipio();
        this.uf = local.getUf();
        this.rua = local.getRua();
        this.bairro = local.getBairro();
        this.numero = local.getNumero();
        this.quantidadeMaxPessoas = local.getQuantidadeMaxPessoas();
        this.valor = local.getValor();
        this.imageUrls = local.getImagens().stream()
                .map(imagem -> "/imagens/locais/" + imagem)
                .collect(Collectors.toList());  // Cria uma lista de URLs completas para cada imagem
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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

    public short getQuantidadeMaxPessoas() {
        return quantidadeMaxPessoas;
    }

    public void setQuantidadeMaxPessoas(short quantidadeMaxPessoas) {
        this.quantidadeMaxPessoas = quantidadeMaxPessoas;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
