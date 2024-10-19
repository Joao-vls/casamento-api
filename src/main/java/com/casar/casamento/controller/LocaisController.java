package com.casar.casamento.controller;

import com.casar.casamento.dto.LocaisDTO;
import com.casar.casamento.model.Locais;
import com.casar.casamento.repository.LocaisRepository;
import com.casar.casamento.services.LocaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/locais", produces = {"application/json"})
public class LocaisController {
    @Autowired
    private LocaisRepository locaisRepository;
    @Autowired
    private LocaisService locaisService;


    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Locais> getLocais(){
        return locaisRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocaisDTO> getLocalById(@PathVariable Integer id) {
        Optional<Locais> local = locaisService.findById(id);
        if (local.isPresent()) {
            LocaisDTO locaisDTO = new LocaisDTO(local.get());
            return ResponseEntity.ok(locaisDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
