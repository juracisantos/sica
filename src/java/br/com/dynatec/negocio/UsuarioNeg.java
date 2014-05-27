package br.com.dynatec.negocio;

import br.com.dynantec.criptografia.CriptografiaSHA512;
import br.com.dynatec.entidade.Usuario;
import br.com.dynatec.persistencia.UsuarioDao;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

public class UsuarioNeg {

    private final UsuarioDao usuarioDao;

    public UsuarioNeg() {
        this.usuarioDao = new UsuarioDao();
    }

    public List<Usuario> findAll() {
        return this.usuarioDao.findAll();
    }

    public int getQtdUsuarios() {
        return this.usuarioDao.getQtdUsuarios();
    }

    public Usuario salvar(Usuario usuario) throws Exception {
        if (usuario.getId() == null) {
            usuario.setAtivo(Boolean.TRUE);
            usuario = this.criptografa(usuario);
            usuario.setCreatedAt(new Date());            
        }
        usuario.setUpdatedAt(new Date());
        return usuarioDao.salvar(usuario);
    }

    public Usuario findByUserName(String username) {
        return this.usuarioDao.findByUserName(username);
    }

    public Usuario criptografa(Usuario usuario) throws Exception {
        if ((!usuario.getSenha().equals(usuario.getConfSenha()))
                || ("".equals(usuario.getSenha()))) {
            throw new Exception("Senha não confere.");
        }

        CriptografiaSHA512 cryptography = new CriptografiaSHA512();

        try {
            usuario.setSenha(cryptography.encrypt(usuario.getSenha()));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e.getMessage());
        }

        return usuario;
    }

    public boolean validaSenha(String senhaCriptografada, String senhaDigitada) throws Exception {
        try {
            if (senhaDigitada.isEmpty()) {
                throw new Exception("Senha não pode ser vazia.");
            }
            CriptografiaSHA512 cryptography = new CriptografiaSHA512();
            return senhaCriptografada.equals(cryptography.encrypt(senhaDigitada));
        } catch (NoSuchAlgorithmException ex) {
            return false;
        }
    }

}
