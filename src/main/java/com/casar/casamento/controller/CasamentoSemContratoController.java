package com.casar.casamento.controller;

import com.casar.casamento.model.CasamentoSemContrato;
import com.casar.casamento.model.Usuario;
import com.casar.casamento.repository.UsuarioRepository;
import com.casar.casamento.services.CasamentoSemContratoService;
import com.casar.casamento.services.ErrorResponse;
import com.casar.casamento.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/casamentos")
public class CasamentoSemContratoController {

    @Autowired
    private CasamentoSemContratoService casamentoSemContratoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/contratante/{email}")
    public ResponseEntity<List<CasamentoSemContrato>> getCasamentosByContratante(@PathVariable("email") String email) {
        // Busca o usuário pelo email
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        System.out.println(email);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            List<CasamentoSemContrato> casamentos = casamentoSemContratoService.getCasamentosPorUsuario(usuario);

            return new ResponseEntity<>(casamentos, HttpStatus.OK);
        } else {
            // Retorna NOT FOUND caso o usuário não seja encontrado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/sem-contrato")
    public ResponseEntity<Object> createCasamentoSemContrato(@Valid @RequestBody CasamentoSemContrato casamentoSemContrato, BindingResult result) {
        if (result.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            ErrorResponse errorResponse = new ErrorResponse("Validação falhou", errors);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            CasamentoSemContrato criado = casamentoSemContratoService.createCasamentoSemContrato(casamentoSemContrato);
            return new ResponseEntity<>(criado, HttpStatus.CREATED);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao criar o casamento", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
