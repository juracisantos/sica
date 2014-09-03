package br.com.dynatec.persistencia;

import br.com.dynatec.entidade.MovimentoCaixa;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class RelatoriosSistemaDAO extends PersistenciaJpa {

    public List<Map<String, Object>> mensalisataPorPeriodo(Date inicio, Date termino) {
        List retorno = null;
        try {
            StringBuilder qry = new StringBuilder();

            qry.append("select entrada, saida, cartao, datatransacaofinaceira, (valorcobrado + ultimo_valor_pago) as valor_a_pagar, ");
            qry.append("(desconto + ultimo_desconto_dado) as desconto, ");
            qry.append("(valorcobrado + ultimo_valor_pago) - (desconto + ultimo_desconto_dado) as valorpago");
            qry.append("from acessos where entrada between ?1 and ?2 ");
            qry.append("order by entrada");                       
            Query query = getEm().createQuery(qry.toString());
            query.setParameter(1, inicio);
            query.setParameter(2, termino);

            retorno = query.getResultList();

            System.out.println("Selected row count : " + retorno.size());

            getEm().getTransaction().commit();
            retorno = findAllWithNativeQuery(qry.toString(), inicio, termino);
        } catch (Exception e) {
            getEm().getTransaction().rollback();
            e.printStackTrace();
        }
        return retorno;
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
