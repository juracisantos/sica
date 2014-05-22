/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.persistencia;

import br.com.dynatec.entidade.Acesso;
import java.util.List;

/**
 *
 * @author jura
 */
public class AcessoDao extends PersistenciaJpa {

    public List<Acesso> findAll() {
        return findAll(Acesso.class, "select e from Acesso e");
    }

    public Acesso find(Integer idAcesso) {
        return find(Acesso.class, idAcesso);
    }

    public Acesso salvar(Acesso e) throws Exception {        
        return save(Acesso.class, e);
    }

    public Acesso removeAcesso(Acesso e) throws Exception {
        e.setAtivo(Boolean.TRUE);
        return salvar(e);
    }
    
    public Acesso find_by_numero_cartao(String cartao) {
        return find(Acesso.class, "select e from Acesso e where e.cartao = ?1", cartao);
    }

    public boolean removeAcesso(Integer idAcesso) {
        return (execute("update acesso e where e.id = ?1", idAcesso) >= 0);
    }

}
