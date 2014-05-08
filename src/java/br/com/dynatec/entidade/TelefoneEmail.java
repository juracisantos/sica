package br.com.dynatec.entidade;

import br.com.dynantec.type.FoneEmail;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="telefone_email")
public class TelefoneEmail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Basic(optional = false)
    private Integer id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FoneEmail tipo = FoneEmail.FIXO;
    @NotNull
    @NotEmpty
    @Column(length = 20)
    private String numero;
    @Column(length = 150)
    private String observacao;       
    
    public FoneEmail getTipo() {
        return tipo;
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

//    public Cartorio getPessoaTelefoneEmail() {
//        return pessoaTelefoneEmail;
//    }
//
//    public void setPessoaTelefoneEmail(Cartorio pessoaTelefoneEmail) {
//        this.pessoaTelefoneEmail = pessoaTelefoneEmail;
//    }
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
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.numero == null) ? (other.numero != null) : !this.numero.equals(other.numero)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 73 * hash + (this.numero != null ? this.numero.hashCode() : 0);
        return hash;
    }

    
}
