package com.casar.casamento.controller;

import com.casar.casamento.dto.UsuarioDTO;
import com.casar.casamento.model.Usuario;
import com.casar.casamento.services.ErrorResponse;
import com.casar.casamento.services.TokenService;
import com.casar.casamento.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/acesso", produces = {"application/json"})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody Usuario user, BindingResult result) {
        System.out.println(user);
        if (result.hasErrors()) {
            HashMap<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            ErrorResponse errorResponse = new ErrorResponse("Validação falhou", errors);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            UsuarioDTO usuarioCriado = usuarioService.createUser(user);
            // Gera o token JWT para o usuário recém-criado
            String token = tokenService.generateToken(user);

            // Retorna a resposta com o usuário criado e o token
            return new ResponseEntity<>(new CreateUserResponse(usuarioCriado, token), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/users/me")
    public ResponseEntity<Object> getUserDetails(HttpServletRequest request) {

        // Obtém o Authentication do SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            Usuario usuario = (Usuario) authentication.getPrincipal();
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    usuario.getEmail(),
                    usuario.getNome(),
                    usuario.getId()
            );

            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }
    }
    static class CreateUserResponse {
        private UsuarioDTO usuario;
        private String token;

        public CreateUserResponse(UsuarioDTO usuario, String token) {
            System.out.println(usuario);
            this.usuario = usuario;
            this.token = token;
        }

        public UsuarioDTO getUsuario() {
            return usuario;
        }

        public void setUsuario(UsuarioDTO usuario) {
            this.usuario = usuario;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
