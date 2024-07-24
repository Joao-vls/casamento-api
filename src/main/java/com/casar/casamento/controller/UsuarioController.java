package com.casar.casamento.controller;

import com.casar.casamento.dto.UsuarioDTO;
import com.casar.casamento.model.Usuario;
import com.casar.casamento.services.ErrorResponse;
import com.casar.casamento.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/acesso", produces = {"application/json"})
public class UsuarioController  {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody Usuario user, BindingResult result) {
        if (result.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            ErrorResponse errorResponse = new ErrorResponse("Validação falhou", errors);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
           UsuarioDTO usuarioCriado= usuarioService.createUser(user);
            return new ResponseEntity<>(usuarioCriado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
