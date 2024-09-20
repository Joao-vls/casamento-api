package com.casar.casamento.controller;

import com.casar.casamento.dto.CasamentoSemContratoDTO;
import com.casar.casamento.model.CasamentoSemContrato;
import com.casar.casamento.model.Locais;
import com.casar.casamento.model.Usuario;
import com.casar.casamento.repository.LocaisRepository;
import com.casar.casamento.repository.UsuarioRepository;
import com.casar.casamento.services.CasamentoSemContratoService;
import com.casar.casamento.services.ErrorResponse;
import com.casar.casamento.services.LocaisService;
import com.casar.casamento.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/casamentos", produces = {"application/json"})
public class CasamentoSemContratoController {

    @Autowired
    private CasamentoSemContratoService casamentoSemContratoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocaisService locai;

    @GetMapping("/contratante/{email}")
    public ResponseEntity<List<CasamentoSemContrato>> getCasamentosByContratante(@PathVariable("email") String email) {
        // Busca o usuário pelo email
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            List<CasamentoSemContrato> casamentos = casamentoSemContratoService.getCasamentosPorUsuario(usuario);
            if (casamentos.isEmpty()){
                casamentos=null;
            }
            return new ResponseEntity<>(casamentos, HttpStatus.OK);
        } else {
            // Retorna NOT FOUND caso o usuário não seja encontrado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/sem-contrato")
    public ResponseEntity<Object> createCasamentoSemContrato(@Valid @RequestBody CasamentoSemContratoDTO casamentoDTO, BindingResult result) {
        if (result.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return new ResponseEntity<>(new ErrorResponse("Validação falhou", errors), HttpStatus.BAD_REQUEST);
        }

        try {
            CasamentoSemContrato criado = casamentoSemContratoService.createCasamentoSemContratoFromDTO(casamentoDTO);
            return new ResponseEntity<>(criado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Erro ao criar o casamento", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
