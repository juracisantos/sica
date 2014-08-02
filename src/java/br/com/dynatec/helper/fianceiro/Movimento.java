/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.helper.fianceiro;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jura
 */
@XmlRootElement
//@XmlType(name = "movimentos", namespace = "br.com.dynatec.helper.fianceiro.Movimento")
public class Movimento {

    private String tipoMovimento;
    private Double valor;

    public Movimento() {}
    
    public Movimento(String tipoMovimento, Double valor) {
        this.tipoMovimento = tipoMovimento;
        this.valor = valor;
    }
    
    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
