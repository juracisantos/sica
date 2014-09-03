/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.helper;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jura
 */
public class AcessoSituacao implements Serializable {

    private Date entrada;
    private Date saida;
    private String cartao;
    private Date dataTransacaoFinanceira;
    private Double valorAPagar;
    private Double desconto;
    private Double valorPago;

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public Date getDataTransacaoFinanceira() {
        return dataTransacaoFinanceira;
    }

    public void setDataTransacaoFinanceira(Date dataTransacaoFinanceira) {
        this.dataTransacaoFinanceira = dataTransacaoFinanceira;
    }

    public Double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(Double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

}
