package com.casar.casamento.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "usuario",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false, length = 350)
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 350, message = "Senha deve ter entre 6 e 350 caracteres")
    String senha;


    @Email(message = "Email deve ser válido")
    @Column(nullable = false, length = 350, unique = true)
    String email;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 1, max = 350, message = "Nome deve ter entre 1 e 350 caracteres")
    @Column(nullable = false, length = 350)
    String nome;


    public Usuario(String senha, String email, String nome) {
        this.senha = senha;
        this.email = email;
        this.nome = nome;
    }
    public Usuario( String email, String nome) {
        this.email = email;
        this.nome = nome;
    }
    public Usuario(int id, String email, String nome) {
        this.id=id;
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
