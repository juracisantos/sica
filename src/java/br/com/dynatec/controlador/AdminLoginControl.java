package br.com.dynatec.controlador;

import br.com.dynatec.entidade.Pessoa;
import br.com.dynatec.entidade.Usuario;
import br.com.dynatec.negocio.Seed;
import br.com.dynatec.negocio.UsuarioNeg;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@ManagedBean
@SessionScoped
public class AdminLoginControl extends BaseControlador<Usuario> implements Serializable {

    private static final long serialVersionUID = 9l;

    private final UsuarioNeg negocio = new UsuarioNeg();
    private Usuario usuario = new Usuario();
    private Pessoa pessoa = new Pessoa();

    @NotNull(message = "O campo Login não pode ser nulo.")
    @NotEmpty(message = "O campo Login não pode ser vazio.")
    private String userName;
    @NotNull(message = "O campo Senha não pode ser nulo")
    @NotEmpty(message = "O campo Senha não pode ser vazio.")
    @Length(min = 6, max = 20)
    private String senha;
    private boolean usuarioLogado = false;

    public String doLogin() throws Exception {

        Seed.populaBanco();

        int qtdUsuarios = negocio.getQtdUsuarios();
        if (qtdUsuarios == 0) {
            return "/primeiroacesso/usuarioadd.faces";
        }

        usuario = negocio.findByUserName(userName);
        if ((usuario != null) && (negocio.validaSenha(usuario.getSenha(), senha))) {
            usuarioLogado = true;
        } else {
            usuarioLogado = false;
        }

        if (isUsuarioLogado()) {
            HttpSession session = UtilFaces.getSession();
            session.setAttribute("usuario_id", usuario.getId());
            session.setAttribute("usuario_nome", usuario.getNome());
            session.setAttribute("usuario_grupo", usuario.getGrupo().getNome());

            return "/index.jsf";
        } else {
            return "/loginerr.jsf";
        }
    }

    public String doFinishCreateUsuarioPrimeiroAcesso() {
        try {            
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
