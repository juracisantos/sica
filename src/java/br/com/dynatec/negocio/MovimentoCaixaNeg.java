/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.negocio;

import br.com.dynantec.comparator.MovimentoCaixaComparator;
import br.com.dynantec.type.TipoMovimento;
import br.com.dynatec.entidade.Acesso;
import br.com.dynatec.entidade.MovimentoCaixa;
import br.com.dynatec.helper.fianceiro.ExtratoDiario;
import br.com.dynatec.helper.fianceiro.Movimento;
import br.com.dynatec.persistencia.MovimentoCaixaDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jura
 */
public class MovimentoCaixaNeg {

    private final MovimentoCaixaDao movimentoCaixaDao;
    private final AcessoNeg acessoNeg;

    public MovimentoCaixaNeg() {
        this.movimentoCaixaDao = new MovimentoCaixaDao();
        this.acessoNeg = new AcessoNeg();
    }

    public List<MovimentoCaixa> findByDataMovimento(Date dataMovimento) {
        return this.movimentoCaixaDao.findByDataMovimento(dataMovimento);
    }

    public List<MovimentoCaixa> findAll() {
        return movimentoCaixaDao.findAll();
    }

    public MovimentoCaixa find(Integer idMovimentoCaixa) {
        return movimentoCaixaDao.find(idMovimentoCaixa);
    }

    public MovimentoCaixa salvar(MovimentoCaixa movimentoCaixa) throws Exception {
        return movimentoCaixaDao.salvar(movimentoCaixa);
    }

    public ExtratoDiario extratoDiaSintetico(Date dataDia) {
        Double saldoAtual;
        ExtratoDiario extrato = new ExtratoDiario();

        //Lançamentos de entrada e saída
        List<MovimentoCaixa> movimentos = this.findByDataMovimento(dataDia);
        
        /** Caso não tenha cadastrado nenhum lançamento na data atual é preenchido um
         * objeto e adicionado a lista, apenas para mostrar na tela que não foi 
         * cadastrado nenhum lançamento inicial.
         */
                
        if (movimentos.isEmpty()) {
            movimentos = new ArrayList<>();
            MovimentoCaixa mc = new MovimentoCaixa();
            mc.setDataMovimento(dataDia);
            mc.setTipoMovimento(TipoMovimento.DEPOSITO);
            mc.setValor(0.0d);
            movimentos.add(mc);
        }

        //Lançamentos de netrada e saída de veículos
        List<Acesso> acessos = acessoNeg.findByDataTransacaoFinanceira(dataDia);

        extrato.setDia(dataDia);
        Collections.sort(movimentos, new MovimentoCaixaComparator());
        saldoAtual = movimentos.get(0).getValor();
        
        if (movimentos.get(0).getTipoMovimento().equals(TipoMovimento.RETIRADA)) {
           saldoAtual = saldoAtual * -1;
        }
        extrato.setSaldoInicial(saldoAtual); 

        //Saldo inicial, deve ser retirado, pois já foi valorizado em saldoAtual;
        movimentos.remove(0);

        for (MovimentoCaixa mc : movimentos) {
            extrato.getMovimentos().add(new Movimento(mc.getTipoMovimento(), mc.getValor()));

            if (mc.getTipoMovimento().equals(TipoMovimento.DEPOSITO)) {
                saldoAtual += mc.getValor();
            } else {
                saldoAtual -= mc.getValor();
            }
        }

        for (Acesso acesso : acessos) {
            if (acesso.getValorAReceber() != null) {
                saldoAtual += acesso.getTotalPago();
            }
        }

        extrato.setSaldoAtual(saldoAtual);

        return extrato;
    }
}
