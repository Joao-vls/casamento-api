package com.casar.casamento.controller;

import com.casar.casamento.model.Locais;
import com.casar.casamento.repository.LocaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/locais", produces = {"application/json"})
public class LocaisController {
    @Autowired
    private LocaisRepository locaisRepository;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Locais> getLocais(){
        return locaisRepository.findAll();
    }
}
