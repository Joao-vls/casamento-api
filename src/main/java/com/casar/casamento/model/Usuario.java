package com.casar.casamento.model;



import jakarta.persistence.*;



@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false, length = 350)
    String senha;

    @Column(nullable = false, length = 350)
    String email;

    @Column(nullable = false, length = 350)
    String nome;


    public Usuario(String senha, String email, String nome) {
        this.senha = senha;
        this.email = email;
        this.nome = nome;
    }

    public Usuario() {

    }

    public Integer getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
