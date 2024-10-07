package com.casar.casamento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    // Garante que a lista de noivos não esteja vazia
    @ElementCollection
    @CollectionTable(name = "noivos_nomes", joinColumns = @JoinColumn(name = "casamento_id"))
    @Column(name = "noivo", nullable = false, length = 350)
    @NotEmpty(message = "A lista de noivos não pode estar vazia")
    private List<String> noivos = new ArrayList<>();

    // Lista de padrinhos pode ser nula ou vazia
    @ElementCollection
    @CollectionTable(name = "padrinhos_nomes", joinColumns = @JoinColumn(name = "casamento_id"))
    @Column(name = "padrinho", length = 350)
    @Size(max = 350, message = "Nome deve ter no máximo 350 caracteres")
    private List<String> padrinhos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private short quantidadeConvidados;

    @Column(nullable = false)
    private float valorDoLocalDiaCompra;

    // Getters e Setters
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
        return new Usuario(usuario.email,usuario.nome);
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "CasamentoSemContrato{" +
                "valorDoLocalDiaCompra=" + valorDoLocalDiaCompra +
                ", quantidadeConvidados=" + quantidadeConvidados +
                ", usuario={ email=" + usuario.getEmail() +",nome="+usuario.getNome()+
                "}, local=" + local +
                ", dia=" + dia +
                '}';
    }
}

