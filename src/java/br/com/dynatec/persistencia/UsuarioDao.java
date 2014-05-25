/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.persistencia;

import br.com.dynatec.entidade.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jura
 */
public class UsuarioDao extends PersistenciaJpa {

    public List<Usuario> findAll() {
        return findAll(Usuario.class, "select e from Usuario e");
    }

    public Usuario find(Integer idUsuario) {
        return find(Usuario.class, idUsuario);
    }

    public Usuario findByUserName(String username) {
        return find(Usuario.class, "select e from Usuario e where e.nome = ?1", username);
    }

    public Usuario salvar(Usuario e) throws Exception {
        return save(Usuario.class, e);
    }

    public Usuario removeUsuario(Usuario e) throws Exception {
        return remove(Usuario.class, e);
    }

    public boolean removeUsuario(Integer idUsuario) {
        return (execute("delete from Usuario e where e.id = ?1", idUsuario) >= 0);
    }
    
    public int getQtdUsuarios() {
        List<Usuario> retorno = new ArrayList<Usuario>();
        retorno = findAll();
        if (retorno == null) {
            return 0;
        }
        return retorno.size();
    }
}
