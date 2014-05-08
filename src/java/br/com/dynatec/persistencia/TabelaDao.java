/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.persistencia;

import br.com.dynatec.entidade.Tabela;
import java.util.List;

/**
 *
 * @author jura
 */
public class TabelaDao extends PersistenciaJpa {

    public List<Tabela> findAll() {
        return findAll(Tabela.class, "select e from Tabela e");
    }

    public Tabela find(Integer idTabela) {
        return find(Tabela.class, idTabela);
    }

    public Tabela first() {
        return first(Tabela.class, "select e from Tabela e");
    }
    
    public Tabela salvar(Tabela e) throws Exception {
        return save(Tabela.class, e);
    }

    public Tabela removeTabela(Tabela e) throws Exception {
        return remove(Tabela.class, e);
    }

    public boolean removeTabela(Integer idTabela) {
        return (execute("delete from Tabela e where e.id = ?1", idTabela) >= 0);
    }

}
