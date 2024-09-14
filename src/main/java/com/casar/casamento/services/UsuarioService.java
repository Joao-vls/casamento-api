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
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(user.getEmail());

        if (optionalUsuario.isPresent()) {
            Usuario existingUser = optionalUsuario.get();

            if (!passwordEncoder.matches(user.getSenha(), existingUser.getSenha())) {
                throw new IllegalArgumentException("Nome ou email ou senha inválidos");
            }

            if (!user.getNome().equals(existingUser.getNome())) {
                throw new IllegalArgumentException("Nome ou email ou senha inválidos");
            }

            return buildUsuarioDTO(existingUser);
        } else {
            user.setSenha(passwordEncoder.encode(user.getSenha()));
            usuarioRepository.save(user);
            return buildUsuarioDTO(user);
        }
    }

    private UsuarioDTO buildUsuarioDTO(Usuario user) {
        Contratante contratante = contratanteService.isUsuario(user.getId()) ? contratanteService.getByUsuarioId(user.getId()) : null;
        Noivos noivo = noivosService.isUsuario(user.getId()) ? noivosService.getByUsuarioId(user.getId()) : null;

        String token = tokenService.generateToken(user); // Gera o token JWT

        if (contratante != null) {
            return new UsuarioDTO(user.getEmail(), user.getNome(), contratante, token);
        } else if (noivo != null) {
            return new UsuarioDTO(user.getEmail(), user.getNome(), noivo, token);
        } else {
            return new UsuarioDTO(user.getEmail(), user.getNome(), (Contratante) null, token);
        }
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
