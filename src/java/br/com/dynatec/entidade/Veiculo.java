/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.entidade;

import br.com.dynantec.type.StatusPresencaCarro;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jura
 */
@Entity
@Table(name = "veiculos")
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max = 32)
    @NotNull
    @NotEmpty
    @Column(name = "cartao", nullable = false, unique = true)
    private String cartao;

    @Size(max = 255)
    @NotNull
    @NotEmpty
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Size(max = 255)
    @NotNull
    @NotEmpty
    @Column(name = "placa", nullable = false)
    private String placa;

    @NotNull
    @Column(name = "vencimento", nullable = false)
    private Integer diaVencimento;

    @NotNull
    @Column(nullable = false)
    private Double valorMensalidade;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPresencaCarro status;

    @NotNull
    @Column(nullable = false)
    private boolean cartaoSuspenso;

//    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
//    @ManyToOne
//    private Pessoa pessoaVeiculo;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Veiculo() {
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(Integer diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(Double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public StatusPresencaCarro getStatus() {
        return status;
    }

    public void setStatus(StatusPresencaCarro status) {
        this.status = status;
    }

    public boolean isCartaoSuspenso() {
        return cartaoSuspenso;
    }

    public void setCartaoSuspenso(boolean cartaoSuspenso) {
        this.cartaoSuspenso = cartaoSuspenso;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
