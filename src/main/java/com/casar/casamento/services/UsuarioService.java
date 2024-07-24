package com.casar.casamento.services;

import com.casar.casamento.dto.UsuarioDTO;
import com.casar.casamento.model.Usuario;
import com.casar.casamento.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    @Transactional
    public UsuarioDTO createUser(Usuario user) {

            if (usuarioRepository.existsByEmail(user.getEmail())) {
                Optional<Usuario> usu = usuarioRepository.findByEmail(user.getEmail());
                if (usu.isPresent()) {
                    Usuario usuario = usu.get();
                    if (passwordEncoder.matches(user.getSenha(), usuario.getSenha())) {
                        if (user.getNome().equals(usuario.getNome())) {
                            if (contratanteService.isUsuario(usuario.getId())) {
                                if (noivosService.isUsuario(usuario.getId())) {
                                    return new UsuarioDTO(usuario.getEmail(), usuario.getNome(), true, true);
                                }
                                return new UsuarioDTO(usuario.getEmail(), usuario.getNome(), false, true);
                            } else if (noivosService.isUsuario(usuario.getId())) {
                                return new UsuarioDTO(usuario.getEmail(), usuario.getNome(), true, false);
                            }
                            return new UsuarioDTO(usuario.getEmail(), usuario.getNome(), false, false);
                        }
                    }
                    throw new IllegalArgumentException("Nome ou email ou senha invalidos");
                }

            }
        else {
            String encodedPassword = passwordEncoder.encode(user.getSenha());
            user.setSenha(encodedPassword);
            usuarioRepository.save(user);
            return new UsuarioDTO(user.getEmail(),user.getNome(),false,false);
        }
        return null;
    }
}
