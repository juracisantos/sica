package br.com.dynatec.entidade;

import br.com.dynantec.type.FoneEmail;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "telefone_email")
public class TelefoneEmail implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FoneEmail tipo = FoneEmail.FIXO;
    @NotNull
    @NotEmpty
    @Column(length = 150)
    private String numero;
    @Column(length = 150)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public FoneEmail getTipo() {
        return tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setTipo(FoneEmail tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TelefoneEmail other = (TelefoneEmail) obj;
        if (!Objects.equals(this.id, other.id) && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return !((this.numero == null) ? (other.numero != null) : !this.numero.equals(other.numero));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 73 * hash + (this.numero != null ? this.numero.hashCode() : 0);
        return hash;
    }

}
