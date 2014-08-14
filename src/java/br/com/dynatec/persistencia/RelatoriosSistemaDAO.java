package br.com.dynatec.persistencia;

import br.com.dynatec.entidade.MovimentoCaixa;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RelatoriosSistemaDAO extends PersistenciaJpa {

    public List<Map<String, Object>> mensalisataPorPeriodo(Date inicio, Date termino) {
        
        StringBuilder qry = new StringBuilder();
        qry.append("select new map(datamovimento, desconto, observacao, usuario_id, u.nome, valor, mensalista_id, p.cpf as cpf_mensalista, p.nome as mensalista)");
        qry.append(" from moveimentos_caixa mc");
        qry.append(" left join usuarios u on u.id = mc.usuario_id");
        qry.append(" left join pessoas p on p.id = mc.mensalista_id");
        qry.append(" where mc.mensalisa = true");
        qry.append(" and tipomovimento = 'DEPOSITO'");
        qry.append(" and datamovimento between ?1 and ?2");
        
        return findAll(qry.toString(), inicio, termino);
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