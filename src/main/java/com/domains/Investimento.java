package com.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "investimento")
@SequenceGenerator(
        name = "seq_investimento",
        sequenceName = "seq_investimento",
        allocationSize = 1
)
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_investimento")
    private Long idInvestimento;

    @NotBlank
    @Column(nullable=false, length=100)
    private String tipo;

    @NotBlank
    @Column(nullable=false, length=100)
    private String nomeAtivo;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    @Column(precision = 18, scale = 3, nullable = false)
    private BigDecimal valorAplicado;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    @Column(precision = 18, scale = 3, nullable = false)
    private BigDecimal rentabilidade;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Investimento() {
    }

    public Investimento(Long idInvestimento, String tipo, String nomeAtivo, BigDecimal valorAplicado, BigDecimal rentabilidade, LocalDate data, Usuario usuario) {
        this.idInvestimento = idInvestimento;
        this.tipo = tipo;
        this.nomeAtivo = nomeAtivo;
        this.valorAplicado = valorAplicado;
        this.rentabilidade = rentabilidade;
        this.data = data;
        this.usuario = usuario;
    }

    public Long getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(Long idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
