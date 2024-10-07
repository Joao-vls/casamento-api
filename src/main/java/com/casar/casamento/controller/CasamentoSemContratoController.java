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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<List<CasamentoSemContrato>> getCasamentosByContratante(
            @PathVariable("email") String email) {

        // Obtém o usuário autenticado do contexto de segurança
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            // Se não houver um usuário autenticado, retorna UNAUTHORIZED
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Verifica se o e-mail do usuário autenticado é o mesmo do e-mail fornecido
        String authenticatedEmail = ((Usuario) authentication.getPrincipal()).getEmail();
        if (!authenticatedEmail.equals(email)) {
            // Se o e-mail não corresponder, retorna FORBIDDEN
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Busca o usuário pelo e-mail fornecido
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            List<CasamentoSemContrato> casamentoGet = casamentoSemContratoService.getCasamentosPorUsuario(usuario);
            if (casamentoGet.isEmpty()) {
                casamentoGet = null;
            }
            return new ResponseEntity<>(casamentoGet, HttpStatus.OK);
        } else {
            // Retorna NOT FOUND se o usuário não for encontrado
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
            return new ResponseEntity<>(criado.toString(), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Erro ao criar o casamento", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/sem-contrato/{id}/convidados")
    public ResponseEntity<Object> updateQuantidadeConvidados(
            @PathVariable("id") int id,
            @RequestParam("quantidadeConvidados") short quantidadeConvidados) {

        try {
            // Busca o casamento pelo ID
            Optional<CasamentoSemContrato> optionalCasamento = casamentoSemContratoService.getById(id);

            if (optionalCasamento.isPresent()) {
                CasamentoSemContrato casamento = optionalCasamento.get();

                // Atualiza a quantidade de convidados
                casamento.setQuantidadeConvidados(quantidadeConvidados);

                // Salva a alteração
                casamentoSemContratoService.save(casamento);

                // Retorna a resposta de sucesso
                return new ResponseEntity<>(casamento, HttpStatus.OK);
            } else {
                // Se o casamento não for encontrado, retorna NOT FOUND
                return new ResponseEntity<>(new ErrorResponse("Casamento não encontrado", null), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            // Se houver qualquer erro, retorna INTERNAL SERVER ERROR
            return new ResponseEntity<>(new ErrorResponse("Erro ao atualizar a quantidade de convidados", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/sem-contrato/{id}/local")
    public ResponseEntity<Object> updateLocal(
            @PathVariable("id") int id,
            @RequestParam("localId") int localId) {
        try {
            // Busca o casamento pelo ID
            Optional<CasamentoSemContrato> optionalCasamento = casamentoSemContratoService.getById(id);

            if (optionalCasamento.isPresent()) {
                CasamentoSemContrato casamento = optionalCasamento.get();

                // Busca o local pelo ID fornecido
                Optional<Locais> optionalLocal = locai.findById(localId);
                if (optionalLocal.isPresent()) {
                    Locais novoLocal = optionalLocal.get();

                    // Atualiza o local do casamento
                    casamento.setLocal(novoLocal);

                    // Salva a alteração
                    casamentoSemContratoService.save(casamento);

                    // Retorna a resposta de sucesso
                    return new ResponseEntity<>(casamento, HttpStatus.OK);
                } else {
                    // Se o local não for encontrado, retorna NOT FOUND
                    return new ResponseEntity<>(new ErrorResponse("Local não encontrado", null), HttpStatus.NOT_FOUND);
                }

            } else {
                // Se o casamento não for encontrado, retorna NOT FOUND
                return new ResponseEntity<>(new ErrorResponse("Casamento não encontrado", null), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            // Se houver qualquer erro, retorna INTERNAL SERVER ERROR
            return new ResponseEntity<>(new ErrorResponse("Erro ao atualizar o local", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/sem-contrato/{id}/data")
    public ResponseEntity<Object> updateData(
            @PathVariable("id") int id,
            @RequestParam("novaData") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate novaData) {

        try {
            // Busca o casamento pelo ID
            Optional<CasamentoSemContrato> optionalCasamento = casamentoSemContratoService.getById(id);

            if (optionalCasamento.isPresent()) {
                CasamentoSemContrato casamento = optionalCasamento.get();

                // Atualiza a data do casamento
                casamento.setDia(novaData);

                // Salva a alteração
                casamentoSemContratoService.save(casamento);

                // Retorna a resposta de sucesso
                return new ResponseEntity<>(casamento, HttpStatus.OK);
            } else {
                // Se o casamento não for encontrado, retorna NOT FOUND
                return new ResponseEntity<>(new ErrorResponse("Casamento não encontrado", null), HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            // Se houver qualquer erro, retorna INTERNAL SERVER ERROR
            return new ResponseEntity<>(new ErrorResponse("Erro ao atualizar a data do casamento", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
