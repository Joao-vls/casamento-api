package com.casar.casamento.services;

import com.casar.casamento.dto.UsuarioDTO;
import com.casar.casamento.model.Contratante;
import com.casar.casamento.model.Noivos;
import com.casar.casamento.model.Usuario;
import com.casar.casamento.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ContratanteService contratanteService;

    @Autowired
    private NoivosService noivosService;

    @Autowired
    private TokenService tokenService;

    @Transactional
    public UsuarioDTO createUser(Usuario user) {
        Optional<Usuario> existingUserOpt = usuarioRepository.findByEmail(user.getEmail());

        if (existingUserOpt.isPresent()) {
            Usuario existingUser = existingUserOpt.get();
            validateUserCredentials(user, existingUser);
            return buildUsuarioDTO(existingUser);
        } else {
            user.setSenha(passwordEncoder.encode(user.getSenha()));
            Usuario savedUser = usuarioRepository.save(user);
            return buildUsuarioDTO(savedUser);
        }
    }

    private void validateUserCredentials(Usuario inputUser, Usuario existingUser) {
        boolean passwordMatches = passwordEncoder.matches(inputUser.getSenha(), existingUser.getSenha());
        boolean nameMatches = inputUser.getNome().equals(existingUser.getNome());

        if (!passwordMatches || !nameMatches) {
            throw new IllegalArgumentException("Nome ou email ou senha inválidos");
        }
    }

    private UsuarioDTO buildUsuarioDTO(Usuario user) {
        Contratante contratante = contratanteService.isUsuario(user.getId()) ?
                contratanteService.getByUsuarioId(user.getId()) : null;
        Noivos noivo = noivosService.isUsuario(user.getId()) ?
                noivosService.getByUsuarioId(user.getId()) : null;

        String token = tokenService.generateToken(user); // Gera o token JWT

        return new UsuarioDTO(user.getEmail(), user.getNome(), getUserRoles(user), token);
    }

    public List<String> getUserRoles(Usuario user) {
        List<String> roles = new ArrayList<>();

        if (contratanteService.isUsuario(user.getId())) {
            roles.add("ROLE_CONTRATANTE");
        } else if (noivosService.isUsuario(user.getId())) {
            roles.add("ROLE_NOIVOS");
        } else {
            roles.add("ROLE_USER");
        }

        return roles;
    }
}
