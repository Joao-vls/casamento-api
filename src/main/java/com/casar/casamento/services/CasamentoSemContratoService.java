package com.casar.casamento.services;

import com.casar.casamento.dto.CasamentoSemContratoDTO;
import com.casar.casamento.model.CasamentoSemContrato;
import com.casar.casamento.model.Locais;
import com.casar.casamento.model.Usuario;
import com.casar.casamento.repository.CasamentoSemContratoRepository;
import com.casar.casamento.repository.LocaisRepository;
import com.casar.casamento.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CasamentoSemContratoService {

    @Autowired
    private CasamentoSemContratoRepository casamentoSemContratoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocaisRepository locaisRepository;

    public List<CasamentoSemContrato> getCasamentosPorUsuario(Usuario usuario) {
        Optional<Usuario> usuario1 = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuario1.isPresent()) {
            return casamentoSemContratoRepository.findByUsuario(usuario);
        } else {
            return null;
        }
    }
    public CasamentoSemContrato createCasamentoSemContratoFromDTO(@Valid CasamentoSemContratoDTO casamentoDTO) {
        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

        // Imprime o DTO recebido para depuração
        System.out.println("DTO Recebido: " + casamentoDTO);

        // Verifica se o ID do usuário no DTO corresponde ao ID do usuário autenticado
        if (usuarioAutenticado.getId() != casamentoDTO.getUsuarioId()) {
            System.out.println("Erro: O ID do usuário no DTO não corresponde ao usuário autenticado.");
            throw new IllegalArgumentException("O ID do usuário fornecido não corresponde ao usuário autenticado");
        }

        // Verifica se a lista de noivos está vazia
        if (casamentoDTO.getNoivos() == null || casamentoDTO.getNoivos().isEmpty()) {
            System.out.println("Erro: A lista de noivos está vazia.");
            throw new IllegalArgumentException("A lista de noivos não pode estar vazia");
        }

        // Buscar o Local pelo ID
        Optional<Locais> localOptional = locaisRepository.findById(casamentoDTO.getLocalId());
        if (localOptional.isEmpty()) {
            System.out.println("Erro: Local não encontrado.");
            throw new IllegalArgumentException("Local não encontrado");
        }

        // Criar o objeto CasamentoSemContrato com base nas informações do DTO
        CasamentoSemContrato casamento = createCasamentoSemContrato(casamentoDTO, localOptional.get(), usuarioAutenticado);

        // Imprime o objeto casamento criado para depuração
        System.out.println("Casamento Criado: " + casamento);

        try {
            // Tenta salvar o objeto no repositório
            CasamentoSemContrato casamentoSalvo = casamentoSemContratoRepository.save(casamento);
            System.out.println("Casamento Salvo com Sucesso: " + casamentoSalvo);
            return casamentoSalvo;
        } catch (Exception e) {
            // Captura e imprime o erro ocorrido durante o salvamento
            System.out.println("Erro ao salvar o casamento: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar o casamento", e);
        }
    }


    private CasamentoSemContrato createCasamentoSemContrato(CasamentoSemContratoDTO casamentoDTO, Locais local, Usuario usuario) {

        CasamentoSemContrato casamento = new CasamentoSemContrato();
        casamento.setLocal(local);
        casamento.setUsuario(usuario);
        casamento.setDia(casamentoDTO.getDia());
        casamento.setNoivos(casamentoDTO.getNoivos());
        casamento.setPadrinhos(casamentoDTO.getPadrinhos());
        casamento.setQuantidadeConvidados(casamentoDTO.getQuantidadeConvidados());
        casamento.setValorDoLocalDiaCompra(casamentoDTO.getValorDoLocalDiaCompra());
        return casamento;
    }

}
