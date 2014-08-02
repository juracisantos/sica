/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.entidade;

import br.com.dynantec.type.StatusPresencaCarro;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jura
 */
@XmlRootElement
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
    @NotNull(message = "Cartão - preenchimento obrigatório.")
    @NotEmpty(message = "Cartão - preenchimento obrigatório.")
    @Column(name = "cartao", nullable = false, unique = true)
    private String cartao;

    @Size(max = 255)
    @Column(name = "descricao")
    private String descricao;

    @Size(max = 255)
    @Column(name = "placa", nullable = false)
    private String placa;

    @NotNull(message = "Vencimento - preenchimento obrigatório.")
    @Column(name = "vencimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    @NotNull(message = "Valor da mensalidade - preenchimento obrigatório.")
    @Column(nullable = false)
    private Double valorMensalidade;

    @NotNull(message = "Situação - preenchimento obrigatório.")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPresencaCarro status;

    @NotNull(message = "Cartão suspenso - preenchimento obrigatório.")
    @Column(nullable = false)
    private boolean cartaoSuspenso;

    @Column
    @Temporal(TemporalType.DATE)
    private Date liberado_ate;

    @NotNull
    @Column(nullable = false)
    private boolean cartaoMestre;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @OneToMany(mappedBy = "veiculo", targetEntity = VeiculoLogStatusPreseca.class, cascade = CascadeType.ALL)
    private final List<VeiculoLogStatusPreseca> logsStatusPresenca = new LinkedList<>();

    public Veiculo() {
        this.cartaoMestre = false;
        this.cartaoSuspenso = false;
        this.status = StatusPresencaCarro.AUSENTE;
        this.valorMensalidade = 0.0d;
        this.dataVencimento = new Date();
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

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
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

    public boolean isCartaoMestre() {
        return cartaoMestre;
    }

    public void setCartaoMestre(boolean cartaoMestre) {
        this.cartaoMestre = cartaoMestre;
    }

    public Date getLiberado_ate() {
        return liberado_ate;
    }

    public void setLiberado_ate(Date liberado_ate) {
        this.liberado_ate = liberado_ate;
    }

    public List<VeiculoLogStatusPreseca> getLogsStatusPresenca() {
        return logsStatusPresenca;
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
