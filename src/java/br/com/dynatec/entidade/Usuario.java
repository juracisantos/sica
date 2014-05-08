/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author juraci
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @NotNull
    @NotEmpty(message = "O campo Nome deve ser preechido.")
    @Column(nullable = false)
    private String nome;
    @NotNull
    @NotEmpty(message = "O e-mail não pode ser vazio.")
    @Email(message = "O e-mail informado não é válido.")
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    @NotEmpty(message = "A senha não pode ser vazia.")
    @Column(nullable = false)
    private String senha;

    @Transient
    private String confSenha;

    @Column(name = "e_admin")
    private Boolean eAdmin;

    @Column(name = "ativo")
    private Boolean ativo;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false, updatable = false)
    private Pessoa pessoa = new Pessoa();

    @JoinColumn(referencedColumnName = "id", name = "grupo_id")
    @ManyToOne(optional = false)
    private Grupo grupo;

    public Usuario() {
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getConfSenha() {
        return confSenha;
    }

    public void setConfSenha(String confSenha) {
        this.confSenha = confSenha;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean geteAdmin() {
        return eAdmin;
    }

    public void seteAdmin(Boolean eAdmin) {
        this.eAdmin = eAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.umbrella.selo.entidade.Users[ id=" + id + " ]";
    }

}
