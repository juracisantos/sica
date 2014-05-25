/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.persistencia;

import br.com.dynatec.entidade.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jura
 */
public class PessoaDao extends PersistenciaJpa {

    public List<Pessoa> findAll() {
        return findAll(Pessoa.class, "select e from Pessoa e");
    }

    public Pessoa find(Integer idPessoa) {
        return find(Pessoa.class, idPessoa);
    }

    public Pessoa findByNome(String nome) {
        return find(Pessoa.class, "select e from Pessoa e where e.nome ilike ?1", "%" + nome + "%");
    }
    
    public Pessoa findByEmail(String email) {
        return find(Pessoa.class, "select e from Pessoa e where e.email = ?1", email);
    }

    public Pessoa salvar(Pessoa e) throws Exception {
        return save(Pessoa.class, e);
    }

    public Pessoa removePessoa(Pessoa e) throws Exception {
        return remove(Pessoa.class, e);
    }

    public boolean removePessoa(Integer idPessoa) {
        return (execute("delete from Pessoa e where e.id = ?1", idPessoa) >= 0);
    }
    
    public int getQtdPessoas() {
        List<Pessoa> retorno = new ArrayList<>();
        retorno = findAll();
        if (retorno == null) {
            return 0;
        }
        return retorno.size();
    }
}
