/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.entidade;

import br.jus.tjgo.bnmp.util.UtilDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jura
 */
@Entity
@Table(name = "acessos")
public class Acesso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "seq_acesso", sequenceName = "seq_acesso", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "O campo Cartão deve ser preechido.")
    @NotEmpty(message = "O campo Cartão deve ser preechido.")
    @Column(nullable = false)
    private String cartao;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date registroSaida;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida;

    @Column(name = "limite_para_sair")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limiteParaSair;

    @Column()
    @Temporal(TemporalType.DATE)
    private Date dataTransacaoFinaceira;

    @Column
    private Double valorCobrado;

    @Column
    private Double desconto;

    @Transient
    private Double valorRecebido;

    @Transient
    private Double valorAReceber;

    @Transient
    private Double troco;

    @Transient
    private String permancencia;

    @Column
    private String placaVeiculo;

    @Column
    private Boolean ativo;

    @Column
    private Boolean liberado;

    @JoinColumn(referencedColumnName = "id", name = "usuario_registrou_entrada_id")
    @ManyToOne(optional = false)
    private Usuario usuarioRegistrouEntrada;

    @JoinColumn(referencedColumnName = "id", name = "usuario_registrou_saida_id")
    @ManyToOne(optional = false)
    private Usuario usuarioRegistrouSaida;

    @JoinColumn(referencedColumnName = "id", name = "tabela_id")
    @ManyToOne(optional = false)
    private Tabela tabela;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getRegistroSaida() {
        return registroSaida;
    }

    public void setRegistroSaida(Date registroSaida) {
        this.registroSaida = registroSaida;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public Double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(Double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Double getDesconto() {
        return desconto == null ? 0.0d : this.desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Usuario getUsuarioRegistrouEntrada() {
        return usuarioRegistrouEntrada;
    }

    public void setUsuarioRegistrouEntrada(Usuario usuarioRegistrouEntrada) {
        this.usuarioRegistrouEntrada = usuarioRegistrouEntrada;
    }

    public Usuario getUsuarioRegistrouSaida() {
        return usuarioRegistrouSaida;
    }

    public void setUsuarioRegistrouSaida(Usuario usuarioRegistrouSaida) {
        this.usuarioRegistrouSaida = usuarioRegistrouSaida;
    }

    public Tabela getTabela() {
        return tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public Double getValorRecebido() {
        return valorRecebido == null ? 0.0d : valorRecebido;
    }

    public void setValorRecebido(Double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public Double getTroco() {
        if (this.getValorRecebido() != 0) {
            return this.getValorRecebido() - this.getValorAReceber();
        } else {
            return 0.0d;
        }
            
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getPermancencia() {
        return UtilDateTime.formataHoraHHMM(permancencia);
    }

    public void setPermancencia(String permancencia) {
        this.permancencia = permancencia;
    }

    public Double getValorAReceber() {
        if (this.valorCobrado != null) {
            return this.getValorCobrado() - this.getDesconto();
        } else {
            return 0.0d;
        }
    }

    public void setValorAReceber(Double valorAReceber) {
        this.valorAReceber = valorAReceber;
    }

    public Boolean isLiberado() {
        return liberado;
    }

    public void setLiberado(Boolean liberado) {
        this.liberado = liberado;
    }

    public Date getLimiteParaSair() {
        return limiteParaSair;
    }

    public void setLimiteParaSair(Date limiteParaSair) {
        this.limiteParaSair = limiteParaSair;
    }

    public Date getDataTransacaoFinaceira() {
        return dataTransacaoFinaceira;
    }

    public void setDataTransacaoFinaceira(Date dataTransacaoFinaceira) {
        this.dataTransacaoFinaceira = dataTransacaoFinaceira;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Acesso other = (Acesso) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
