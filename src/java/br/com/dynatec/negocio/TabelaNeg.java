package br.com.dynatec.negocio;

import br.com.dynatec.entidade.Tabela;
import br.com.dynatec.persistencia.TabelaDao;
import java.util.List;

public class TabelaNeg {

    private final TabelaDao tabelaDao;
    
    public TabelaNeg() {
        this.tabelaDao = new TabelaDao();
    }
    
    public List<Tabela> findAll() {
        return this.tabelaDao.findAll();
    }
    
    public Tabela first() {
        return this.tabelaDao.first();
    }
    
    public Tabela find(Integer id) {
        return this.tabelaDao.find(id);
    }
    
    public Tabela salvar(Tabela e) throws Exception  {
        return tabelaDao.salvar(e);
    }
        
}
