/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.negocio;

import br.com.dynatec.entidade.Configuracao;
import br.com.dynatec.persistencia.ConfiguracaoDao;
import java.util.List;

/**
 *
 * @author jura
 */
public class ConfiguracaoNeg {
   
    private final ConfiguracaoDao configuracaoDao;
    
    public ConfiguracaoNeg() {
        this.configuracaoDao = new ConfiguracaoDao();
    }
    
    public List<Configuracao> findAll() {
        return configuracaoDao.findAll();
    }
    
    public Configuracao find(Integer idConfiguracao) {
        return configuracaoDao.find(idConfiguracao);
    }
    
    public Configuracao findByDescricao(String descricao) {
        return configuracaoDao.findByDescricao(descricao);
    }
    
    public Configuracao salvar(Configuracao grupo) throws Exception  {
        return configuracaoDao.salvar(grupo);
    }
}
