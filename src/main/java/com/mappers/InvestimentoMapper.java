package com.mappers;

import com.domains.Investimento;
import com.domains.Usuario;
import com.domains.dtos.InvestimentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InvestimentoMapper {

    private InvestimentoMapper() {}

    public static InvestimentoDTO toDto(Investimento i) {
        if (i == null) return null;

        Long usuarioId = (i.getUsuario() == null) ? null : i.getUsuario().getIdUsuario();

        return new InvestimentoDTO(
                i.getIdInvestimento(),
                i.getNomeAtivo(),
                i.getValorAplicado(),
                i.getRentabilidade(),
                usuarioId
        );
    }

    public static List<InvestimentoDTO> toDtoList(Collection<Investimento> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(InvestimentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Page<InvestimentoDTO> toDtoPage(Page<Investimento> page) {
        List<InvestimentoDTO> content = toDtoList(page.getContent());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

    public static Investimento toEntity(InvestimentoDTO dto, Usuario usuario) {
        if (dto == null) return null;

        Investimento i = new Investimento();
        i.setIdInvestimento(dto.getIdInvestimento());
        i.setNomeAtivo(trim(dto.getNomeAtivo()));
        i.setValorAplicado(dto.getValorAplicado());
        i.setRentabilidade(dto.getRentabilidade());
        i.setUsuario(usuario);


        return i;
    }

    public static Investimento toEntity(InvestimentoDTO dto, Function<Long, Usuario> usuarioResolver) {
        if (dto == null) return null;
        Usuario usuario = (dto.getUsuarioId() == null) ? null : usuarioResolver.apply(dto.getUsuarioId());
        return toEntity(dto, usuario);
    }

    public static void copyToEntity(InvestimentoDTO dto, Investimento target, Usuario usuario) {
        if (dto == null || target == null) return;

        target.setNomeAtivo(trim(dto.getNomeAtivo()));
        target.setValorAplicado(dto.getValorAplicado());
        target.setRentabilidade(dto.getRentabilidade());
        target.setUsuario(usuario);


    }

    public static void copyToEntity(InvestimentoDTO dto, Investimento target, Function<Long, Usuario> usuarioResolver) {
        if (dto == null || target == null) return;
        Usuario usuario = (dto.getUsuarioId() == null) ? null : usuarioResolver.apply(dto.getUsuarioId());
        copyToEntity(dto, target, usuario);
    }

    private static String trim(String s) {
        return (s == null) ? null : s.trim();
    }

}