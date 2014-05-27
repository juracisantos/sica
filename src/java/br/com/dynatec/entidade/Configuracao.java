/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.entidade;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jura
 */
@Entity
@Table(name = "configuracoes")
public class Configuracao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @NotNull
    @NotEmpty(message = "O campo Descrição deve ser preechido.")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "A tolerância deve ser informada.")
    @Column(nullable = false)
    private Integer tolerancia;

    @NotNull(message = "A tolerância em dias para pagamento do mensalista deve ser informada.")
    @Column(nullable = false)
    private Integer toleranciaMensalista;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(Integer tolerancia) {
        this.tolerancia = tolerancia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getToleranciaMensalista() {
        return toleranciaMensalista;
    }

    public void setToleranciaMensalista(Integer toleranciaMensalista) {
        this.toleranciaMensalista = toleranciaMensalista;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Configuracao other = (Configuracao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
