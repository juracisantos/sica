package br.com.dynatec.negocio;

import br.com.dynatec.entidade.Acesso;
import br.com.dynatec.entidade.Pessoa;
import br.com.dynatec.entidade.Regra;
import br.com.dynatec.persistencia.AcessoDao;
import br.jus.tjgo.bnmp.util.UtilDateTime;
import java.util.Date;
import java.util.List;

public class AcessoNeg {

    private final AcessoDao acessoDao;
    private final PessoaNeg pessoaNeg;

    public AcessoNeg() {
        this.acessoDao = new AcessoDao();
        this.pessoaNeg = new PessoaNeg();
    }

    public Acesso find_by_numero_cartao(String cartao) {
        return this.acessoDao.find_by_numero_cartao(cartao);
    }

    public List<Acesso> findAll() {
        return this.acessoDao.findAll();
    }
    
    public List<Acesso> findByDataTransacaoFinanceira(Date dataTransacao) {
        return this.acessoDao.findByDataTransacaoFinanceira(dataTransacao);
    }

    public Acesso find(Integer id) {
        return this.acessoDao.find(id);
    }

    public Acesso salvar(Acesso e) throws Exception {
        e.setLiberado(Boolean.TRUE);
        return acessoDao.salvar(e);
    }
    
    public Pessoa salvarPessoa(Pessoa p) throws Exception {
        return this.pessoaNeg.salvar(p);
    }

    public Pessoa findPessoaByCartao(String cartao) {
        return this.pessoaNeg.findByCartao(cartao);
    }
    
    public Acesso consultarECalcular(Acesso e) {        
        Acesso acesso = acessoDao.find_by_numero_cartao(e.getCartao());

        if (acesso != null) {
            acesso.setTabela(e.getTabela());
            acesso.setRegistroSaida(new Date());
            acesso = calcular(acesso);
        }

        return acesso;
    }

    private Acesso calcular(Acesso acesso) {
        List<Regra> regras = acesso.getTabela().getRegras();

        Integer ultimo_intervalo = 0;
        Double valor_ultimo_periodo_regra = 0d;
        Integer minAcumulados;
        Integer permancencia;

        permancencia = (int) UtilDateTime.diferencaEmMinutos(acesso.getEntrada(), acesso.getRegistroSaida());
        Double valor = 0d;
        minAcumulados = 0;

        boolean intervalor_menor_que_maximo_regra = false;
        for (Regra regra : regras) {
            ultimo_intervalo = (regra.getIntervaloHora() * 60) + regra.getIntervaloMinuto();
            minAcumulados += ultimo_intervalo;

            valor_ultimo_periodo_regra = regra.getValor();
            valor += valor_ultimo_periodo_regra;
            if (permancencia <= minAcumulados) {
                intervalor_menor_que_maximo_regra = true;
                break;
            }
        }

        if (!intervalor_menor_que_maximo_regra) {
            while (permancencia >= minAcumulados) {
                valor += valor_ultimo_periodo_regra;
                minAcumulados += ultimo_intervalo;
            }
        }

        Date limiteParaSair = somaHora(acesso.getEntrada(), minAcumulados);

        acesso.setValorCobrado(valor);
        acesso.setLimiteParaSair(limiteParaSair);

        String permaneceu = UtilDateTime.minToHora(permancencia);
        acesso.setPermancencia(permaneceu);
        return acesso;
    }

    private Date somaHora(Date dataHora, Integer minutosParaAdicionar) {
        java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
        gc.setTime(dataHora);

        gc.add(java.util.Calendar.MINUTE, minutosParaAdicionar);
        return gc.getTime();
    }
}
