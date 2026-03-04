package com.domains.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class InvestimentoDTO {
    public interface Create {}
    public interface Update {}

    @Null(groups = EntidadeDTO.Create.class, message = "Id deve ser omitido na criação")
    @NotNull(groups = EntidadeDTO.Update.class, message = "Id é obrigatório na atualização")
    private Long idInvestimento;

    @NotBlank(message = "Nome  é obrigatório")
    @Size(max = 30, message = "Documento deve ter no máximo 30 caracteres")
    private String nomeAtivo;

    @NotNull(message = "Valor aplicado do investimento é obrigatório")
    @Digits(integer = 15, fraction = 2, message = "Valor aplicado inválido")
    private BigDecimal valorAplicado;

    @Digits(integer = 15, fraction = 2, message = "Rentabilidade inválida")
    private BigDecimal rentabilidade;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    public InvestimentoDTO() {
    }

    public InvestimentoDTO(Long idInvestimento, String nomeAtivo, BigDecimal valorAplicado, BigDecimal rentabilidade, Long usuarioId) {
        this.idInvestimento = idInvestimento;
        this.nomeAtivo = nomeAtivo;
        this.valorAplicado = valorAplicado;
        this.rentabilidade = rentabilidade;
        this.usuarioId = usuarioId;
    }

    public Long getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(Long idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public void setNomeAtivo(String nomeAtivo) {
        this.nomeAtivo = nomeAtivo;
    }

    public BigDecimal getValorAplicado() {
        return valorAplicado;
    }

    public void setValorAplicado(BigDecimal valorAplicado) {
        this.valorAplicado = valorAplicado;
    }

    public BigDecimal getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(BigDecimal rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "InvestimentoDTO{" +
                "idInvestimento=" + idInvestimento +
                ", nomeAtivo='" + nomeAtivo + '\'' +
                ", valorAplicado=" + valorAplicado +
                ", rentabilidade=" + rentabilidade +
                ", usuarioId=" + usuarioId +
                '}';
    }
}