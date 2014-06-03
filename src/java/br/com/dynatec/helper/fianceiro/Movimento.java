/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.helper.fianceiro;

import br.com.dynantec.type.TipoMovimento;

/**
 *
 * @author jura
 */
public class Movimento {

    private TipoMovimento tipoMovimento;
    private Double valor;

    public Movimento(TipoMovimento tipoMovimento, Double valor) {
        this.tipoMovimento = tipoMovimento;
        this.valor = valor;
    }
    
    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
