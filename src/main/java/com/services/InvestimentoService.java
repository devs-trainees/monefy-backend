package com.services;

import com.domains.Investimento;
import com.domains.Usuario;
import com.domains.dtos.InvestimentoDTO;
import com.mappers.InvestimentoMapper;
import com.repositories.InvestimentoRepository;
import com.repositories.UsuarioRepository;
import com.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InvestimentoService {

    private final InvestimentoRepository investimentoRepo;
    private final UsuarioRepository usuarioRepo;

    public InvestimentoService(InvestimentoRepository investimentoRepo,
                               UsuarioRepository usuarioRepo) {
        this.investimentoRepo = investimentoRepo;
        this.usuarioRepo = usuarioRepo;
    }

    private Usuario resolveUsuario(Long usuarioId) {
        if (usuarioId == null) {
            return null;
        }

        return usuarioRepo.findById(usuarioId)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Usuário não encontrado: id=" + usuarioId));
    }

    @Transactional(readOnly = true)
    public List<InvestimentoDTO> findAll() {
        return InvestimentoMapper.toDtoList(investimentoRepo.findAll());
    }

    @Transactional(readOnly = true)
    public InvestimentoDTO findById(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do investimento é obrigatório");
        }

        return investimentoRepo.findById(id)
                .map(InvestimentoMapper::toDto)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Investimento não encontrado: id=" + id));
    }

    @Transactional(readOnly = true)
    public List<InvestimentoDTO> findByUsuario(Long idUsuario) {
        if (idUsuario == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do usuário é obrigatório");
        }


        List<Investimento> investimentos = investimentoRepo.findAll()
                .stream()
                .filter(i -> i.getUsuario() != null
                        && idUsuario.equals(i.getUsuario().getIdUsuario()))
                .toList();

        return InvestimentoMapper.toDtoList(investimentos);
    }

    @Transactional
    public InvestimentoDTO create(InvestimentoDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados do investimento são obrigatórios");
        }

        dto.setIdInvestimento(null);

        Investimento entity;
        try {
            entity = InvestimentoMapper.toEntity(dto, this::resolveUsuario);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        Investimento saved = investimentoRepo.save(entity);
        return InvestimentoMapper.toDto(saved);
    }

    @Transactional
    public InvestimentoDTO update(Long id, InvestimentoDTO dto) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do investimento é obrigatório");
        }
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados do investimento são obrigatórios");
        }

        Investimento entity = investimentoRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Investimento não encontrado: id=" + id));

        InvestimentoMapper.copyToEntity(dto, entity, this::resolveUsuario);

        Investimento updated = investimentoRepo.save(entity);
        return InvestimentoMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id do investimento é obrigatório");
        }

        Investimento entity = investimentoRepo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Investimento não encontrado: id=" + id));

        try {
            investimentoRepo.delete(entity);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(
                    "Investimento possui vínculos e não pode ser removido: id=" + id,
                    ex
            );
        }
    }
}
