package br.com.dynatec.negocio;

import br.com.dynatec.entidade.Veiculo;
import br.com.dynatec.persistencia.CartaoDao;
import java.util.List;

public class CartaoNeg {

    private final CartaoDao cartaoDao;

    public CartaoNeg() {
        this.cartaoDao = new CartaoDao();
    }    
    
    public List<Veiculo> findAll() {
        return this.cartaoDao.findAll();
    }
       
    public String gerarNumeroCartao() {
        return this.cartaoDao.gerarNumeroCartao();
    }
    
    public Veiculo salvar(Veiculo e) throws Exception {
        return this.cartaoDao.salvar(e);
    }
}