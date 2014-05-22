/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.entidade;

import br.com.dynantec.comparator.RegrasComparator;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jura
 */
@Entity
@Table(name = "tabelas")
public class Tabela implements Serializable {

    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @NotNull
    @NotEmpty(message = "O campo Nome deve ser preechido.")   
    @Column(nullable = false, unique = true)
    private String nome;

    @NotNull(message = "O campo Valor deve ser preechido.")
    @Column(nullable = false)
    private Double valor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabela")
    private List<Regra> regras = new LinkedList<>();

    public void addRegra(Regra e) {
        e.setTabela(this);
        e.setSequencia(this.regras.size()+1);
        this.getRegras().add(e);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Regra> getRegras() {
        Collections.sort(regras, new RegrasComparator());
        return regras;
    }

    public void setRegras(List<Regra> regras) {
        this.regras = regras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Tabela other = (Tabela) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    
}
