/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.entidade;

import br.com.dynantec.type.TipoMovimento;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jura
 */
@Entity
@Table(name = "moveimentos_caixa")
public class MovimentoCaixa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Basic(optional = false)
    private Integer id;

    public MovimentoCaixa() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.dataMovimento = new Date();
        this.ativo = true;
        this.mensalisa = false;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected TipoMovimento tipoMovimento;

    @Column
    @NotNull(message = "O valor deve ser informado.")
    private Double valor;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataMovimento;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column
    private Boolean mensalisa;

    @Column
    @Lob
    private String observacao;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "usuario_id")
    private Integer usuario_id;

    @Transient
    private String nomePessoa;

    @Transient
    private Double valorAReceber;

    @Column
    private Double desconto;

    @Transient
    private Double valorRecebido;

    @Transient
    private Double troco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Double getValor() {
        return this.valor == null ? 0.0d : this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean isMensalisa() {
        return mensalisa;
    }

    public void setMensalisa(Boolean mensalisa) {
        this.mensalisa = mensalisa;
    }

    public Double getDesconto() {
        return desconto == null ? 0.0d : desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValorRecebido() {
        return this.valorRecebido == null ? 0.0d : this.valorRecebido;
    }

    public void setValorRecebido(Double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public Double getTroco() {
        return this.getValorRecebido() - this.getValorAReceber();
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }

    public Double getValorAReceber() {
        return this.getValor() - this.getDesconto();
    }

    public void setValorAReceber(Double valorAReceber) {
        this.valorAReceber = valorAReceber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final MovimentoCaixa other = (MovimentoCaixa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
