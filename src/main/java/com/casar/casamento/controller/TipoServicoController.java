package com.casar.casamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casar.casamento.model.TipoServico;
import com.casar.casamento.repository.TipoServicoRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value = "/servicos", produces = { "application/json" })
public class TipoServicoController {
    @Autowired
    private TipoServicoRepository tipoServicoRepository;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<TipoServico> getLocais(){
        return tipoServicoRepository.findAll();
    }

}
