package br.com.dynatec.negocio;

import br.com.dynatec.entidade.Acesso;
import br.com.dynatec.entidade.Configuracao;
import br.com.dynatec.entidade.Pessoa;
import br.com.dynatec.entidade.Regra;
import br.com.dynatec.entidade.Tabela;
import br.com.dynatec.persistencia.AcessoDao;
import br.jus.tjgo.bnmp.util.UtilDateTime;
import java.util.Date;
import java.util.List;

public class AcessoNeg {

    private final AcessoDao acessoDao;
    private final PessoaNeg pessoaNeg;
    private final ConfiguracaoNeg configuracaoNeg;
    private final TabelaNeg tabelaNeg;

    public AcessoNeg() {
        this.acessoDao = new AcessoDao();
        this.pessoaNeg = new PessoaNeg();
        this.configuracaoNeg = new ConfiguracaoNeg();
        this.tabelaNeg = new TabelaNeg();
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

    public Acesso salvarPeriodoAdicional(Acesso e) throws Exception {
        e.setLiberado(Boolean.TRUE);
        Double valorCobrado = e.getValorCobrado();
        e.setValorCobrado(valorCobrado);
        return acessoDao.salvar(e);
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
    
    public Acesso consultarECalcular(String cartao, Integer codTabela, Double valorRecebido, Double descontoAtual) throws Exception {        
        Acesso acesso;        
        cartao = cartao.length() > 11 ? cartao.substring(0, 11) : cartao;
        acesso = acessoDao.find_by_numero_cartao(cartao);                
        
        acesso.setValorRecebido(valorRecebido);
        acesso.setDescontoAtual(descontoAtual);
        acesso.setUltimoValorPago(acesso.getValorAReceber()+acesso.getUltimoValorPago());
        acesso.setUltimoDescontoDado(acesso.getDescontoAtual()+acesso.getUltimoDescontoDado());
        acesso.setDescontoAtual(0.0d);
        
        if (acesso != null) {
            Tabela tabela = tabelaNeg.find(codTabela);
            acesso.setTabela(tabela);
            acesso.setRegistroSaida(new Date());
            //acesso.setUltimoValorPago(acesso.getValorCobrado() - acesso.getDesconto());
            
            if (acesso.getTabela() == null) {
                throw new Exception("Cadastre pelo menos uma tabela com regras para cobrança.");
            }
            
            if (acesso.getSaida() != null) {
                throw new Exception("Cartão já utilizado, registro da saída as " + UtilDateTime.dateTimeToString(acesso.getSaida()));
            }            
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
        
        Configuracao conf = this.configuracaoNeg.findByDescricao("Padrão");
     
        if (permancencia <= conf.getToleranciaMinimaSemPagar()) {
            minAcumulados = conf.getToleranciaMinimaSemPagar();
        } else {
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
        }

        Date limiteParaSair = somaHora(acesso.getEntrada(), minAcumulados);
               
//        if (permancencia > conf.getToleranciaMinimaSemPagar()) {
          limiteParaSair = somaHora(limiteParaSair, conf.getTolerancia());
//        }

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
