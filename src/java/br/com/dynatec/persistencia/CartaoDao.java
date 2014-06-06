/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.persistencia;

import br.com.dynatec.entidade.Veiculo;
import java.util.List;

/**
 *
 * @author jura
 */
public class CartaoDao extends PersistenciaJpa {

    public List<Veiculo> findAll() {
        return findAll(Veiculo.class, "select e from Veiculo e where e.cartaoMestre = true");
    }
       
    public String gerarNumeroCartao() {
        return executeNativeQuery("select get_matricula()");
    }
    
    public Veiculo salvar(Veiculo e) throws Exception {
        return save(Veiculo.class, e);
    }
    
    public Veiculo getVeiculo(String cartao) {
      return find(Veiculo.class, "select e from Veiculo e where e.cartao = 1?", cartao);        
    }
}
