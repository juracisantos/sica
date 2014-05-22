package br.com.dynatec.negocio;

import br.com.dynatec.entidade.Acesso;
import br.com.dynatec.entidade.Regra;
import br.com.dynatec.persistencia.AcessoDao;
import br.jus.tjgo.bnmp.util.UtilDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcessoNeg {

    private final AcessoDao acessoDao;

    public AcessoNeg() {
        this.acessoDao = new AcessoDao();
    }

    static {
        AcessoNeg negocio = new AcessoNeg();
        Acesso a;
        a = negocio.find_by_numero_cartao("123456789012");
        if (a == null) {
            a = new Acesso();
            a.setAtivo(Boolean.TRUE);
            a.setCartao("123456789012");
            a.setEntrada(new Date());
            a.setUsuarioRegistrouEntrada(null);

            try {
                negocio.salvar(a);
            } catch (Exception ex) {
                Logger.getLogger(AcessoNeg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        a = negocio.find_by_numero_cartao("123456789013");
        if (a == null) {
            a = new Acesso();
            a.setAtivo(Boolean.TRUE);
            a.setCartao("123456789013");
            a.setEntrada(new Date());
            a.setUsuarioRegistrouEntrada(null);

            try {
                negocio.salvar(a);
            } catch (Exception ex) {
                Logger.getLogger(AcessoNeg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Acesso find_by_numero_cartao(String cartao) {
        return this.acessoDao.find_by_numero_cartao(cartao);
    }

    public List<Acesso> findAll() {
        return this.acessoDao.findAll();
    }

    public Acesso find(Integer id) {
        return this.acessoDao.find(id);
    }

    public Acesso salvar(Acesso e) throws Exception {
        e.setLiberado(Boolean.TRUE);        
        return acessoDao.salvar(e);
    }
    
    public Acesso consultarECalcular(Acesso e) {        
        Acesso acesso = acessoDao.find_by_numero_cartao(e.getCartao());
        acesso.setTabela(e.getTabela());
        acesso.setRegistroSaida(new Date());
        acesso = calcular(acesso);
        
        return acesso;
    }
    
    public Acesso calcularTroco(Acesso e) {
        Double valorCobrado = e.getValorCobrado() == null ? 0d : e.getValorCobrado();
        Double valorDesconto = e.getDesconto() == null ? 0d : e.getDesconto();
        Double valorRecebido = e.getValorRecebido() == null ? 0d : e.getValorRecebido();
        
        e.setValorAReceber(valorCobrado - valorDesconto);
        e.setTroco(valorRecebido - e.getValorAReceber());
                               
        return e;
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
        
        acesso.setValorCobrado(valor);
        
        String permaneceu = UtilDateTime.minToHora(permancencia);
        acesso.setPermancencia(permaneceu);
        return acesso;
    }

}
