/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author juraci
 */
@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 33L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Size(max = 255)
    @Column(name = "nome")
    @NotEmpty(message = "O Nome não pode ser vazio.")
    private String nome;

    @NotNull
    @NotEmpty(message = "O e-mail não pode ser vazio.")
    @Email(message = "O e-mail informado não é válido.")
    @Column(nullable = false, unique = true)
    private String email;

    @Transient
    private Integer qtdVeiculos;

    @Size(max = 255)
    @Column(name = "cpf", unique = true)
    private String cpf;
    @Size(max = 255)
    @Column(name = "rg")
    private String rg;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "ativo")
    private Boolean ativo;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    private Endereco endereco;

    @OneToMany(mappedBy = "pessoa", targetEntity = Veiculo.class, cascade = CascadeType.ALL)
    private final List<Veiculo> veiculos = new LinkedList<>();

    @OneToMany(mappedBy = "pessoa", targetEntity = TelefoneEmail.class, cascade = CascadeType.ALL)
    private final List<TelefoneEmail> telefones = new LinkedList<>();

    public boolean isPossuiTelefone() {
        boolean retorno = !this.telefones.isEmpty();
        return retorno;
    }

    public Pessoa() {
    }

    public void addTelefone(TelefoneEmail f) {
        this.getTelefones().add(f);
        f.setPessoa(this);
    }

    public void addVeidulo(Veiculo v) {
        this.getVeiculos().add(v);
        v.setPessoa(this);
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public List<TelefoneEmail> getTelefones() {
        return telefones;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getQtdVeiculos() {        
        return this.veiculos.size();
    }

    public void setQtdVeiculos(Integer qtdVeiculos) {
        this.qtdVeiculos = qtdVeiculos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CPF: " + this.cpf + " - Nome: " + this.nome;
    }
}
