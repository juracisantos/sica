/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.entidade;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jura
 */
@XmlRootElement
@Entity
@Table(name = "regras")
public class Regra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "O campo Descrição deve ser preechido.")
    @NotEmpty(message = "O campo Descrição deve ser preechido.")
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer sequencia;

    @NotNull(message = "O campo Intervalo Hora deve ser preechido.")
    @Column(nullable = false)
    private Integer intervaloHora;

    @NotNull(message = "O campo Intervalo Minuto deve ser preechido.")
    @Column(nullable = false)
    private Integer intervaloMinuto;

    @Column()
    private Double valor;

    @JoinColumn(referencedColumnName = "id", name = "tabela_id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Tabela tabela;

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

    public Integer getIntervaloHora() {
        return intervaloHora;
    }

    public void setIntervaloHora(Integer intervaloHora) {
        this.intervaloHora = intervaloHora;
    }

    public Integer getIntervaloMinuto() {
        return intervaloMinuto;
    }

    public void setIntervaloMinuto(Integer intervaloMinuto) {
        this.intervaloMinuto = intervaloMinuto;
    }

    public Double getValor() {
        return this.valor == null ? 0.0d : this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Tabela getTabela() {
        return tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Regra other = (Regra) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
