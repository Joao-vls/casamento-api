package com.casar.casamento.services;

import com.casar.casamento.dto.CasamentoSemContratoDTO;
import com.casar.casamento.model.*;
import com.casar.casamento.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private TipoServicoRepository tipoServicoRepository;

    @Autowired
    private ServicosRepository servicosRepository;

    public List<CasamentoSemContrato> getCasamentosPorUsuario(Usuario usuario) {
        Optional<Usuario> usuario1 = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuario1.isPresent()) {
            List<CasamentoSemContrato> casamentoSemContratoes = casamentoSemContratoRepository.findByUsuario(usuario);
            return casamentoSemContratoRepository.findByUsuario(usuario);
        } else {
            return null;
        }
    }
    public CasamentoSemContrato createCasamentoSemContratoFromDTO(@Valid CasamentoSemContratoDTO casamentoDTO) {
        // Obtém o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();


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
    public Optional<CasamentoSemContrato> getById(int id) {
        return casamentoSemContratoRepository.findById(id);
    }

    public CasamentoSemContrato save(CasamentoSemContrato casamento) {
        return casamentoSemContratoRepository.save(casamento);
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
    public CasamentoSemContrato adicionarServico(int casamentoId, List<Integer> servicosDTO) {
        CasamentoSemContrato casamento = casamentoSemContratoRepository.findById(casamentoId)
                .orElseThrow(() -> new EntityNotFoundException("Casamento não encontrado"));

        for (Integer servicoDTO : servicosDTO) {
            TipoServico tipoServico = tipoServicoRepository.findById(servicoDTO)
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de serviço não encontrado"));

            // Cria a entidade Servicos e a associa ao casamento
            Servicos servico = new Servicos();
            servico.setTipoServico(tipoServico);
            servico.setCasamento(casamento);
            servico.setValor(tipoServico.getValor());

            // Adiciona o serviço ao casamento
            casamento.getServicos().add(servico);
            servicosRepository.save(servico);  // Salva o serviço individualmente
        }

        return casamentoSemContratoRepository.save(casamento);  // Atualiza o casamento
    }
    @Transactional
    public CasamentoSemContrato substituirServicos(int casamentoId, List<Integer> servicosDTO) {
        // Busca o casamento pelo ID
        CasamentoSemContrato casamento = casamentoSemContratoRepository.findById(casamentoId)
                .orElseThrow(() -> new EntityNotFoundException("Casamento não encontrado"));

        // Remove todos os serviços associados a este casamento
        servicosRepository.deleteByCasamentoId(casamentoId);
        
        // Adiciona os novos serviços
        for (Integer servicoId : servicosDTO) {
            TipoServico tipoServico = tipoServicoRepository.findById(servicoId)
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de serviço não encontrado"));

            // Cria a entidade Servicos e a associa ao casamento
            Servicos servico = new Servicos();
            servico.setTipoServico(tipoServico);
            servico.setCasamento(casamento);
            servico.setValor(tipoServico.getValor());

            // Adiciona o serviço à lista de serviços do casamento
            casamento.getServicos().add(servico);
        }

        // Atualiza e salva o casamento junto com os novos serviços
        return casamentoSemContratoRepository.save(casamento);
    }



}
