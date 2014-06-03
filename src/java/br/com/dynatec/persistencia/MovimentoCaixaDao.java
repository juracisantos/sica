package br.com.dynatec.persistencia;

import br.com.dynatec.entidade.MovimentoCaixa;
import java.util.Date;
import java.util.List;

public class MovimentoCaixaDao extends PersistenciaJpa {

    public List<MovimentoCaixa> findAll() {
        return findAll(MovimentoCaixa.class, "select e from MovimentoCaixa e");
    }
    
    public List<MovimentoCaixa> findByDataMovimento(Date dataMovimento) {
        return findAll(MovimentoCaixa.class, "select e from MovimentoCaixa e where e.dataMovimento = ?1", dataMovimento);
    }
    
    public MovimentoCaixa salvar(MovimentoCaixa movimentoCaixa) throws Exception {
        return save(MovimentoCaixa.class, movimentoCaixa);
    }
    
    public MovimentoCaixa find(Integer idMovimentoCaixa) {
        return find(MovimentoCaixa.class, idMovimentoCaixa);
    }
}