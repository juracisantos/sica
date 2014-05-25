package br.com.dynatec.persistencia;

import br.com.dynatec.entidade.Configuracao;
import java.util.List;

public class ConfiguracaoDao extends PersistenciaJpa {

    public List<Configuracao> findAll() {
        return findAll(Configuracao.class, "select e from Configuracao e");
    }
    
    public Configuracao salvar(Configuracao configuracao) throws Exception {
        return save(Configuracao.class, configuracao);
    }
    
    public Configuracao find(Integer idConfiguracao) {
        return find(Configuracao.class, idConfiguracao);
    }
    
    public Configuracao findByDescricao(String descricao) {
        return find(Configuracao.class, "select e from Configuracao e where e.descricao = ?1", descricao);
    }
}
