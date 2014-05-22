package br.com.dynatec.controlador;

import br.com.dynatec.entidade.Pessoa;
import br.com.dynatec.entidade.Usuario;
import br.com.dynatec.negocio.UsuarioNeg;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@ManagedBean
@SessionScoped
public class AdminLoginControl extends BaseControlador<Usuario> implements Serializable {

    private static final long serialVersionUID = 9l;
    
    private final UsuarioNeg negocio = new UsuarioNeg();
    private Usuario usuario = new Usuario();
    private Pessoa pessoa = new Pessoa();

    @NotNull(message = "O campo E-Mail não pode ser nulo.")
    @NotEmpty(message = "O campo E-Mail não pode ser vazio.")
    @Email(message = "O E-Mail não é válido, digite um e-mail válido.")
    private String email;
    @NotNull(message = "O campo Senha não pode ser nulo")
    @NotEmpty(message = "O campo Senha não pode ser vazio.")
    @Min(6)
    private String senha;
    private boolean usuarioLogado = false;

    public String doLogin() throws Exception {
        
        int qtdUsuarios = negocio.getQtdUsuarios();
        if (qtdUsuarios == 0) {
            return "/primeiroacesso/usuarioadd.faces";
        }
        
        usuario = negocio.findByEmail(email);
        if (negocio.validaSenha(usuario.getSenha(), senha)) {
            usuarioLogado = true;
        } else {
            usuarioLogado = false;
        }

        if (isUsuarioLogado()) {

            return "/index.jsf";
        } else {
            return "/loginerr.jsf";
        }
    }

    public String doFinishCreateUsuarioPrimeiroAcesso() {
        try {            
            usuario = negocio.Criptografa(usuario);
            usuario.setPessoa(this.pessoa);
            this.pessoa.setCreatedAt(new Date());
            this.pessoa.setUpdatedAt(new Date());
            this.pessoa.setAtivo(true);
            this.usuario.setAtivo(true);
            usuario = negocio.salvar(usuario);            
            return "/index.jsf";
        } catch (Exception ex) {
            UtilFaces.addErrorMessage(ex.getLocalizedMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(boolean usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
}
