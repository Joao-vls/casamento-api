package com.casar.casamento.controller;

import com.casar.casamento.model.Usuario;
import com.casar.casamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController  {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getUsers(){
        return usuarioRepository.findAll();
    }

}
